import java.util.List;


public class SimulatorDefinitions {

	private List<InputSequence> inputSequences;

	private List<String> alphabet;
	
	private String startingState;
	private List<String> states;
	private List<String> acceptableStates;
	
	private String startingStackSymbol;
	private List<String> stackSymbols;
	
	private List<Transition> transitions;
	
	public List<InputSequence> getInputSequences() {
		return inputSequences;
	}

	public void setInputSequences(List<InputSequence> inputSequences) {
		this.inputSequences = inputSequences;
	}

	public String getStartingState() {
		return startingState;
	}

	public void setStartingState(String startingState) {
		this.startingState = startingState;
	}
	
	public List<String> getAcceptableStates() {
		return acceptableStates;
	}
	
	public void setAcceptableStates(List<String> acceptableStates) {
		this.acceptableStates = acceptableStates;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public List<String> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(List<String> alphabet) {
		this.alphabet = alphabet;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public void setStackSymbols(List<String> stackSymbols) {
		this.stackSymbols = stackSymbols;
	}

	public void setStartingStackSymbol(String startingStackSymbol) {
		this.startingStackSymbol = startingStackSymbol;
	}

	public List<String> getStackSymbols() {
		return stackSymbols;
	}

	public String getStartingStackSymbol() {
		return startingStackSymbol;
	}
	
	public void print() {
		System.out.println("========================");
		System.out.println("Input sequences");
		for(InputSequence inputSequence : inputSequences) {
			System.out.println(inputSequence);
		}
		
		System.out.println("========================");
		System.out.println("Alphabet symbols");
		for(String symbol : alphabet) {
			System.out.println(symbol);
		}
		
		System.out.println("========================");
		System.out.println("Starting state");
		System.out.println(startingState);
		
		System.out.println("========================");
		System.out.println("States");
		for(String state : states) {
			System.out.println(state);
		}
		
		System.out.println("========================");
		System.out.println("Acceptable states");
		for(String state : acceptableStates) {
			System.out.println(state);
		}
		
		System.out.println("========================");
		System.out.println("Starting stack symbol");
		System.out.println(startingStackSymbol);
		
		System.out.println("========================");
		System.out.println("Stack symbols");
		for(String symbol : stackSymbols) {
			System.out.println(symbol);
		}
		
		System.out.println("========================");
		System.out.println("Transitions");
		for(Transition transition : transitions) {
			System.out.println(transition);
		}
	}
	
	
}
