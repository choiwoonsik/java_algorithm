package Gold이상문제_정리;
/*
1번 지점에서 출발하여 가장 많은 점수를 얻어 다시 1번 지점으로 돌아오는 팀이 우승을 하게 된다.
가장 많은 점수를 얻어 1번 지점으로 돌아오는 경로를 찾아 그 얻는 점수와 경로를 출력하는 프로그램을 작성하시오.
 */
import java.io.*;
import java.util.*;

public class 자동차경주_2611 {
	static int N, M;
	static ArrayList<Node>[] adj;
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adj = new ArrayList[N+1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			adj[p].add(new Node(q, point));
		}

		race();
	}

	private static void race() {
		PriorityQueue<CAR> pq = new PriorityQueue<>(Comparator.comparingInt(s -> -s.sum_point));
		StringBuilder last_trace = new StringBuilder();
		StringBuilder t = new StringBuilder();
		t.append("1 ");
		int MAX = 0;


		for (Node n : adj[1])
			pq.add(new CAR(n.next, n.point, t));

		while (!pq.isEmpty())
		{
			CAR car = pq.poll();
			StringBuilder tmp_list = new StringBuilder(car.trace);
			tmp_list.append(car.now).append(" ");


			for (Node node : adj[car.now]) {
				if (node.next == 1 && car.sum_point + node.point > MAX)
				{
					MAX = car.sum_point + node.point;
					last_trace = tmp_list;
				}
				else if (node.next != 1)
					pq.add(new CAR(node.next, car.sum_point + node.point, tmp_list));
			}
		}
		answer.append(MAX).append("\n");
		answer.append(last_trace);
		answer.append("1");
		System.out.println(answer);
	}

	private static class Node
	{
		int next;
		int point;

		public Node(int next, int point) {
			this.next = next;
			this.point = point;
		}
	}

	private static class CAR
	{
		int now;
		int sum_point;
		StringBuilder trace = new StringBuilder();

		public CAR(int now, int sum_point, StringBuilder trace) {
			this.now = now;
			this.sum_point = sum_point;
			this.trace = trace;
		}
	}

	private static class info
	{
		StringBuilder node_trace = new StringBuilder();
		
	}
}
