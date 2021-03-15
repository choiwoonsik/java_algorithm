package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 네트워크연결_3780 {
	static int T, N;
	static int[] parent;
	static int[] distance;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			parent = new int[N+1];
			distance = new int[N+1];
			for (int i = 0; i < N + 1; i++) {
				parent[i] = i;
				distance[i] = 0;
			}

			while (true) {
				st = new StringTokenizer(br.readLine());
				char type = st.nextToken().charAt(0);
				if (type == 'O') {
					break;
				} else if (type == 'E') {
					int company = Integer.parseInt(st.nextToken());
					find(company);
					str.append(distance[company]).append("\n");
				} else if (type == 'I') {
					int u = Integer.parseInt(st.nextToken());
					int v = Integer.parseInt(st.nextToken());
					if (u != find(v))
						union(u, v);
				}
			}
		}
		System.out.print(str);
	}
	private static int find(int n) {
		if (parent[n] == n)
			return n;
		int tmp_p = find(parent[n]);
		// 현재-센터 거리 += 현재의 부모-센터 거리
		distance[n] += distance[parent[n]];
		// 현재의 부모 -> 최고 부모
		parent[n] = tmp_p;
		return parent[n];
	}

	private static void union(int u, int v) {
		// 센터 i를 기업 j에 연결
		parent[u] = v;
		distance[u] = Math.abs(u - v) % 1000;
	}
}