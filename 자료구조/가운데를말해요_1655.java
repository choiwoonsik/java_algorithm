package 자료구조;

import java.io.*;
import java.util.*;

public class 가운데를말해요_1655 {
	static int N;
	static StringBuilder answer = new StringBuilder();
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());

			int size = maxHeap.size() + minHeap.size();

			if (size == 0) {
				maxHeap.add(number);
				answer.append(number).append("\n");
			} else if (size % 2 == 0) {
				maxHeap.add(number);
				if (maxHeap.peek() > minHeap.peek()) {
					answer.append(swapAndPeek()).append("\n");
				} else {
					answer.append(maxHeap.peek()).append("\n");
				}
			} else if (size % 2 == 1) {
				minHeap.add(number);
				if (maxHeap.peek() > minHeap.peek()) {
					answer.append(swapAndPeek()).append("\n");
				} else {
					answer.append(maxHeap.peek()).append("\n");
				}
			}
		}

		System.out.print(answer);
	}

	private static int swapAndPeek() {
		int maxH = maxHeap.poll();
		int minH = minHeap.poll();

		maxHeap.add(minH);
		minHeap.add(maxH);

		return maxHeap.peek();
	}
}
