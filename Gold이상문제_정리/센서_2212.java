package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 센서_2212 {
	static int N, K;
	static PriorityQueue<Integer> sensor = new PriorityQueue<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(s -> -s));
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (!sensor.contains(p)) sensor.add(p);
		}

		while (!sensor.isEmpty())
		{
			int top = sensor.poll();

			if (!sensor.isEmpty()) {
				pq.add(sensor.peek() - top);
			}
		}

		for (int i = 0; i < K - 1; i ++)
			pq.poll();

		int sum = 0;
		for (int dist : pq)
			sum += dist;
		System.out.println(sum);
	}
}
