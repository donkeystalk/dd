package dardo.bb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dardo.bb.entity.Entity;
import dardo.bb.entity.EntityContainer;
import dardo.bb.entity.Player;
import dardo.bb.enums.AttributeEnum;
import dardo.bb.go.Weapon;
import dardo.bb.utils.DiceRoller;
import dardo.bb.utils.GameUtils;

public class Entry {

	public static void main(String[] args) throws IOException {
		EntityContainer.player = rollCharacter();
		GameUtils.DisplayStats(EntityContainer.player);
		List<Entity> enemies = new ArrayList<Entity>();
		enemies.add(new Entity());
		Entity badger = new Entity();
		badger.setName("Badger");
		enemies.add(badger);
		GameUtils.battleLoop(enemies);
	}
	
	private static Player rollCharacter() throws IOException
	{
		Player character = new Player();
		System.out.println("Create a new character!");
		Map<AttributeEnum, Integer> stats = new HashMap<AttributeEnum, Integer>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		System.out.print("Enter Player Name: ");
		character.setName(reader.readLine());
		System.out.println(GameUtils.HORIZONTAL_DASH);
		while(true)
		{
			if(input.toUpperCase().equals("N"))
			{
				break;
			}
			stats.clear();
			for(AttributeEnum e : AttributeEnum.values())
			{
				stats.put(e, DiceRoller.RollForStat());
				System.out.println(e.toString() + "\t" + stats.get(e));
			}
			System.out.println(GameUtils.HORIZONTAL_DASH);
			System.out.print("Reroll? (y/n): ");
			input = reader.readLine();
			if(!input.toUpperCase().equals("Y"))
			{
				while(!input.toUpperCase().equals("Y") && !input.toUpperCase().equals("N"))
				{
					System.out.print("Enter y or n please: ");
					input = reader.readLine();
				}
			}
		}
		character.setAttributes(stats);
		character.setLevel(1);
		character.setCurrentHp(character.getHp());
		character.setCurrentEnergy(character.getEnergy());
		Weapon playerWeapon = new Weapon(1,6,0);
		character.setWeapon(playerWeapon);
		return character;
	}
	
}
