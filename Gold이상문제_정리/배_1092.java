package Gold이상문제_정리;

import java.util.*;
import java.io.*;
/*
3
6 8 9
6
2 5 2 4 7 8

3
1 2 3
6
2 2 2 2 2 2

3
6 8 9
9
1 2 3 4 5 6 7 8 9
 */
public class 배_1092 {
	static int craneCnt, boxCnt, time;
	static Integer[] crane;
	static ArrayList<Integer> box = new ArrayList<>();
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		craneCnt = Integer.parseInt(br.readLine());
		crane = new Integer[craneCnt];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < craneCnt; i++)
			crane[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(crane, Collections.reverseOrder());

		boxCnt = Integer.parseInt(br.readLine());
		visited = new boolean[boxCnt];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < boxCnt; i++)
			box.add(Integer.parseInt(st.nextToken()));
		box.sort(Collections.reverseOrder());

		if (box.get(0) > crane[0])
			System.out.println("-1");
		else{
			calc();
			System.out.println(time);
		}
	}

	private static void calc() {
		while (box.size() != 0)
		{
			int box_idx = 0;
			int c = 0;
			while(c < crane.length) {
				if (box_idx == box.size()) break;
				else if (box.get(box_idx) <= crane[c]) {
					box.remove(box_idx);
					c++;
				} else if (box.get(box_idx) > crane[c]) {
					box_idx++;
				}
			}
			time++;
		}
	}
}
