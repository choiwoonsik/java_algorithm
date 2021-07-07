package Gold이상문제_정리;

import java.io.*;
import java.util.*;
/*
3
0 0 0 1
0 0 0 1 1
0 0 0 0 0
0 0 0 0 0
1
1

3
0 0 0 1
0 0 0 0 0
0 0 0 0 0
0 0 0 1 0

0
1 2 3
 */
public class 다이어트_19942 {

	static int N;
	static int[][] nutrient;
	static boolean[] visited;
	static int minPrice = Integer.MAX_VALUE;
	static int P, F, C, V;
	static HashMap<Integer, ArrayList<String>> minMap = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		nutrient = new int[N][5];

		st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int protein = Integer.parseInt(st.nextToken());
			int fat = Integer.parseInt(st.nextToken());
			int carbon = Integer.parseInt(st.nextToken());
			int vitamin = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			nutrient[i][0] = protein;
			nutrient[i][1] = fat;
			nutrient[i][2] = carbon;
			nutrient[i][3] = vitamin;
			nutrient[i][4] = price;
		}

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N];
			DFS(0, 0, i, 0, 0, 0, 0);
		}

		if (minPrice == Integer.MAX_VALUE)
			System.out.println(-1);
		else {
			System.out.println(minPrice);
			String[] answer = minMap.get(minPrice).toArray(new String[]{});
			Arrays.sort(answer);
			System.out.print(answer[0]);
		}
	}

	private static void DFS(int start, int select, int max, int pS, int fS, int cS, int vS) {

		if (select >= max)
		{
			if (pS >= P && fS >= F && cS >= C && vS >= V)
			{
				int price = 0;

				StringBuilder answer = new StringBuilder();
				for (int i = 0; i < visited.length; i++) {
					if (visited[i]) {
						price += nutrient[i][4];
						answer.append(i + 1).append(" ");
					}
				}

				if (price <= minPrice) {
					minPrice = price;
					if (minMap.get(minPrice) == null) {
						ArrayList<String> tmp = new ArrayList<>();
						tmp.add(answer.toString());
						minMap.put(minPrice, tmp);
					}
					else minMap.get(minPrice).add(answer.toString());
				}
			}
			return;
		}

		for (int i = start; i < N; i++) {
			if (!visited[i])
			{
				visited[i] = true;
				pS += nutrient[i][0];
				fS += nutrient[i][1];
				cS += nutrient[i][2];
				vS += nutrient[i][3];
				DFS(i+1, select+1, max, pS, fS, cS, vS);
				visited[i] = false;
				pS -= nutrient[i][0];
				fS -= nutrient[i][1];
				cS -= nutrient[i][2];
				vS -= nutrient[i][3];
			}
		}
	}
}