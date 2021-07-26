package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 정보상인호석_22252 {
	static long Q, SUM;
	static HashMap<String, PriorityQueue<Integer>> infosMap = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Q = Integer.parseInt(st.nextToken());

		while (Q-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if (type == 1) {
				gorilla(st);
			} else {
				hosuck(st);
			}
		}
		System.out.println(SUM);
	}

	private static void hosuck(StringTokenizer st) {
		String key = st.nextToken();
		int n = Integer.parseInt(st.nextToken());

		while (infosMap.containsKey(key) && !infosMap.get(key).isEmpty() && n-- > 0)
			SUM += infosMap.get(key).poll();
	}

	private static void gorilla(StringTokenizer st) {
		String key = st.nextToken();
		if (!infosMap.containsKey(key))
			infosMap.put(key, new PriorityQueue<>(Comparator.comparingInt(k -> -k)));
		int n = Integer.parseInt(st.nextToken());
		while (n-- > 0) infosMap.get(key).add(Integer.parseInt(st.nextToken()));
	}
}
