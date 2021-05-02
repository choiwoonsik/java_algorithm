package Gold이상문제_정리;
/*
 N×N인 체스판에서 진행되고, 사용하는 말의 개수는 K개
 말은 원판모양이고, 하나의 말 위에 다른 말을 올릴 수 있다.
 체스판의 각 칸은 흰색, 빨간색, 파란색 중 하나

 A번 말이 이동하려는 칸이
	- 흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
	A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
	예를 들어, A, B, C로 쌓여있고, 이동하려는 칸에 D, E가 있는 경우에는 A번 말이 이동한 후에는 D, E, A, B, C가 된다.

	- 빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
	A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
	A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.

	- 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다.
	방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.

	- 체스판을 벗어나는 경우에는 파란색과 같은 경우이다. (방향을 바꿈)

첫째 줄에 체스판의 크기 N, 말의 개수 K가 주어진다.
둘째 줄부터 N개의 줄에 체스판의 정보가 주어진다.
- 체스판의 정보는 정수로 이루어져 있고, 각 정수는 칸의 색을 의미한다. 0은 흰색, 1은 빨간색, 2는 파란색이다.

다음 K개의 줄에 말의 정보가 1번 말부터 순서대로 주어진다.
 - 말의 정보는 세 개의 정수로 이루어져 있고,
 - 순서대로 행, 열의 번호, 이동 방향이다.
 행과 열의 번호는 1부터 시작하고, 이동 방향은 4보다 작거나 같은 자연수이고 1부터 순서대로 →, ←, ↑, ↓의 의미를 갖는다.

 게임이 종료되는 턴의 번호를 출력한다. 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.
 */
import java.io.*;
import java.util.*;

public class 새로운게임_17837 {
	static Stack<Dot>[][] table = new Stack[13][13];
	static HashMap<Integer, Dot> player_map = new HashMap<>();
	static int[][] color_table = new int[13][13];
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				table[i][j] = new Stack<>();
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				color_table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			Dot p = new Dot(y, x, dir, i);
			player_map.put(i, p);
			table[y][x].push(p);
		}

		int answer = solution();
		System.out.println(answer);
	}

	private static int solution() {
		int turn = 0;
		boolean flag = true;
		while (turn++ < 1000) {
			for (int i = 0; i < K; i++) {
				player_turn(i);
				if (check_end(i)) {
					flag = false;
					break;
				}
			}
			if (!flag) break;
		}
		if (turn > 1000) return -1;
		return turn;
	}

	private static boolean check_end(int p) {
		Dot player = player_map.get(p);
		int cy = player.y;
		int cx = player.x;
		return table[cy][cx].size() >= 4;
	}

	private static void player_turn(int player_num)
	{
		Dot now_player = player_map.get(player_num);
		int dir = now_player.dir;
		int cy = now_player.y;
		int cx = now_player.x;
		int ny = dy[dir] + now_player.y;
		int nx = dx[dir] + now_player.x;

		if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
			now_player.dir = reverse_dir(now_player.dir);
			ny = dy[now_player.dir] + cy;
			nx = dx[now_player.dir] + cx;
			player_map.put(now_player.num, now_player);
			if (color_table[ny][nx] == 0)
				case_white(ny, nx, cy, cx, now_player);
			else if (color_table[ny][nx] == 1)
				case_red(ny, nx, cy, cx, now_player);
		}
		else if (color_table[ny][nx] == 0)
			case_white(ny, nx, cy, cx, now_player);
		else if (color_table[ny][nx] == 1)
			case_red(ny, nx, cy, cx, now_player);
		else if (color_table[ny][nx] == 2)
			case_blue(cy, cx, now_player);
	}

	private static void case_blue(int cy, int cx, Dot now_player) {
		now_player.dir = reverse_dir(now_player.dir);
		player_map.put(now_player.num, now_player);
		int ny = dy[now_player.dir] + cy;
		int nx = dx[now_player.dir] + cx;
		if (ny < 0 || nx < 0 || ny >= N || nx >= N) return;
		if (color_table[ny][nx] == 0)
			case_white(ny, nx, cy, cx, now_player);
		else if (color_table[ny][nx] == 1)
			case_red(ny, nx, cy, cx, now_player);
	}

	private static void case_red(int ny, int nx, int cy, int cx, Dot now_player) {
		int pos = table[cy][cx].search(now_player);
		if (pos == 1) {
			table[ny][nx].push(table[cy][cx].pop());
			now_player.y = ny;
			now_player.x = nx;
			player_map.put(now_player.num, now_player);
		}
		else { // 순서 뒤집어서 넣기
			for (int i = 0; i < pos; i++) {
				Dot player = table[cy][cx].pop();
				player.y = ny;
				player.x = nx;
				player_map.put(player.num, player);
				table[ny][nx].push(player);
			}
		}
	}

	private static void case_white(int ny, int nx, int cy, int cx, Dot now_player) {
		ArrayList<Dot> order_list = new ArrayList<>();
		int pos = table[cy][cx].search(now_player);
		if (pos == 1) {
			table[ny][nx].push(table[cy][cx].pop());
			now_player.y = ny;
			now_player.x = nx;
			player_map.put(now_player.num, now_player);
		}
		else { // 순서 유지해서 넣기
			for (int i = 0; i < pos; i++)
				order_list.add(table[cy][cx].pop());
			for (int i = order_list.size() - 1; i >= 0; i--) {
				Dot player = order_list.get(i);
				player.y = ny;
				player.x = nx;
				player_map.put(player.num, player);
				table[ny][nx].push(player);
			}
		}
	}

	private static int reverse_dir(int dir) {
		if (dir == 0) dir = 1;
		else if (dir == 1) dir = 0;
		else if (dir == 2) dir = 3;
		else if (dir == 3) dir = 2;
		return dir;
	}

	private static class Dot
	{
		int y, x, dir, num;
		Dot (int y, int x, int dir, int num) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.num = num;
		}
	}
}
