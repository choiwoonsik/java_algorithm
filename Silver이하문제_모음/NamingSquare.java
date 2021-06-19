package Silver이하문제_모음;

import java.util.*;
import java.io.*;

public class NamingSquare {

	static int		ground_size;
	static int  	count_tile;
	static int  	count_danji;
	static int[]    x_dir = {0, 0, 1, -1};
	static int[]	y_dir = {-1, 1, 0, 0};
	static int[][] 	ground;

	public static void main(String args[]) throws IOException {

		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();

		count_danji = 0;
		ground_size = atoi(sc.next());
		ground = new int[ground_size][ground_size];
		for (int i = 0; i < ground_size; i++)
		{
			String str = sc.next();
			for(int j = 0; j < ground_size; j++)
			{
				ground[i][j] = str.charAt(j) - '0';
			}
		}
		for (int i = 0; i < ground_size; i++)
		{
			for(int j = 0; j < ground_size; j++)
			{
				if (ground[i][j] == 1)
				{
					count_tile = 0;
					count_danji++;
					find_tile(i, j);
					list.add(count_tile);
				}
			}
		}
		Collections.sort(list);
		System.out.println("all danji" + count_danji);
		
		for (int n : list)
			System.out.println("count>>" + n);
	}
		static int atoi(String c)
		{
			return (Integer.parseInt(c));
		}

		static void find_tile (int x, int y)
		{
			ground[x][y] = 0;
			count_tile++;
			for (int i = 0; i < 4; i++) {
				int new_x = x + x_dir[i];
				int	new_y = y + y_dir[i];
				if (new_x >= 0 && new_y >= 0 && new_x < ground_size && new_y < ground_size)
				{
					if (ground[new_x][new_y] == 1)
						find_tile(new_x, new_y);
				}
			}
		}
}