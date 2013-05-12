import java.util.List;


public class Simulator {

	private static final String INPUT_EPSILON_TRANSITION_SYMBOL = "$";
	
	private static final String OUTPUT_SYMBOL_EMPTY_STACK = "$";
	private static final String OUTPUT_STATE_LISTS_SEPARATOR = "|";
	private static final String OUTPUT_SYMBOL_STACK_SEPARATOR = "#";
	
	private SimulatorDefinitions simulatorDefinitions;
	
	public Simulator(SimulatorDefinitions simulatorDefinitions) {
		this.simulatorDefinitions = simulatorDefinitions;
	}

	public void run() {
		List<InputSequence> inputSequences = simulatorDefinitions.getInputSequences();
		for(InputSequence inputSequence : inputSequences) {
			String currentState = simulatorDefinitions.getStartingState();
			Stack currentStack = new Stack();
			currentStack.push(simulatorDefinitions.getStartingStackSymbol());
			
			System.out.print(statesTextFormatting(currentState, currentStack));
			
			int alphabetIndex = 0;
			List<String> alphabet = inputSequence.getAlphabet();
			while(alphabetIndex < alphabet.size()) {
				if(currentStack.isEmpty()) {
					currentState = null;
					break;
				}
				
				String symbol = alphabet.get(alphabetIndex);
				Transition transition = getTransition(currentState, symbol, currentStack);
				
				if(transition == null) {
					// Try to get out with epsilon transition(s)
					Transition transitionWithEpsilon = getTransitionWithEpsilon(currentState, currentStack);
					if(transitionWithEpsilon == null) {
						currentState = null;
						break;
					} else {
						currentState = transitionWithEpsilon.getEndState();
						currentStack.pop();
						currentStack.push(transitionWithEpsilon.getPushOnStack());
						
						System.out.print(OUTPUT_STATE_LISTS_SEPARATOR + statesTextFormatting(currentState, currentStack));
						
						// Don't increase alphabet index because you need to
						// check same alphabet symbol again
					}
				} else {
					currentState = transition.getEndState();
					currentStack.pop();
					currentStack.push(transition.getPushOnStack());

					System.out.print(OUTPUT_STATE_LISTS_SEPARATOR + statesTextFormatting(currentState, currentStack));
					
					// Go to next symbol of alphabet (input sequence)
					alphabetIndex++;
				}
			}
			
			
			if(currentState == null) {
				System.out.print(OUTPUT_STATE_LISTS_SEPARATOR + "fail|0");
			} else if(isAcceptable(currentState)) {
				System.out.print(OUTPUT_STATE_LISTS_SEPARATOR + "1");
			} else {
				// Try with epsilon transitions move to acceptable state
				// while stack is not empty and currenState is still unacceptable
				
				while(!currentStack.isEmpty() && !isAcceptable(currentState)) {
					Transition transitionWithEpsilon = getTransitionWithEpsilon(currentState, currentStack);
					if(transitionWithEpsilon != null) {
						currentState = transitionWithEpsilon.getEndState();
						currentStack.pop();
						currentStack.push(transitionWithEpsilon.getPushOnStack());
						
						System.out.print(OUTPUT_STATE_LISTS_SEPARATOR + statesTextFormatting(currentState, currentStack));
					} else {
						break;
					}
				}
				
				if(isAcceptable(currentState)) {
					System.out.print(OUTPUT_STATE_LISTS_SEPARATOR + "1");
				} else {
					System.out.print(OUTPUT_STATE_LISTS_SEPARATOR + "0");
				}
			}
			
			System.out.println();
		}
	}

	private boolean isAcceptable(String currentState) {
		return simulatorDefinitions.getAcceptableStates().contains(currentState);
	}

	private Transition getTransition(String state, String symbol, Stack stack) {
		List<Transition> transitions = simulatorDefinitions.getTransitions();
		for(Transition transition : transitions) {
			if(transition.getStartState().equals(state) && transition.getSymbol().equals(symbol) && transition.getPopFromStack().equals(stack.peek())) {
				return transition;
			}
		}
		
		return null;
	}
	
	private Transition getTransitionWithEpsilon(String state, Stack stack) {
		List<Transition> transitions = simulatorDefinitions.getTransitions();
		for(Transition transition : transitions) {
			if(transition.getSymbol().equals(INPUT_EPSILON_TRANSITION_SYMBOL) && transition.getStartState().equals(state) && transition.getPopFromStack().equals(stack.peek())) {
				return transition;
			}
		}
		
		return null;
	}
	
	private String statesTextFormatting(String state, Stack stack) {
		return state + OUTPUT_SYMBOL_STACK_SEPARATOR + (stack.isEmpty() ? OUTPUT_SYMBOL_EMPTY_STACK : stack);
	}

}
