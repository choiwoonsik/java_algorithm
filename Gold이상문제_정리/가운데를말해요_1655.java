package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 가운데를말해요_1655 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
		StringBuilder str = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());

			if (maxHeap.size() == minHeap.size())
				maxHeap.add(number);
			else
				minHeap.add(number);
			// 같으면 maxHeap에 넣는다. 단 minHeap이 더 작으면 swap
			// 크기가 다르면 minHeqp에 넣는다, 단 maxHeap이 더 크면 swap
			// -> minHeap의 최솟값보다 maxHeap의 최대값이 더 작게 유지
			if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if (maxHeap.peek() > minHeap.peek()) {
					int tmp = minHeap.poll();
					minHeap.add(maxHeap.poll());
					maxHeap.add(tmp);
				}
			}
			str.append(maxHeap.peek()).append("\n");
		}
		System.out.print(str);
	}
}
