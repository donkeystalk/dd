package dardo.bb.entity;

import java.util.List;

import dardo.bb.go.Item;

public class Player extends Entity{
	
	private int currentExp = 0;
	private List<Item> items;
	
	public int attack() {
		return 0;
	}
	
	public int getCurrentExp() {
		return currentExp;
	}

	public void setCurrentExp(int currentExp) {
		this.currentExp = currentExp;
	}

	public int getExperienceToLevel()
	{
		return (1000 * (level * level));
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
