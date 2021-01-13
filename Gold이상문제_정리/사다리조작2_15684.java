package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작2_15684 {
	static int W, M, H;
	static int MIN = Integer.MAX_VALUE;
	static boolean[][] ladder;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new boolean[H+2][W+2];

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			ladder[y][x] = true; // 현재 다리가 있으면 오른쪽으로 간다
		}

		for (int i = 0; i <= 3; i++)
		{
			if(solve(1, 0, 0, i)) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}

	private static boolean check(int n)
	{
		int x = n;
		for (int i = 1; i <= H; i++) {
			if (x > 1 && ladder[i][x - 1])
				x--;
			else if (x < W && ladder[i][x])
				x++;
		}
		return x == n;
	}

	private static boolean checkAll()
	{
		for (int i = 1; i <= W; i++)
		{
			if (!check(i))
				return false;
		}
		return true;
	}

	private static boolean solve(int y, int x, int selected, int N) {
		if (selected == N)
			return checkAll();

		if (x < W)
			x++;
		else if (y < H) {
			y++;
			x = 1;
		}
		else
			return false;

		boolean ret = false;
		if (x < W && !ladder[y][x-1] && !ladder[y][x] && !ladder[y][x+1])
		{
			ladder[y][x] = true;
			ret = solve(y, x, selected+1, N);
			ladder[y][x] = false;
		}
		return ret || solve(y, x, selected, N);
	}
}
