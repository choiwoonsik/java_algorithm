package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
		연필을 내리면 선긋기 - 올리면 긋지 않는다
		시작 : (0,0)  보는 방향 : y축이 증가하는 방향  연필은 내리고있다
		FD x : x만큼 전진
		LT a : 반시계 방향으로 a도 회전
		RT a : 시계 방향으로 a도 회전
		PU : 연필 업
		PD : 연필 다운

		축에 평행한 직사각형 N개가 주어졌을 때, 이 직사각형을 그리는데 필요한
		PU 명령어의 최소값을 구하라
		 */

public class 로고_3108 {
	static int[][] map = new int[1001][1001]; // 더하기 500;
	static int[] parents;
	static Dot[] dotArr;
	static int N;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		dotArr = new Dot[N+1];
		parents = new int[N+1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());

			Dot dot = new Dot(y1, x1, y2, x2);
			dotArr[i] = dot;
			parents[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = i+1; j <= N; j++) {
				// 선분에 겹치면 find 체크 후 유니온
				if (line_check(dotArr[i], dotArr[j])) {
					if (find(i) != find(j))
						union(i, j);
				}
			}
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == i)
				cnt++;
		}
		System.out.println(cnt);
	}

	private static void union(int i, int j) {
		parents[parents[i]] = parents[j];
	}

	private static int find(int p) {
		if (parents[p] == p)
			return p;
		return parents[p] = find(parents[p]);
	}

	private static boolean line_check(Dot dotA, Dot dotB) {
		// dotB의 lu or rd 좌표의 y, x가 dotA의 y1, y2사이 && x1, x2사이에 있으면서

		// 하나는 안에, 하나는 밖에 있는 경우
		if ((dotB.y1 > dotA.y1 && dotB.y1 < dotA.y2) && (dotB.x1 > dotA.x1 && dotB.x1 < dotA.x2)) {
			if (dotB.x2 >= dotA.x2 || dotB.y2 >= dotA.y2) {
				System.out.println("case 1 > " + dotA.y1 + ", " + dotA.x1 + " : " + dotB.y2 + " , " + dotB.x2);
				return true;
			}
		}
		// 높이가 같은 경우
		else if ((dotB.y1 == dotA.y1 || dotB.y1 == dotA.y2) && (dotB.x1 >= dotA.x1 && dotB.x1 <= dotA.x2)) {
			System.out.println("case 2 > " + dotA.y1 + ", " + dotA.x1 + " : " + dotB.y2 + " , " + dotB.x2);
			return true;
		}
		// 열이 같은 경우
		else if ((dotB.x1 == dotA.x1 || dotB.x1 == dotA.x2) && (dotB.y1 >= dotA.y1 && dotB.y1 <= dotA.y2)) {
			System.out.println("case 3 > " + dotA.y1 + ", " + dotA.x1 + " : " + dotB.y2 + " , " + dotB.x2);
			return true;
		}

		return false;
	}

	private static class Dot
	{
		int y1;
		int x1;
		int y2;
		int x2;

		public Dot(int y1, int x1, int y2, int x2) {
			this.y1 = y1;
			this.x1 = x1;
			this.y2 = y2;
			this.x2 = x2;
		}
	}
}
