package 인덱스트리;

import java.util.*;
import java.io.*;
/*
6
2 1 2
2 3 3
1 2
1 2
2 1 -1
1 2
 */
public class 사탕상자_2243 {
	static int N;
	static int S;
	static int[] flavorCountTree;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		S = 1;
		while (S <= 1000000) S *= 2;
		flavorCountTree = new int[S * 2];

		// 초기 모든 맛 별 사탕의 개수는 0개

		// 1 : b번째 사탕 꺼낸다. 해당 맛 출력
		// 2 : b맛 사탕을 c개 넣는다. 음수는 그만큼 뺀다
		for (int cmd = 0; cmd < N; cmd++) {
			st = new StringTokenizer(br.readLine());

			int type = Integer.parseInt(st.nextToken());

			if (type == 1) {
				int index = Integer.parseInt(st.nextToken());
				int candyFlavor = query(1, 1, S, index);
				update(candyFlavor, -1);
				answer.append(candyFlavor).append("\n");
			} else {
				int candyFlavor = Integer.parseInt(st.nextToken());
				int candyCountDiff = Integer.parseInt(st.nextToken());
				update(candyFlavor, candyCountDiff);
			}
		}

		System.out.print(answer);
	}

	private static int query(int node, int start, int end, int index) {

		if (start == end) {
			return start;
		}

		int mid = (start + end) / 2;

		if (flavorCountTree[node * 2] >= index) {
			return query(node * 2, start, mid, index);
		} else {
			return query(node * 2 + 1, mid + 1, end, index - flavorCountTree[node * 2]);
		}
	}

	private static void update(int candyFlavor, int candyCountDiff) {
		int idx = candyFlavor + S - 1;

		flavorCountTree[idx] += candyCountDiff;

		while (idx > 1) {
			idx /= 2;
			flavorCountTree[idx] = flavorCountTree[idx * 2] + flavorCountTree[idx * 2 + 1];
		}
	}
}
