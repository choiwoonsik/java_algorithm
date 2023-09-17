package 힣_코테.코테_콘웨_2022_03;

import java.util.*;

class Solution2 {

	ArrayList<Monster> monsters = new ArrayList<>();

	public int solution(int[][] stats) {

		for (int[] stat : stats)
		{
			monsters.add(new Monster(stat[0], stat[1]));
		}


		monsters.sort(Comparator.comparingInt(M -> M.atk)); // atk 작은순
		monsters.sort(Comparator.comparingInt(M -> M.def)); // def 작은순

		int cycle = 0;
		int mostCycle = 0;
		Monster front = new Monster(0, 0);

		for (Monster mon : monsters)
		{
			if (front.atk == 0) {
				cycle = 1;
				front = mon;
			} else {
				if (mon.atk > front.atk && mon.def > front.def) {
					// win
					cycle += 1;
				} else {
					// lose
					mostCycle = Math.max(mostCycle, cycle);
					cycle = 1;
				}
				front = mon;
			}
			mostCycle = Math.max(mostCycle, cycle);
		}

		return mostCycle;
	}

	private static class Monster
	{
		int atk;
		int def;

		Monster (int atk, int def) {
			this.atk = atk;
			this.def = def;
		}
	}
}
