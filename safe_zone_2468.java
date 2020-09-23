import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class safe_zone_2468 {
	static int N;
	static int Top;
	static int max = 0;
	static int part = 0;
	static int[] p_x = {0, 0, -1, 1};
	static int[] p_y = {-1, 1, 0, 0};
	static int[][] map;
	static boolean[][] visited;
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int n[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = n[0];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < N; j++) {
				if(map[i][j] > Top)
					Top = map[i][j];
			}
		}
		for (int i = 0; i < Top; i++) {
			visited = new boolean[N][N];
			if (calc_safe(i) > max) {
				visited = new boolean[N][N];
				max = calc_safe(i);
			}
		}
		System.out.println(max);
	}
	private static int calc_safe(int rain)
	{
		part = 0;
		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) {
				if (map[i][j] > rain && !visited[i][j]) {
					part++;
					DFS(new Dot(i, j), rain);
				}
			}
		}
		return part;
	}
	private static void DFS(Dot dot, int rain){
		visited[dot.x][dot.y] = true;

		for (int i = 0; i < 4; i++)
		{
			int x = dot.x + p_x[i];
			int y = dot.y + p_y[i];
			if (x >= 0 && y >= 0 && x < N && y < N) {
				if (map[x][y] > rain && !visited[x][y]){
					DFS(new Dot(x, y), rain);
				}
			}
		}
	}
}
