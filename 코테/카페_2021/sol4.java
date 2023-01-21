package 코테.카페_2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class sol4 {
	public static void main(String[] args) {
		Solution sol;
		int[] solution;
		int[] answer;
		boolean isMatch;

		sol = new Solution();
		solution = sol.solution(
				new long[]{35, 25, 3, 8, 7},
				new int[][]{
						{1, 2, 5},
						{2, 1, 5},
						{1, 3, 2},
						{3, 4, 2},
						{3, 5, 20},
						{4, 5, 1}
				}
		);
		answer = new int[]{3, 4, 5, 2, 1};
		isMatch = true;
		for (int i = 0; i < solution.length; i++) {
			if (answer[i] != solution[i]) {
				isMatch = false;
				break;
			}
		}
		System.out.println(isMatch);

		sol = new Solution();
		solution = sol.solution(
				new long[]{8, 13, 5, 8},
				new int[][]{
						{1, 3, 10},
						{3, 4, 1},
						{4, 2, 2},
						{2, 1, 3}
				}
		);
		answer = new int[]{3, 4, 1, 2};
		isMatch = true;
		for (int i = 0; i < solution.length; i++) {
			if (answer[i] != solution[i]) {
				isMatch = false;
				break;
			}
		}
		System.out.println(isMatch);

		sol = new Solution();
		solution = sol.solution(
				new long[]{5, 200, 300},
				new int[][]{
						{1, 2, 1},
						{1, 3, 1}
				}
		);
		answer = new int[]{1, 2, 3};
		isMatch = true;
		for (int i = 0; i < solution.length; i++) {
			if (answer[i] != solution[i]) {
				isMatch = false;
				break;
			}
		}
		System.out.println(isMatch);

	}

	private static class Solution {
		static ArrayList<Node>[] adj;
		static PriorityQueue<Node> nodePq;
		static boolean[] check;
		static boolean[] isBlockNode;
		static int[] inCount;
		static int[] nodeLongestWire;
		static ArrayList<Integer> nodeList;
		// 1_000_000_000_000_000

		private int[] solution(long[] ages, int[][] wires) {
			int N = (ages.length + 1);
			nodeList = new ArrayList<>();
			nodePq = new PriorityQueue<>(Comparator.comparingLong(node -> node.remainAge));
			adj = new ArrayList[N];
			nodeLongestWire = new int[N];
			isBlockNode = new boolean[N];
			inCount = new int[N];
			check = new boolean[N];

			for (int i = 1; i < N; i++) {
				adj[i] = new ArrayList<>();
			}

			for (int[] wire : wires) {
				int start = wire[0];
				int end = wire[1];
				int len = wire[2];

				adj[start].add(new Node(end, ages[end - 1], len));
				inCount[end]++;
				if (!check[start]) {
					check[start] = true;
					nodePq.add(new Node(start, ages[start - 1], 0));
				}
			}

			for (int i = 1; i < N; i++) {
				adj[i].sort(Comparator.comparing(node -> node.nodeNumber));
			}

			while (!nodePq.isEmpty()) {
				Node curNode = nodePq.poll();

				if (isBlockNode[curNode.nodeNumber]) continue;

				isBlockNode[curNode.nodeNumber] = true;
				nodeList.add(curNode.nodeNumber);

				for (Node nextNode : adj[curNode.nodeNumber]) {
					inCount[nextNode.nodeNumber]--;
					if (inCount[nextNode.nodeNumber] == 0) {
						if (curNode.remainAge + nextNode.wireLen < nextNode.remainAge) {
							// 전력이 먼저 끝나면
							nextNode.remainAge = curNode.remainAge + nextNode.wireLen;
							nodePq.add(nextNode);
						}
					}
				}
			}

			int[] answer = new int[nodeList.size()];
			int i = 0;
			for (int l : nodeList) {
				System.out.println(l);
				answer[i++] = l;
			}

			return answer;
		}

		private static class Node {
			int nodeNumber;
			long remainAge;
			int wireLen;

			private Node(int nodeNumber, long remainAge, int wireLen) {
				this.nodeNumber = nodeNumber;
				this.remainAge = remainAge;
				this.wireLen = wireLen;
			}
		}
	}
}
