import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class search_miro2_2178 {
	static int X;
	static int Y;
	static int[] plus_x = {1, -1, 0, 0};
	static int[] plus_y = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	public static void main()
	{
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		Y = sc.nextInt();
		map = new int[X][Y];
		visited = new boolean[X][Y];

		for (int i = 0; i < X; i++)
		{
			String s = sc.next();
			for (int j = 0; j < Y; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		visited[0][0] = true;
		BFS();
		System.out.println(map[X][Y]);
	}
	static void BFS()
	{
		Queue<Dot> que = new LinkedList<>();
		que.add(new Dot(0, 0));

		while (!que.isEmpty())
		{
			Dot now = que.poll();
			for (int i = 0; i < 4; i++) {
				int pos_x = now.x + plus_x[i];
				int pos_y = now.y + plus_y[i];
				if (pos_x >= 0 && pos_y >= 0 && pos_x < X && pos_y < Y) {
					if (map[pos_x][pos_y] == 1 && !visited[pos_x][pos_y]) {
						map[pos_x][pos_y] = map[now.x][now.y] + 1;
						visited[pos_x][pos_y] = true;
					}
				}
			}
		}
	}
}
