import java.util.ArrayList;
import java.util.List;


public class Stack {

	private List<String> stack;
	
	public Stack() {
		this.stack = new ArrayList<String>();
	}
	
	public Stack(List<String> stack) {
		this.stack = new ArrayList<String>(stack);
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public void push(String symbol) {
		stack.add(symbol);
	}
	
	public String pop() {
		if(stack.isEmpty()) {
			return null;
		}
		
		String popSymbol = stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
		
		return popSymbol;
	}
	
	@Override
	public String toString() {
		Stack tmpStack = new Stack(stack);
		String pushOnStackString = "";
		while(!tmpStack.isEmpty()) {
			pushOnStackString += tmpStack.pop();
		}
		
		return pushOnStackString;
	}

	public String peek() {
		if(stack.isEmpty()) {
			return null;
		}
		
		return stack.get(stack.size()-1);
	}

	public void push(Stack pushOnStack) {
		Stack tmpPushOnStack = new Stack(pushOnStack.getValues());
		
		Stack tmpStack = new Stack();
		while(!tmpPushOnStack.isEmpty()) {
			tmpStack.push(tmpPushOnStack.pop());
		}
		
		while(!tmpStack.isEmpty()) {
			push(tmpStack.pop());
		}
	}

	private List<String> getValues() {
		return stack;
	}
	
}
