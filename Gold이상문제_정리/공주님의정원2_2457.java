package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 공주님의정원2_2457 {
	static int N;
	static int START_DAY = 301;
	static int DIE_DAY = 1201;
	static HashMap<Integer, Integer> flowerMap = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
		 	st = new StringTokenizer(br.readLine());
		 	int sm = Integer.parseInt(st.nextToken()) * 100;
			int sd = Integer.parseInt(st.nextToken());
			int dm = Integer.parseInt(st.nextToken()) * 100;
			int dd = Integer.parseInt(st.nextToken());

			int start = sm + sd;
			int die = dm + dd;

			if (flowerMap.get(start) == null || flowerMap.get(start) < die) {
				flowerMap.put(start, die);
			}
		}

		System.out.println(count_flower());
	}

	private static int count_flower() {
		int count = 0;
		int NOW = START_DAY;
		boolean flag = false;

		while (NOW < DIE_DAY) {
			int LAST = NOW;
			for (int flower_start : flowerMap.keySet())
			{
				if (flower_start <= NOW && flowerMap.get(flower_start) > LAST) {
					LAST = flowerMap.get(flower_start);
					flag = true;
				}
			}

			if (flag) {
				NOW = LAST;
				count++;
				flag = false;
			} else {
				count = 0;
				break;
			}
		}
		return count;
	}
}
