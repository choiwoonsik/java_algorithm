package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 얼음미로_Dijkstra_20926 {
	static final int Hol = -1, Rock = 100, Exit = 200, T = 300;
	static int W, H, Min = Integer.MAX_VALUE;
	static int[][] board;
	static int[] dp;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static ArrayList<Node>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		W = Integer.parseInt(input[0]);
		H = Integer.parseInt(input[1]);
		adj = new ArrayList[W * H];
		for (int i = 0; i < W * H; i++) {
			adj[i] = new ArrayList<>();
		}

		int start = 0;
		int end = 0;
		dp = new int[H * W];
		Arrays.fill(dp, Integer.MAX_VALUE);
		board = new int[H][W];
		for (int i = 0; i < H; i++) {
			String l = br.readLine();
			for (int j = 0; j < W; j++) {
				char c = l.charAt(j);
				if (c >= '1' && c <= '9') board[i][j] = c - '0';
				else if (c == 'T') {
					board[i][j] = T;
					start = i * W + j;
				}
				else if (c == 'E') {
					board[i][j] = Exit;
					end = i * W + j;
				}
				else if (c == 'H') board[i][j] = Hol;
				else if (c == 'R') board[i][j] = Rock;
			}
		}

		makeGraph();
		dijkstra(start);

		for (int d : dp) System.out.print(d+" ");
		System.out.println();
		System.out.println(dp[end]);
	}

	private static void dijkstra(int s) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
		pq.add(new Node(s, 0));
		dp[s] = 0;

		while (!pq.isEmpty())
		{
			Node now = pq.poll();
			System.out.println(now.now);

			for (Node next : adj[now.now]) {
				if (dp[next.now] > dp[now.now] + next.cost) {
					dp[next.now] = dp[now.now] + next.cost;
					pq.add(new Node(next.now, dp[next.now]));
				}
			}
		}
	}

	private static void makeGraph() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (board[i][j] == Rock)
				{
					int u;
					int v;
					for (int d = 0; d < 4; d++) {
						if (isOut(i + dy[d], j + dx[d])) continue;
						u = (i + dy[d]) * W + (j + dx[d]);
						int ni = i;
						int nj = j;
						int time = 0;
						boolean flag = false;
						while (true) {
							ni += dy[d];
							nj += dx[d];
							System.out.println(ni + ", " + nj);
							if (isOut(ni, nj)) {
								flag = true;
								time -= board[i + dy[d]][j + dx[d]];
								ni -= dy[d];
								nj -= dx[d];
								System.out.println(ni + ", " + nj);
								break;
							}
							if (board[ni][nj] == Hol) break;
							if (board[ni][nj] == T) {
//								time -= board[i + dy[d]][j + dx[d]];
								flag = true;
								break;
							}
							if (board[ni][nj] == Rock) {
								time -= board[i + dy[d]][j + dx[d]];
								flag = true;
								ni -= dy[d];
								nj -= dx[d];
								break;
							}
							if (board[ni][nj] == Exit) {
								time -= board[i + dy[d]][j + dx[d]];
								flag = true;
								break;
							}
							time += board[ni][nj];
						}

						if (flag) {
							v = ni * W + nj;
							adj[u].add(new Node(v, time));
							adj[v].add(new Node(u, time));
							System.out.println("u+\" , \"+ v = " + u+" , "+ v +" t : "+ time);
						}
					}
				}
			}
		}
	}

	private static boolean isOut(int ny, int nx) {
		return ny < 0 || nx < 0 || ny >= H || nx >= W;
	}

	private static class Dot
	{
		int y, x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static class Node
	{
		int now;
		int cost;

		public Node(int now, int cost) {
			this.now = now;
			this.cost = cost;
		}
	}
}
