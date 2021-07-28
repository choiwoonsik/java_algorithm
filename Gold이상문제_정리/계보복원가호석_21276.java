package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 계보복원가호석_21276 {
	static int N, M;
	static HashMap<String, Integer> familyMap = new HashMap<>();
	static List<Node> allChilds = new LinkedList<>();
	static ArrayList<Integer>[] adj;
	static String[] families;
	static int[] degree;
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N];
		degree = new int[N];

		families = br.readLine().split(" ");
		Arrays.sort(families);
		for (int i = 0; i < N; i++) {
			familyMap.put(families[i], i);
			adj[i] = new ArrayList<>();
		}

		M = Integer.parseInt(br.readLine());
		String[] input;
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int child = familyMap.get(input[0]);
			int parent = familyMap.get(input[1]);
			if (!adj[parent].contains(child)) adj[parent].add(child);
			degree[child]++;
		}

		List<Integer> roots = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (degree[i] == 0) roots.add(i);
		}

		answer.append(roots.size()).append("\n");
		for (int root : roots) {
			answer.append(families[root]).append(" ");
			makeTree(root);
		}

		allChilds.sort(Comparator.comparing(n -> n.name));
		for (Node node : allChilds)
			answer.append("\n").append(node.name).append(" ").append(node.size).append(" ").append(node.childs);

		System.out.print(answer);
	}

	private static void makeTree(int parent) {
		List<Integer> childs = new LinkedList<>();
		adj[parent].sort(null);
		StringBuilder childStr = new StringBuilder();
		for (int child : adj[parent]) {
			if (degree[child] == 1) {
				childStr.append(families[child]).append(" ");
				childs.add(child);
			}
			degree[child]--;
		}
		allChilds.add(new Node(families[parent], childs.size(), childStr.toString()));

		for (int child : childs) {
			if (degree[child] == 0) makeTree(child);
		}
	}

	private static class Node
	{
		String name;
		int size;
		String childs;

		public Node(String name, int size, String childs) {
			this.name = name;
			this.size = size;
			this.childs = childs;
		}
	}
}
