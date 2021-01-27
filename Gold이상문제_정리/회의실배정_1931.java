package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 회의실배정_1931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.end < o2.end)
				return -1;
			else if (o1.end > o2.end)
				return 1;
			else
				return Integer.compare(o1.start, o2.start);
		});

		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.add(new Node(start, end));
		}

		int last_end = 0;
		int count = 0;
		while (!pq.isEmpty())
		{
			Node meet = pq.poll();
			// 시작이 끝이상이라면
			if (meet.start >= last_end) {
				last_end = meet.end;
				count++;
			}
		}
		System.out.println(count);
	}

	private static class Node
	{
		int start;
		int end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
