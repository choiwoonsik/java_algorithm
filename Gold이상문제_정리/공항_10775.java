package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공항_10775 {
	/*
	먼저 순서대로 온 비행기에 대해서 게이트의 부모를 찾는다 그 부모에 넣는다
	부모는 비행기가 들어오면 왼쪽 게이트와 union 한다
	if 부모가 0이면 끝!
	 */
	static int G, B;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		G = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		int count;

		parents = new int[G+1];
		for (int i = 0; i <= G; i++)
			parents[i] = i;

		for (count = 1; count <=B; count++) {
			int plane = Integer.parseInt(br.readLine());

			if (find(plane) != 0)
				union(find(plane), find(find(plane) - 1));
			else
				break;
		}

		System.out.println(count-1);
	}

	private static void union(int origin, int left) {
		parents[parents[origin]] = parents[left];
	}

	private static int find(int plane) {
		if (parents[plane] == plane) return plane;
		return parents[plane] = find(parents[plane]);
	}
}
