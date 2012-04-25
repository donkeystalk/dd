package dardo.bb.enums;

public enum AttributeEnum {

	STRENGTH("Strength"), DEXTERITY("Dexterity"), INTELLIGENCE("Intelligence"), CONSTITUTION("Constitution");
	
	private String name;
	
	private AttributeEnum(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
