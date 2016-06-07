package combat;

import java.util.Random;

public class Attack {
	private int damageDie, numberDice, mod;

	public void attack(Entity e) {
		Random r = new Random();
		int damage = 0;
		for (int i = 0; i < numberDice; i++) {
			damage += r.nextInt(damageDie);
		}
	}
}