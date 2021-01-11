package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class CountCircleGroup_10216 {
	static int[] parents;
	static Dot[] spotArr;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder str = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		while (T-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			// 부모 초기화, 좌표 배열 초기화
			parents = new int[N + 1];
			spotArr = new Dot[N + 1];
			for (int i = 1; i <= N; i++)
				parents[i] = i;

			for (int i = 1; i <= N; i++)
			{
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				Dot dot = new Dot(y, x, r);
				spotArr[i] = dot;
			}

			for (int i = 1; i <= N; i++) {
				for (int j = i + 1; j <= N; j++) {
					if (find(i) != find(j)) {
						if (dist(spotArr[i], spotArr[j]) <= spotArr[i].r + spotArr[j].r)
							// 같거나 겹친다면 합치기
							union(i, j);
					}
				}
			}

			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (parents[i] == i)
					cnt++;
			}
			str.append(cnt).append("\n");
		}
		System.out.print(str);
	}

	private static void union(int dotA, int dotB) {
		parents[parents[dotB]] = parents[dotA];
	}

	private static int find(int p) {
		if (parents[p] == p)
			return parents[p];
		return parents[p] = find(parents[p]);
	}

	private static double dist (Dot dotA, Dot dotB)
	{
		double dis = Math.pow(Math.abs(dotA.y - dotB.y), 2) + Math.pow(Math.abs(dotA.x - dotB.x), 2);
		return Math.sqrt(dis);
	}

	private static class Dot
	{
		int y;
		int x;
		int r;

		public Dot(int y, int x, int r) {
			this.y = y;
			this.x = x;
			this.r = r;
		}
	}
}
