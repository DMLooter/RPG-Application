package data.tree;

import combat.Entity;
import main.Main;

public class EntityData {
	public static Entity lookup(int entityID){
		for(int i = 0; i < Main.entities.size(); i ++){
			if(Main.entities.get(i).ID == entityID){
				return Main.entities.get(i);
			}
		}
		return null;
	}
}
