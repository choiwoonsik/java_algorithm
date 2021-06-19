package Silver이하문제_모음;

import java.util.*;

public class search_miro_2178 {
	static int height;
	static int width;
	static int[][] map;
	static boolean[][] visited;
	static int[]	plus_x = {1, -1, 0, 0};
	static int[] 	plus_y = {0, 0, 1, -1};
	public static void main(String[] args)
	{
		//bfs문제
		//링크드 리스트를 이용하여 갈 수있는 모든 길을 add
		//큐에서 하나씩 뽑아서 갈수있는길 add -> 큐 pop -> 큐가 빌때까지 반복 or 목적지 도달할 때 까지 반복
		Scanner sc = new Scanner(System.in);
		height = sc.nextInt();	//4
		width = sc.nextInt();	//6
		map = new int[height][width];
		visited = new boolean[height][width];

		for (int i = 0; i < height; i++)
		{
			String s = sc.next();
			for (int j = 0; j < width; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		visited[0][0] = true;
		BFS();
		System.out.println(map[height-1][width-1]);
	}

	static void BFS()
	{
		Queue<Dot> que = new LinkedList<>();
		que.add(new Dot(0,0));
		while (!que.isEmpty()) {
			Dot now = que.poll();
			for (int i = 0; i < 4; i++) {
				int pox = now.x;
				int poy = now.y;
				pox += plus_x[i];
				poy += plus_y[i];
				if (pox >= 0 && poy >= 0 && pox < height && poy < width) {
					if (map[pox][poy] == 1 && !visited[pox][poy]) {
						visited[pox][poy] = true;
						que.add(new Dot(pox, poy));
						map[pox][poy] = map[now.x][now.y] + 1;
					}
				}
			}
		}
	}
}
