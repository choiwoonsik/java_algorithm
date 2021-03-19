package Gold이상문제_정리;
/*
6 4
1 2
1 3
2 5
4 5
 */
import java.io.*;
import java.util.*;

public class 선수과목_14567 {
	static int N, M;
	static ArrayList<Integer>[] adj;
	static Queue<Integer> queue = new LinkedList<>();
	static int[] in_degree;
	static StringBuilder str = new StringBuilder();
	static int semester = 1;
	static int[] sub_seme;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		in_degree = new int[N+1];
		sub_seme = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			adj[first].add(second);
			in_degree[second]++;
		}
		for (int i = 1; i < N + 1; i++) {
			if (in_degree[i] == 0) {
				queue.add(i);
				sub_seme[i] = semester;
			}
		}
		while (!queue.isEmpty())
		{
			int subject = queue.poll();

			for (int next_sub : adj[subject]){
				in_degree[next_sub]--;
				if (in_degree[next_sub] == 0) {
					queue.add(next_sub);
					sub_seme[next_sub] = sub_seme[subject] + 1;
				}
			}
		}
		for (int i : sub_seme)
			str.append(i).append(" ");
		System.out.println(str.replace(0, 2, ""));
	}
}
