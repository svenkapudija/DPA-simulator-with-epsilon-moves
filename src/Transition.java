


public class Transition {

	private String startState;
	private String symbol;
	private String popFromStack;
	
	private String endState;
	private Stack pushOnStack;
	
	public Transition(String startState, String symbol, String popFromStack, String endState, Stack pushOnStack) {
		this.startState = startState;
		this.symbol = symbol;
		this.endState = endState;
		this.popFromStack = popFromStack;
		this.pushOnStack = pushOnStack;
	}

	public String getEndState() {
		return endState;
	}
	
	public String getStartState() {
		return startState;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getPopFromStack() {
		return popFromStack;
	}
	
	public Stack getPushOnStack() {
		return pushOnStack;
	}
	
	@Override
	public String toString() {
		return startState + "," + symbol + "," + popFromStack + "->" + endState + "," + pushOnStack;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endState == null) ? 0 : endState.hashCode());
		result = prime * result
				+ ((popFromStack == null) ? 0 : popFromStack.hashCode());
		result = prime * result
				+ ((pushOnStack == null) ? 0 : pushOnStack.hashCode());
		result = prime * result
				+ ((startState == null) ? 0 : startState.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition other = (Transition) obj;
		if (endState == null) {
			if (other.endState != null)
				return false;
		} else if (!endState.equals(other.endState))
			return false;
		if (popFromStack == null) {
			if (other.popFromStack != null)
				return false;
		} else if (!popFromStack.equals(other.popFromStack))
			return false;
		if (pushOnStack == null) {
			if (other.pushOnStack != null)
				return false;
		} else if (!pushOnStack.equals(other.pushOnStack))
			return false;
		if (startState == null) {
			if (other.startState != null)
				return false;
		} else if (!startState.equals(other.startState))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

}
