package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
7
3
1
1
5
5
4
6
 */

public class 숫자고르기_2668 {
	static int N, all;
	static int[] select;
	static boolean[] visited;
	static boolean[] done;
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		select = new int[N+1];
		visited = new boolean[N+1];
		done = new boolean[N+1];

		for (int i = 1; i <= N; i++) {
			select[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i < N+1; i++) {
			DFS(i);
		}
		Collections.sort(list);
		System.out.println(all);
		list.forEach(System.out::println);
	}

	private static void DFS(int i) {
		visited[i] = true;

		int next = select[i];
		if (!visited[next]) {
			DFS(next);
		} else if (!done[next]) {
			int cnt = 1;
			ArrayList<Integer> sub_list = new ArrayList<>();
			sub_list.add(select[i]);
			for (int j = select[i]; j != i ; j = select[j]) {
				cnt++;
				sub_list.add(select[j]);
				if (done[j]) {
					done[i] = true;
					return;
				}
			}
			list.addAll(sub_list);
			all += cnt;
		}
		done[next] = true;
	}
}
