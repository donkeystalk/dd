package dardo.bb.entity;

import java.util.Map;

import dardo.bb.enums.AttributeEnum;
import dardo.bb.go.Armor;
import dardo.bb.go.Weapon;
import dardo.bb.utils.DiceRoller;
import dardo.bb.utils.GameUtils;

public class Entity {

	protected Weapon weapon = new Weapon();
	protected String name = "Skeleton";
	protected Armor armor = new Armor();
	protected int energy;
	protected int currentEnergy;
	protected int level;
	protected int hp;
	protected Map<AttributeEnum, Integer> attributes = GameUtils.getDefaultAttributes();
	protected int currentHp = getHp();
	protected int attackBonus = (level + GameUtils.getStatModifier(attributes.get(AttributeEnum.DEXTERITY)));
	
	public int getCurrentHp() {
		return currentHp;
	}

	public int getAc()
	{
		return (GameUtils.getStatModifier(attributes.get(AttributeEnum.DEXTERITY)) + armor.getBonus() + armor.getAc());
	}
	
	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public int getHp() {
		return 10 + (level * (4 + GameUtils.getStatModifier(attributes.get(AttributeEnum.CONSTITUTION))));
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public int getEnergy() {
		return 10 + (level * (4 + GameUtils.getStatModifier(attributes.get(AttributeEnum.INTELLIGENCE))));
	}

	public int getCurrentEnergy() {
		return currentEnergy;
	}

	public void setCurrentEnergy(int currentEnergy) {
		this.currentEnergy = currentEnergy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Map<AttributeEnum, Integer> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<AttributeEnum, Integer> attributes) {
		this.attributes = attributes;
	}
	
	public int getAttackBonus() {
		return attackBonus;
	}

	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	public int getDamage()
	{
		int retVal = 0;
		for(int i=0; i<weapon.getRolls(); i++)
		{
			retVal += DiceRoller.RollNSidedDie(weapon.getDie());
		}
		retVal += weapon.getBonus();
		retVal += GameUtils.getStatModifier(attributes.get(AttributeEnum.STRENGTH));
		return retVal > 0 ? retVal : 1;
	}
	
}
