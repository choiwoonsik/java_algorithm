package 자료구조;

import java.io.*;
import java.util.*;
/*
10 10 100
9 9 1 9 9 1 9 9 8 2
 */
public class 트럭_13335 {
	static int N;
	static int W;
	static int L;
	static Queue<Integer> waitingQ = new LinkedList<>();
	static Queue<Integer> bridgeQ = new LinkedList<>();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int bus = Integer.parseInt(st.nextToken());
			waitingQ.add(bus);
		}

		for (int i = 0; i < W; i++) {
			bridgeQ.add(0);
		}

		int total = 0;
		int allCount = 0;
		while (!waitingQ.isEmpty()) {
			int curBus = waitingQ.peek();

			if (bridgeQ.size() < W) {
				if (total + curBus <= L) {
					total += curBus;
					waitingQ.poll();
					bridgeQ.add(curBus);
				} else {
					bridgeQ.add(0);
				}
			} else {
				int outBus = bridgeQ.poll();
				total -= outBus;
				allCount++;
			}
		}

		while (!bridgeQ.isEmpty()) {
			bridgeQ.poll();
			allCount++;
		}

		System.out.print(allCount);
	}
}
