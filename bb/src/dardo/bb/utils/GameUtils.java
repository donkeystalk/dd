package dardo.bb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dardo.bb.entity.Entity;
import dardo.bb.entity.EntityContainer;
import dardo.bb.entity.Player;
import dardo.bb.enums.AttributeEnum;

public class GameUtils {

	public static final String HORIZONTAL_DASH = "-------------------";

	public static void DisplayStats(Player entity)
	{
		System.out.println("Name: " + entity.getName());
		System.out.println("Level: " + entity.getLevel());
		System.out.println("Health: " + entity.getCurrentHp() + "/" + entity.getHp());
		System.out.println("Energy: " + entity.getCurrentEnergy() + "/" + entity.getEnergy());
		System.out.println("Experience: " + entity.getCurrentExp() + "/" + entity.getExperienceToLevel());
		System.out.println("AC: " + entity.getAc());
		System.out.println(HORIZONTAL_DASH);
		for(AttributeEnum e : AttributeEnum.values())
		{
			System.out.println(e.toString() + ": " + entity.getAttributes().get(e) + "(" + getStatModifier(entity.getAttributes().get(e)) + ")" );
		}
	}
	
	public static int getStatModifier(int stat)
	{
		return (stat - 10) / 2;
	}
	
	public static Map<AttributeEnum, Integer> getDefaultAttributes()
	{
		Map<AttributeEnum, Integer> attributes = new HashMap<AttributeEnum, Integer>();
		for(AttributeEnum e : AttributeEnum.values())
		{
			attributes.put(e, 10);
		}
		return attributes;
	}
	
	public static boolean isHit(int roll, int toHit)
	{
		return roll >= toHit ? true : false;
	}
	
	public static boolean battleLoop(List<Entity> entities) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int enemySelection = 0;
		TreeMap<Integer, Entity> initiative = new TreeMap<Integer, Entity>();
		List<Entity> enemies = new ArrayList<Entity>();
		for(Entity e : entities)
		{
			initiative.put(DiceRoller.RollD20() + e.getAttackBonus(), e);
		}
		initiative.put(DiceRoller.RollD20() + EntityContainer.player.getAttackBonus(), EntityContainer.player);
		for(Integer i : initiative.keySet())
		{
			if(!initiative.get(i).equals(EntityContainer.player))
			{
				enemies.add(initiative.get(i));
			}
		}
		while(true)
		{
			for(Integer i : initiative.keySet())
			{
				if(initiative.get(i).equals(EntityContainer.player))
				{
					displayEnemies(enemies);
					while(true)
					{
						String input = "";
						try 
						{
							enemySelection = Integer.parseInt(input = reader.readLine());
							while(enemySelection > enemies.size() ||
							   enemySelection < 0)
							{
								System.out.println("Select Enemy within bounds");
								displayEnemies(enemies);
								enemySelection = Integer.parseInt(input = reader.readLine());
							}
							if(!attack(EntityContainer.player, enemies.get(enemySelection - 1)))
							{
								for(Map.Entry<Integer, Entity> e : initiative.entrySet())
								{
									if(e.getValue().equals(enemies.get(enemySelection - 1)))
									{
										initiative.remove(e.getKey());
										break;
									}
								}
								enemies.remove(enemySelection - 1);
								if(enemies.size() == 0)
								{
									return true;
								}
							}
							break;
						} 
						catch (NumberFormatException e) {
							if(input.equals("?"))
							{
								displayHelp();
								displayEnemies(enemies);
							}
						}
						catch (IOException e) {
						}
					}
				}
				else
				{
					if(!attack(initiative.get(i), EntityContainer.player))
					{
						return false;
					}
				}
			}
		}
	}
	
	private static void displayEnemies(List<Entity> enemies)
	{
		for(int j = 0; j < enemies.size(); j++)
		{
			System.out.println(j+1 + ": " + enemies.get(j).getName());
		}
		System.out.print("Attack (1 - " + enemies.size() + ") or ? for help: ");
	}
	
	/**
	 * 
	 * @param attacker
	 * @param defender
	 * @return true if battle continues, false if defender dies
	 */
	private static boolean attack(Entity attacker, Entity defender)
	{
		int damage = 0;
		int hit = DiceRoller.RollD20();
		if(isHit(hit + attacker.getAttackBonus(), defender.getAc()))
		{
			if(isCritical(hit, attacker))
			{
				System.out.println(attacker.getName() + " takes a swing, and CRITICALLY hits!. (" + hit + " vs. " + defender.getAc() + ")");
				damage = attacker.getDamage() * attacker.getWeapon().getCritBonus();
			}
			else
			{
				System.out.println(attacker.getName() + " takes a swing, and hits!. (" + hit + " vs. " + defender.getAc() + ")");
				damage = attacker.getDamage();
			}
			defender.setCurrentHp(defender.getCurrentHp() - damage);
			System.out.println(defender.getName() + " takes " + damage + " points of damage.");
			if(defender.getCurrentHp() < 0)
			{
				System.out.println(defender.getName() + " dies.");
				return false;
			}
		}
		else
		{
			System.out.println(attacker.getName() + " takes a swing, but misses. (" + hit + " vs. " + defender.getAc() + ")");
		}
		return true;
	}
	
	private static boolean isCritical(int hit, Entity attacker)
	{
		if(hit >= attacker.getWeapon().getCritical())
		{
			return true;
		}
		return false;
	}
	
	public static void displayHelp() throws IOException
	{
		String input = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(!input.toUpperCase().equals("B"))
		{
			System.out.println("Possible Entries : ");
			System.out.println("S - Status");
			System.out.println("B - Back");
			System.out.print("Enter: ");
			input = reader.readLine();
			if(input.toUpperCase().equals("S"))
			{
				GameUtils.DisplayStats(EntityContainer.player);
			}
		}
	}
	
	
}
