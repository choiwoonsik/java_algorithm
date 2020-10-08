import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class organic_baechu_1012 {
	static int Y;
	static int X;
	static int N;
	static int T;
	static int Count;
	static int[] p_x = {0, 0, -1, 1};
	static int[] p_y = {1, -1, 0, 0};
	static int[][] map;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(bf.readLine());
		while (T-- > 0)
		{
			Count = 0;
			make_map();
			System.out.println(Count);
		}
	}
	static void make_map() throws IOException {
		String[] first = bf.readLine().split(" ");
		int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Y = arr[0];
		X = arr[1];
		N = arr[2];
		map = new int[X][Y];

		while (N-- > 0)
		{
			String[] pos = bf.readLine().split(" ");
			int x = Integer.parseInt(pos[0]);
			int y = Integer.parseInt(pos[1]);
			Dot dot = new Dot(y, x);
			map[dot.x][dot.y] = 1;
		}
		for (int i = 0; i < X; i++){
			for(int j = 0; j < Y; j++){
				Dot dot = new Dot(i, j);
				if (map[dot.x][dot.y] == 1) {
					map[dot.x][dot.y] = 0;
					Count++;
					DFS(dot);
				}
			}
		}
	}
	static void DFS(Dot dot)
	{
		for (int i = 0; i < 4; i++) {
			int x = dot.x + p_x[i];
			int y = dot.y + p_y[i];
			if (x >= 0 && y >= 0 && x < X && y < Y) {
				if (map[x][y] == 1) {
					map[x][y] = 0;
					DFS(new Dot(x, y));
				}
			}
		}
	}
}
