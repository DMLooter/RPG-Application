package combat;

public class Entity {
	public int Level;
	public int x, y;
	public String Name;
	public int str, con, dex, inte, wis, cha;
	public int strMod, conMod, dexMod, intMod, wisMod, chaMod;
	public int AC, Fort, Ref, Will;
	public int Health, Surge, fullHealth;
	public int Init;
	public String[] Languages;
	public int Speed;

	public Entity(String s) {
		Name = s;
	}
}