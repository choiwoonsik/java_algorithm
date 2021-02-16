package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 보석도둑_1202 {
	static int N, K;
	static long point;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> queue_jew = new PriorityQueue<>((s1, s2) -> Integer.compare(s2, s1)); // 내림차순

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		pair[] jew_arr = new pair[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			jew_arr[i] = new pair(w, p);
		}
		Arrays.sort(jew_arr, (s1, s2) -> {
			if (s1.weight > s2.weight) return 1;
			else if (s1.weight < s2.weight) return -1;
			else return Integer.compare(s2.price, s1.price); // 가치 높은거 먼저
		});

		int[] bag_arr = new int[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int capacity = Integer.parseInt(st.nextToken());
			bag_arr[i] = capacity;
		}
		Arrays.sort(bag_arr);

		// 가방 : 2 , 5, 9 , 10 ...
		int j = 0;
		for (int bag : bag_arr) {
			while (j < N && jew_arr[j].weight <= bag) {
				queue_jew.add(jew_arr[j].price);
				j++;
			}
			if (!queue_jew.isEmpty())
				point += queue_jew.poll();
		}
		System.out.println(point);
	}

	private static class pair
	{
		int weight, price;

		public pair(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}
	}
}
