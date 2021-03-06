
public class State {

	private String value;
	private boolean isAcceptable;
	
	public State(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setAcceptable(boolean isAcceptable) {
		this.isAcceptable = isAcceptable;
	}
	
	public boolean isAcceptable() {
		return isAcceptable;
	}
	
	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		State other = (State) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
