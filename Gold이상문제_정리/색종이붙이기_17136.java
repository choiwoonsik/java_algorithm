package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 색종이붙이기_17136 {
	static int[][] map;
	static int[] paper = new int[6];
	static int used = 987654321;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Arrays.fill(paper, 5);
		map = new int[10][10];
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(new Dot(0, 0), 0);
		if (used == 987654321)
			used = -1;
		System.out.println(used);
	}

	private static void dfs(Dot dot, int count)
	{
		// 끝에 도달하면
		if (dot.y >= 9 && dot.x > 9) {
			used = Math.min(used, count);
			return;
		}

		// 조건에 안맞으면
		if (used <= count) return;

		// 다음 행
		if (dot.x > 9) {
			dfs(new Dot(dot.y + 1, 0), count);
			return;
		}

		// 색종이있으면
		if (map[dot.y][dot.x] == 1) {
			// 5가지의 색종이에 대해서
			for (int size = 5; size >= 1; size--) {
				// 아직 종이가 있고, 붙일수 있다면
				if (paper[size] > 0 && canAttach(dot, size)) {
					// 붙여보고, 색종이 개수 줄여보고, 남은 칸수 줄인후 다음거 체크
					attach(dot, size, 2);
					paper[size]--;
					dfs(new Dot(dot.y, dot.x + 1), count+1);
					// 다시 돌아오면 원상복구후 진행
					attach(dot, size, 1);
					paper[size]++;
				}
			}
		}// 없으면 다음 칸
		else
			dfs(new Dot(dot.y, dot.x+1), count);
	}

	private static void attach(Dot dot, int size, int k) {
		for (int i = dot.y; i < dot.y + size; i++) {
			for (int j = dot.x; j < dot.x + size; j++) {
				map[i][j] = k;
			}
		}
	}

	private static boolean canAttach(Dot dot, int size) {
		// 문제있는 경우
		for (int i = dot.y; i < dot.y + size; i++) {
			for (int j = dot.x; j < dot.x + size; j++) {
				if (i >= 10 || j >= 10)
					return false;
				if (map[i][j] != 1) {
					return false;
				}
			}
		}
		// 문제없다면
		return true;
	}

	private static class Dot
	{
		int y;
		int x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
