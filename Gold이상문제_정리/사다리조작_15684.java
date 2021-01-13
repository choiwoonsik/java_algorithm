package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 사다리조작_15684 {
	static boolean[][] ladders;
	static int X, M, Y;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		X = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		ladders = new boolean[Y+2][X+2];

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			ladders[y][x] = true;
		}

		count = 4;
		solve(1, 1, 0);
		if (count > 3)
			count = -1;
		System.out.println(count);
	}

	private static void solve(int y, int x, int selected) {
		if (selected >= count)
			return;
		if (checkAll()) {
			count = selected;
			return;
		}
		if (selected == 3)
			return;

		for (int i = y; i <= Y; i++) {
			for (int j = x; j <= X; j++) {
				if (!ladders[i][j - 1] && !ladders[i][j] && !ladders[i][j+1]) {
					ladders[i][j] = true;
					solve(i, j, selected+1);
					ladders[i][j] = false;
				}
			}
			x = 1;
		}
	}

	private static boolean check(int n)
	{
		int x = n;
		for (int y = 1; y <= Y; y++)
		{
			if (x > 1 && ladders[y][x-1])
				x--;
			else if (x < X && ladders[y][x])
				x++;
		}
		return n == x;
	}

	private static boolean checkAll()
	{
		for (int x = 1; x <= X; x++)
		{
			if (!check(x))
				return false;
		}
		return true;
	}
}
