package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 도서관_1461 {
	static int N, M;
	static PriorityQueue<Integer> pqLeft = new PriorityQueue<>();
	static PriorityQueue<Integer> pqRight = new PriorityQueue<>(Comparator.comparingInt(s -> -s));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int last = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int pos = Integer.parseInt(st.nextToken());
			if (pos < 0)
				pqLeft.add(pos);
			else
				pqRight.add(pos);
		}

		boolean flag = false;
		if (!pqLeft.isEmpty() && !pqRight.isEmpty()) {
			if (pqLeft.peek() + pqRight.peek() <= 0)
				flag = true;
		}

		if (flag || pqRight.isEmpty()) {
			last = Math.abs(pqLeft.peek());
			int cnt = 0;
			while (!pqLeft.isEmpty() && cnt++ < M)
				pqLeft.poll();
		}
		else {
			last = pqRight.peek();
			int cnt = 0;
			while (!pqRight.isEmpty() && cnt++ < M)
				pqRight.poll();
		}

		int dist = 0;
		while (!pqLeft.isEmpty() || !pqRight.isEmpty()) {
			int turn = 0;
			int d = 0;
			while (!pqLeft.isEmpty() && turn++ < M)
				d = Math.min(d, pqLeft.poll());
			dist += Math.abs(d * 2);

			d = 0;
			turn = 0;
			while (!pqRight.isEmpty() && turn++ < M)
				d = Math.max(d, pqRight.poll());
			dist += Math.abs(d * 2);
		}
		dist += last;
		System.out.println(dist);
	}
}
