package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N번째큰수_2075 {
	static int N;
	// N^2 * 4byte 의 경우 N이 1000개이면 4백만바이트가 되므로 4MB가 된다
	// 따라서 이를 n^2의 방식으로 N번째 큰수를 찾게되면 메모리가 초과가 나게 되므로 안된다 (12mb 제한)
	// -> 힙구조를 사용해서 제한된 메모리만을 사용해서 구하자
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		N = Integer.parseInt(st.nextToken());

		// 최소힙이라면 최상단 노드는 밑의 값들은 모두 자신보다 큰수 라는 것이다
		// 그러므로 N^2개의 수중에서 N번째 큰수는 pq를 N개의 사이즈로 유지시켰을때 나오는 최상단 노드값이 될것이다
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (pq.size() < N)
					pq.add(num);
				else {
					if (!pq.isEmpty() && num > pq.peek()) {
						pq.poll();
						pq.add(num);
					}
				}
			}
		}
		System.out.println(pq.poll());
	}
}
