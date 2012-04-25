package dardo.bb.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DiceRoller {

	private static final int STAT_DIE = 6;
	
	public static int RollNSidedDie(int die)
	{
		Random ran = new Random();
		return ran.nextInt(die) + 1;
	}
	
	public static int RollD20()
	{
		return RollNSidedDie(20);
	}
	
	public static int RollForStat()
	{
		List<Integer> stats = new ArrayList<Integer>();
		int retVal = 0;
		for(int i=0; i<3; i++)
		{
			stats.add(RollNSidedDie(STAT_DIE));
		}
		Collections.sort(stats);
		if(stats.get(0) <= 10)
		{
			stats.remove(0);
			stats.add(RollNSidedDie(STAT_DIE));
		}
		for(Integer i : stats)
		{
			retVal += i;
		}
		return retVal;
	}
}
