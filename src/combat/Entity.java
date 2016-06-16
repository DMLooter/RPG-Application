package combat;

import java.io.Serializable;
import java.util.Random;

import data.tree.EntityDataModel;

/**
 * Simple container class containing all fields used by the
 * {@link EntityDataModel}. TODO add in editable Fields functionality.
 * 
 * @author Michael
 *
 */
public class Entity implements Serializable {
	public int ID;

	public int Level;
	public int x, y;
	public String Name;
	public int Str, Con, Dex, Int, Wis, Cha;
	public int strMod, conMod, dexMod, intMod, wisMod, chaMod;
	public int AC, Fort, Ref, Will;
	public int Health, Surge, fullHealth;
	public int Init;
	public String[] Languages;
	public int Speed;

	public Entity(String name) {
		this(name, new Random().nextInt());
	}

	public Entity(String name, int ID) {
		Name = name;
		this.ID = ID;
	}
}