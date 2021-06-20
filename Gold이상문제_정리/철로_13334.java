package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
8
5 40
35 25
10 20
10 25
30 50
50 60
30 25
80 100
30
hi와 oi는 −100,000,000이상, 100,000,000이하의 서로 다른 정수
 */

public class 철로_13334 {
	static int Left, Right, N, D, MAX, Cnt;
	static Line[] lines;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		lines = new Line[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			if (l > r)
			{
				int tmp = r;
				r = l;
				l = tmp;
			}
			lines[i] = new Line(l, r);
		}
		D = Integer.parseInt(br.readLine());
		Arrays.sort(lines, Comparator.comparingInt(l -> l.right));

		for (int i = 0; i < N; i++) {
			pq.add(lines[i].left);

			while (!pq.isEmpty() && pq.peek() < lines[i].right - D)
				pq.poll();

			MAX = Math.max(MAX, pq.size());
		}

		System.out.println(MAX);
	}
	private static class Line {
		int left, right;

		public Line(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}
