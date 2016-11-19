package data.tree;

import combat.Entity;

public class StringField extends Field {
	public String value;

	public StringField(String name, String value, int ent, String fID) {
		super(name, "String", ent, fID);
		this.value = value;
	}

	@Override
	public void update(boolean push) {
		Entity e = EntityData.lookup(entityID);
		java.lang.reflect.Field f;
		try {
			f = e.getClass().getField(fieldName);
			if (push) {
				f.set(e, value);
			} else {
				value = (String) f.get(e);
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
}