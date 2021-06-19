package Silver이하문제_모음;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class calc_square_2583 {
	static boolean[][] map;
	static int		height;
	static int		width;
	static int		n_box;
	static int		count;
	static int[]	check_x = {1, -1, 0, 0};
	static int[]	check_y = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		int part = 0;

		height = sc.nextInt();
		width = sc.nextInt();
		n_box = sc.nextInt();
		map = new boolean[height + 1][width + 1];

		for (int i = 0; i < n_box; i++) {
			int st_x = width - sc.nextInt();
			int st_y = height - sc.nextInt();
			int ed_x = width - sc.nextInt();
			int ed_y = height - sc.nextInt();
			map = make_box(st_x, st_y, ed_x, ed_y);
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (!map[i][j]) {
					part++;
					count = 1;
					find_Nbox(i, j);
					list.add(count);
				}
			}
		}
		System.out.println(part);

		Collections.sort(list);
		for (int n : list)
			System.out.print(n + " ");
	}

	static boolean[][] 	make_box(int st_x, int st_y, int ed_x, int ed_y)
	{
		for (int y = ed_y; y < st_y; y++)
		{
			for (int x = ed_x; x < st_x; x++)
			{
				map[y][x] = true;
			}
		}
		return map;
	}
	static void		find_Nbox(int y, int x)
	{
		map[y][x] = true;
		for (int i = 0; i < 4; i++)
		{
			int px = x + check_x[i];
			int py = y + check_y[i];

			if (px < width && py < height && px >= 0 && py >= 0) {
				if (!map[py][px]) {
					count++;
					find_Nbox(py, px);
				}
			}
		}
	}
}
