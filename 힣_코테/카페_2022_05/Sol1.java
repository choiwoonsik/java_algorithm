package 힣_코테.카페_2022_05;

import java.util.*;

public class Sol1 {
	public static void main(String[] args) {
		Solution sol = new Solution();

	}

	private static class Solution {
		static PriorityQueue<Person> pqInner = new PriorityQueue<>(
				(p1, p2) -> {
					if (p1.point != p2.point) {
						return -Integer.compare(p1.point, p2.point);
					}
					return Integer.compare(p1.id, p2.id);
				}
		);

		static PriorityQueue<Person> pqOuter = new PriorityQueue<>(
				(p1, p2) -> {
					if (p1.point != p2.point) {
						return -Integer.compare(p1.point, p2.point);
					}
					return Integer.compare(p1.id, p2.id);
				}
		);

		static int[] getTime;

		public int[] solution(int region, int num, int[][] info) {

			getTime = new int[info.length];
			Arrays.fill(getTime, -1);

			int id = -1;
			for (int[] personInfo : info) {
				id++;
				int myRegion = personInfo[0];
				int noTime = personInfo[1];
				int reTime = personInfo[2];
				int fCount = personInfo[3];

				int point = (noTime + 1) * 2 + (reTime + 2) + (fCount + 1) * 5;

				if (myRegion == region) {
					pqInner.add(new Person(id, point));
				} else {
					pqOuter.add(new Person(id, point));
				}
			}

			int time = 0;
			while (!pqInner.isEmpty()) {
				Person p = pqInner.poll();
				time++;

				getTime[p.id] = time;

				if (time == num) {
					return getTime;
				}
			}

			while (!pqOuter.isEmpty()) {
				Person p = pqOuter.poll();
				time++;

				getTime[p.id] = time;

				if (time == num) {
					return getTime;
				}
			}

			return getTime;
		}

		private static class Person {
			int id;
			int point;

			Person(int id, int p) {
				this.id = id;
				this.point = p;
			}
		}
	}
}

