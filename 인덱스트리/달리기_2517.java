package 인덱스트리;

import java.io.*;
import java.util.*;

public class 달리기_2517 {
	static int N;
	static int S;
	static Runner[] runnersSkillOrder;
	static int[] tree;
	static int[] runnerResult;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		runnersSkillOrder = new Runner[N + 1];
		runnerResult = new int[N + 1];
		S = 1;
		while (S < N) S *= 2;
		tree = new int[S * 2];

		runnersSkillOrder[0] = new Runner(0, 1111111111);
		for (int i = 1; i < N + 1; i++) {
			int skill = Integer.parseInt(br.readLine());
			runnersSkillOrder[i] = new Runner(i, skill);
		}
		Arrays.sort(runnersSkillOrder, Comparator.comparingInt(R -> -R.skill));

		segmentTree();

		for (int order = 1; order < N + 1; order++) {
			answer.append(runnerResult[order]).append("\n");
		}
		System.out.print(answer);
	}

	private static void segmentTree() {
		for (int i = 1; i < N + 1; i++) {

			int runnerOrder = i;
			Runner runner = runnersSkillOrder[runnerOrder];

			if (runner.order == runnerOrder) {
				int count = query(1, 1, S, 1, runnerOrder);
				runnerResult[runnerOrder] = count + 1;
			} else {
				runnerOrder = runner.order;
				int count = query(1, 1, S, 1, runnerOrder);
				runnerResult[runnerOrder] = count + 1;
			}

			update(runnerOrder);

//			System.out.println("=============");
//			for (int j = S; j < S + N; j++) {
//				System.out.print(tree[j]+" ");
//			}
//			System.out.println();
		}
	}

	private static void update(int node) {
		int cur = S + node - 1;

		while (cur >= 1) {
			tree[cur] += 1;
			cur /= 2;
		}
	}

	private static int query(int node, int start, int end, int queryLeft, int queryRight) {
		if (start > queryRight || end < queryLeft) return 0;

		if (start == end) return tree[node];

		if (queryLeft <= start && end <= queryRight) return tree[node];

		int mid = (start + end) / 2;
		return query(node * 2, start, mid, queryLeft, queryRight) +
				query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);
	}

	private static class Runner{
		int order;
		int skill;

		public Runner(int order, int skill) {
			this.order = order;
			this.skill = skill;
		}
	}
}
