package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치킨배달_15686 {
	static int N, M, Min;
	static ArrayList<int[]> chickenList = new ArrayList<>();
	static ArrayList<int[]> houseList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		/*
		도시 : r행, c열
		1 : 집, 2 : 치킨집
		최대 M개를 골라서 도시의 치킨거리가 가장 작게 될지 구하라
		-> M개를 고른후 도시의 치킨거리를 구해서 출력하면 된다
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1)
					houseList.add(new int[]{i, j});
				else if (tmp == 2)
					chickenList.add(new int[]{i, j});
			}
		}

		// 모든 치킨집 중에서 M개를 뽑아서 그 치킨집들에 대해 모든 집들의 최소 거리의 합이 가장 적은걸 출력
		boolean[] include = new boolean[chickenList.size()];
		Min = 99999;
		chickenListMake(include, 0, M);
		System.out.println(Min);

	}

	private static void chickenListMake(boolean[] include, int start, int r) {

		if (r == 0)
		{
			calc(include);
			return;
		}
		for (int i = start; i < chickenList.size(); i++)
		{
			if (!include[i]) {
				include[i] = true;
				chickenListMake(include, i + 1, r - 1);
				include[i] = false;
			}
		}
	}

	private static void calc(boolean[] open) {
		int sum = 0;
		int dist;
		// 열린 집에대해서 모든 치킨집 비교
		for (int[] house : houseList) {
			dist = 2*N;
			for (int i = 0; i < chickenList.size(); i++) {
				if (open[i])
					dist = Math.min(dist, Math.abs(chickenList.get(i)[0] - house[0]) + Math.abs(chickenList.get(i)[1] - house[1]));
			}
			sum += dist;
			if (dist >= Min)
				break;
		}
		Min = Math.min(Min, sum);
	}
}
