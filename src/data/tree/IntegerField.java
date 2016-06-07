package data.tree;


public class IntegerField extends Field {
	public int value;
	public int min, max;
	
	public IntegerField(String name, int value, int min, int max) {
		super(name, "Integer");
		this.value = value;
		this.min = min;
		this.max = max;
	}

}