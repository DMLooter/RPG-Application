package data.tree;

import combat.Entity;
import main.Main;

public class IntegerField extends Field {
	public int value;
	public int min, max;

	public IntegerField(String name, int value, int min, int max, int ent, String fID) {
		super(name, "Integer", ent, fID);
		this.value = value;
		this.min = min;
		this.max = max;
	}

	@Override
	public void update(boolean push) {
		Entity e = EntityData.lookup(entityID);
		java.lang.reflect.Field f;
		try {
			f = e.getClass().getField(fieldName);
			if (push) {
				f.setInt(e, value);
			} else {
				value = f.getInt(e);
				Main.mainScreen.dataScreen.redraw(this);
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
}