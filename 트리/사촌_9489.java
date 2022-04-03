package 트리;

import java.util.*;
import java.io.*;

/*
10 15
1 3 4 5 8 9 15 30 31 32
12 9
3 5 6 8 9 10 13 15 16 22 23 25
10 4
1 3 4 5 8 9 15 30 31 32
0 0
 */
public class 사촌_9489 {
	static int N;
	static int K;
	static int INDEX;
	static int[] parents;
	static int[] nodes;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			if (N == 0 && K == 0) {
				break;
			}

			parents = new int[N + 1];
			nodes = new int[N + 1];
			nodes[0] = -100;
			parents[0] = -100;

			int parentNumber = -1;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int nodeNum = Integer.parseInt(st.nextToken());

				nodes[i] = nodeNum;
				if (nodes[i] != nodes[i - 1] + 1) {
					parentNumber++;
				}
				if (nodeNum == K) {
					INDEX = i;
				}
				parents[i] = parentNumber;
			}

			int total = 0;
			for (int i = 1; i <= N; i++) {
				if (parents[INDEX] != parents[i] && parents[parents[INDEX]] == parents[parents[i]]) {
					total++;
				}
			}

			answer.append(total).append("\n");
		}

		System.out.print(answer);
	}
}
