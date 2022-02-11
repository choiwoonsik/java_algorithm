package 자료구조;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class 최솟값찾기_11003 {
	static int N;
	static int L;
	static int left;
	static int right;
	static int[] num;
	static ArrayDeque<Integer> deque = new ArrayDeque<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(N -> N.num));

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		num = new int[N];

		left = 0;
		right = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());

			while (!deque.isEmpty() && num[deque.getLast()] > num[i]) {
				deque.removeLast();
			}

			deque.addLast(i);

			if (!deque.isEmpty() && deque.getFirst() <= i - L) {
				deque.removeFirst();
			}

			bw.write(num[deque.getFirst()] + "\n");
		}

		bw.flush();
		bw.close();
	}

	private static class Node {
		int index;
		int num;

		public Node(int index, int num) {
			this.index = index;
			this.num = num;
		}
	}
}
