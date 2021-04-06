package Gold이상문제_정리.코딩테스트;
/*
1
5
5 4 3 2 1
2
2 4
3 4
 */

/*
5 4 3 2 1
-> 5 4 / 5 3 / 5 2 / 5 1
-> 4 1 / 3 1 / 2 1
-> 2 4 / 3 4

no cnt -> 5

cnt
5 : 0 / 0
4 : -5, -2, -3 / 3
3 : -5 / 1
2 : -5 / 1
1 : -2, -3, -4, -5 / 4

---

no cnt -> 5 3 2

4 : -2, -3 / 2
3 : 0 / 0
2 : 0 / 0
1 : -2, -3, -4, -5 / 4

---

no cnt -> 5 3 2 4

4 : 0 / 0
1 : -4 / 1

---

no cnt -> 5 3 2 4 1

1 : 0 / 0

 */

import java.io.*;
import java.util.*;

public class 최종순위_3665 {
	static int T, N, M;
	static int[] past_order;
	static int[] degree_cnt;
	static ArrayList<Integer>[] in_degree;
	static StringBuilder str = new StringBuilder();
	static Queue<Integer> patch_order = new LinkedList<>();
	static Queue<Integer> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0)
		{
			N = Integer.parseInt(br.readLine());
			degree_cnt = new int[N+1];
			in_degree = new ArrayList[N+1];
			for (int i = 0; i < N + 1; i++) {
				in_degree[i] = new ArrayList<>();
			}
			queue.clear();
			patch_order.clear();
			past_order = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			M = Integer.parseInt(br.readLine());
			List<Integer> diff_list = new ArrayList<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int win = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());

				if (!diff_list.contains(win)) diff_list.add(win);
				if (!diff_list.contains(lose)) diff_list.add(lose);
				in_degree[win].add(lose);
				degree_cnt[lose]++;
			}

			// 순서 만들기
			for (int w = 0; w < past_order.length - 1; w++) {
				for (int l = w + 1; l < past_order.length; l++) {
					int win = past_order[w];
					int lose = past_order[l];

					if (!diff_list.contains(win)) {
						in_degree[win].add(lose);
						degree_cnt[lose]++;
					}

					if (diff_list.contains(win)) {
						if (diff_list.contains(lose)) continue;
						in_degree[win].add(lose);
						degree_cnt[lose]++;
					}
				}
			}

			for (int i = 1; i < N + 1; i++) {
				if (degree_cnt[i] == 0)
					queue.add(i);
			}

			while (!queue.isEmpty())
			{
				int win = queue.poll();
				patch_order.add(win);

				for (int lose : in_degree[win])
				{
					degree_cnt[lose]--;
					if (degree_cnt[lose] == 0) {
						queue.add(lose);
					}
				}
			}

			boolean cannot_find = false;
			for (int i = 1; i < N + 1; i++) {
				if (degree_cnt[i] != 0) {
					cannot_find = true;
					break;
				}
			}

			if (cannot_find)
				str.append("IMPOSSIBLE").append("\n");
			else {
				StringBuilder order = new StringBuilder();
				patch_order.forEach(s->order.append(s).append(" "));
				str.append(order).append("\n");
			}
		}
		System.out.print(str);
	}
}
