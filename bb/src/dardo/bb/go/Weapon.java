package dardo.bb.go;

public class Weapon extends Item{
	private int rolls = 1;
	private int die = 4;
	private int bonus = 0;
	private int critical = 20;
	private int critBonus = 2;
	
	public Weapon()
	{
		name = "Short Sword";
	}

	public Weapon(int rolls, int die, int bonus)
	{
		this.rolls = rolls;
		this.die = die;
		this.bonus = bonus;
	}
	
	public int getCritBonus() {
		return critBonus;
	}

	public int getRolls() {
		return rolls;
	}

	public int getDie() {
		return die;
	}

	public int getBonus() {
		return bonus;
	}

	public int getCritical() {
		return critical;
	}
}
