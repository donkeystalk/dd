package dardo.bb.go;

public class Armor extends Item{
	
	private int bonus = 0;
	private int ac = 10;
	
	public Armor()
	{
		name = "Leather Armor";
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

}
