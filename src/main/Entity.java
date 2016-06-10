package main;

/**
 * Simple container class containing all fields used by the {@link EntityDataModel}.
 * TODO add in editable Fields functionality.
 * @author Michael
 *
 */
public class Entity {
	public String Name;
	public int Str, Con, Dex, Int, Wis, Cha;
	public int AC, Fort, Ref, Will;
	
	public Entity(String name){
		Name = name;
	}
}