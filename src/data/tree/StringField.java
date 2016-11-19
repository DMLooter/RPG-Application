package data.tree;


public class StringField extends Field {
	public String value;
	
	public StringField(String name, String value) {
		super(name, "String");
		this.value = value;
	}

}