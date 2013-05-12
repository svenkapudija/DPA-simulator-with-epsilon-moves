import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class SimPaInputReader {

	private final static String EMPTY_STACK_SYMBOL = "$";
	
	private List<InputSequence> inputSequences;

	private List<String> alphabet;
	
	private String startingState;
	private List<String> states;
	private List<String> acceptableStates;
	
	private String startingStackSymbol;
	private List<String> stackSymbols;
	
	private List<Transition> transitions;
	
	public void readInput(InputStream is) throws SimPaInputReaderException {
		List<String> inputLines = readInputLines(is);
		if(inputLines.size() < 5) {
			throw new SimPaInputReaderException();
		}
		
		inputSequences = extractInputSequences(inputLines.get(0));
		
		states = splitToStates(inputLines.get(1));
		acceptableStates = splitToStates(inputLines.get(4));
		
		alphabet = splitToList(inputLines.get(2));
		stackSymbols = extractStackSymbols(inputLines.get(3));
		startingState = inputLines.get(5);
		startingStackSymbol = inputLines.get(6);
		transitions = extractTransitions(inputLines);
	}

	private List<String> extractStackSymbols(String line) {
		List<String> symbols = new ArrayList<String>();
		String[] lineArray = line.split(",");
		
		for(String symbol : lineArray) {
			symbols.add(symbol);
		}
		
		return symbols;
	}

	private List<Transition> extractTransitions(List<String> inputLines) {
		List<Transition> transitions = new ArrayList<Transition>();
		
		for(int i = 7; i < inputLines.size(); i++) {
			String line = inputLines.get(i);
			String[] splitByArrow = line.split("->");
			
			String startState = splitByArrow[0].split(",")[0];
			String symbol = splitByArrow[0].split(",")[1];
			String popFromStack = new String(splitByArrow[0].split(",")[2]);
			
			String endState = splitByArrow[1].split(",")[0];
			
			Stack pushOnStack = new Stack();
			String pushOnStackString = splitByArrow[1].split(",")[1];
			if(!pushOnStackString.equals(EMPTY_STACK_SYMBOL)) {
				for(int j = pushOnStackString.length()-1; j >= 0; j--) {
					String stackSymbol = Character.toString(pushOnStackString.charAt(j));
					pushOnStack.push(stackSymbol);
				}
			}
			
			transitions.add(new Transition(startState, symbol, popFromStack, endState, pushOnStack));
		}
		
		return transitions;
	}
	
	private List<String> splitToStates(String line) {
		List<String> states = new ArrayList<String>();
		String[] lineArray = line.split(",");
		
		for(String stateValue : lineArray) {
			states.add(stateValue);
		}
		
		return states;
	}
	
	private List<String> splitToList(String line) {
		String[] lineArray = line.split(",");
		return Arrays.asList(lineArray);
	}
	
	private List<InputSequence> extractInputSequences(String line) {
		List<InputSequence> list = new ArrayList<InputSequence>();
		
		String[] lineArray = line.split("\\|");
		for(String inputSequeneLine : lineArray) {
			InputSequence inputSequene = new InputSequence(inputSequeneLine);
			list.add(inputSequene);
		}
		
		return list;
	}
	
	private List<String> readInputLines(InputStream is) {
		Scanner s = new Scanner(is);
		List<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		
		return list;
	}

	public SimulatorDefinitions generateSimulatorDefinitions() {
		SimulatorDefinitions simulatorDefinitions = new SimulatorDefinitions();
		
		simulatorDefinitions.setAlphabet(alphabet);
		simulatorDefinitions.setInputSequences(inputSequences);
		
		simulatorDefinitions.setStartingState(startingState);
		simulatorDefinitions.setAcceptableStates(acceptableStates);
		simulatorDefinitions.setStates(states);
		
		simulatorDefinitions.setStartingStackSymbol(startingStackSymbol);
		simulatorDefinitions.setStackSymbols(stackSymbols);
		
		simulatorDefinitions.setTransitions(transitions);
		
		return simulatorDefinitions;
	}
	
}
