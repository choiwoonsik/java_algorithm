package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class laboratory_14502 {
	static ArrayList<Dot> list = new ArrayList<>();
	static int 		X;
	static int 		Y;
	static int		maxN = 0;
	static int		count = 0;
	static int[]	pos_x = {1, -1, 0, 0};
	static int[]	pos_y = {0, 0, -1, 1};
	static int[][] 	Map;
	static int[][]	tmp_Map;
	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int size[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		X = size[0];
		Y = size[1];
		Map = new int[X][Y];
		tmp_Map = new int[X][Y];
		for (int i = 0; i < X; i++) {
			Map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < Y; j++) {
				if (Map[i][j] == 2)
					list.add(new Dot(i, j));
			}
		}
		make_wall(0, 0);
		System.out.println(maxN);
	}

	static void make_wall(int start, int depth)
	{
		if (depth == 3)
		{
			for (int i = 0; i < X; i++) {
				for (int j = 0; j < Y; j++) {
					tmp_Map[i][j] = Map[i][j];
				}
			}
			for (Dot dot : list)
				fill_birus(dot.x, dot.y);
			if (count_noirus() > maxN)
				maxN = count_noirus();
			return ;
		}

		for (int i = start; i < X * Y; i++) {
			int x = i / Y;
			int y = i % Y;

			if (Map[x][y] == 0) {
				Map[x][y] = 1;
				make_wall(i + 1, depth + 1);
				Map[x][y] = 0;
			}
		}
	}

	static void	fill_birus(int x, int y)
	{
		for (int i = 0; i < 4; i++)
		{
			int nx = x + pos_x[i];
			int	ny = y + pos_y[i];

			if (nx < X && ny < Y && nx >= 0 && ny >= 0) {
				if (tmp_Map[nx][ny] == 0) {
					tmp_Map[nx][ny] = 2;
					fill_birus(nx, ny);
				}
			}
		}
	}

	static int count_noirus()
	{
		count = 0;
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (tmp_Map[i][j] == 0) {
					count++;
				}
			}
		}
		return (count);
	}
}
