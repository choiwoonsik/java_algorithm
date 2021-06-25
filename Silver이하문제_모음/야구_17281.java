package Silver이하문제_모음;

import java.io.*;
import java.util.*;

/*
2
4 0 0 0 1 1 1 0 0
0 0 0 0 0 0 0 0 0
 */

public class 야구_17281 {
	static int ining;
	static ArrayList<int[]> permList = new ArrayList<>();
	static int nowPlayer = 0;
	static int[][] pointArr;
	static int[] Base = new int[4];
	static int outCount = 0;
	static int MaxPoint = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		ining = Integer.parseInt(st.nextToken());
		pointArr = new int[ining][9];

		for (int i = 0; i < ining; i++) {

			st = new StringTokenizer(br.readLine());
			for (int pl = 0; pl < 9; pl++)
				pointArr[i][pl] = Integer.parseInt(st.nextToken());

		}

		boolean[] visited = new boolean[9];
		int[] player = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
		int[] perm_player = new int[9];

		Arrays.fill(perm_player, 0);
		Arrays.fill(visited, false);

		make_perm(0, perm_player, player, visited);

		// 순서 하나씩 테스트
		for (int[] playerArr : permList) {
			outCount = 0;
			nowPlayer = 0;
			Arrays.fill(Base, 0);
			MaxPoint = Math.max(MaxPoint, play(playerArr));
		}
		System.out.println(MaxPoint);
	}

	private static int play(int[] playerArr) {
		int total_point = 0;
		// 한 이닝 게임 시작
		for (int i = 0; i < ining; i++)
		{
			// 플레이어 출발
			while (outCount != 3)
			{
				int playerNum = playerArr[nowPlayer++];
				int point = pointArr[i][playerNum];

				nowPlayer %= 9;

				total_point += move(point);
			}
			outCount = 0;
			Arrays.fill(Base, 0);
		}
		return total_point;
	}

	private static int move(int point) {
		int getPoint = 0;
		if (point == 1) {
			if (Base[3] == 1) getPoint++;
			Base[3] = Base[2];
			Base[2] = Base[1];
			Base[1] = 1;
		}
		else if (point == 2) {
			if (Base[3] == 1) getPoint++;
			if (Base[2] == 1) getPoint++;
			Base[3] = Base[1];
			Base[2] = 1;
			Base[1] = 0;
		}
		else if (point == 3) {
			if (Base[3] == 1) getPoint++;
			if (Base[2] == 1) getPoint++;
			if (Base[1] == 1) getPoint++;
			Base[3] = 1;
			Base[2] = 0;
			Base[1] = 0;
		}
		else if (point == 4) {
			if (Base[3] == 1) getPoint++;
			if (Base[2] == 1) getPoint++;
			if (Base[1] == 1) getPoint++;
			Base[3] = 0;
			Base[2] = 0;
			Base[1] = 0;
			getPoint++;
		}
		else outCount++;

		return getPoint;
	}

	private static void make_perm(int start, int[] perm, int[] player, boolean[] visited) {

		if (start == 9 && perm[3] == 0) {
			int[] tmp = new int[9];
			System.arraycopy(perm, 0, tmp, 0, 9);
			permList.add(tmp);
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!visited[i])
			{
				visited[i] = true;
				perm[start] = player[i];
				make_perm(start + 1, perm, player, visited);
				visited[i] = false;
			}
		}
	}
}
