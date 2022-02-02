package 인덱스트리;

import java.util.*;
import java.io.*;

public class 강수량_2094 {
	static int N;
	static int M;
	static int S = 1;
	static Info[] infos;
	static HashMap<Integer, IdxRain> map = new HashMap<>();
	static Info[] tree;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		infos = new Info[N + 1];
		calcS();
		tree = new Info[S * 2];

		for (int i = 0; i < S; i++) {
			tree[S + i] = new Info(0, 0);
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int year = Integer.parseInt(st.nextToken());
			int rain = Integer.parseInt(st.nextToken());

			Info info = new Info(year, rain);

			infos[i] = info;
			tree[S + i - 1] = info;
			map.put(info.year, new IdxRain(i, info.rain));
		}

		initTree();

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int beforeY = Integer.parseInt(st.nextToken());
			int nowX = Integer.parseInt(st.nextToken());

			// 둘다 정보 없음 -> maybe
			if (!map.containsKey(beforeY) && !map.containsKey(nowX)) {
				answer.append("maybe");
			}
			// 예전 정보는 있는데 현재 정보 없음 -> maybe
			else if (map.containsKey(beforeY) && !map.containsKey(nowX)) {
				// x앞 년도 최대값이 y년 값보다 작아야됨 -> maybe, 아니면 false
				int queryLeft = map.get(beforeY).index + 1;
				int queryRight = binarySearch(nowX) - 1;
				int maxRain = query(1, 1, S, queryLeft, queryRight);

				if (maxRain < map.get(beforeY).rain)
					answer.append("maybe");
				else answer.append("false");
			}
			// 예전 정보는 없는데 현재 정보 있음 -> maybe or false (사이 모든 년도가 현재보다 낮아야됌)
			else if (!map.containsKey(beforeY) && map.containsKey(nowX)) {
				int queryLeft = binarySearch(beforeY);
				int queryRight = map.get(nowX).index - 1;
				int maxRain = query(1, 1, S, queryLeft, queryRight);

				if (maxRain >= map.get(nowX).rain) {
					answer.append("false");
				} else answer.append("maybe");
			}
			// 둘다 정보가 있는 경우
			else if (map.containsKey(beforeY) && map.containsKey(nowX)) {
				int queryLeft = map.get(beforeY).index + 1;
				int queryRight = map.get(nowX).index - 1;
				int maxRain = query(1, 1, S, queryLeft, queryRight);

				if (map.get(beforeY).rain < map.get(nowX).rain || maxRain >= map.get(nowX).rain) {
					answer.append("false");
				} else if (queryRight - queryLeft == nowX - beforeY - 2) {
					answer.append("true");
				} else {
					answer.append("maybe");
				}
			}
			answer.append("\n");
		}
		System.out.print(answer);
	}

	private static void calcS() {
		while (S < N) {
			S *= 2;
		}
	}

	private static void initTree() {
		for (int i = S - 1; i >= 1; i--) {
			tree[i] = new Info(0, 0);
			tree[i].rain = Math.max(tree[i * 2].rain, tree[i * 2 + 1].rain);
		}
	}

	private static int binarySearch(int year) {
		int left = 1;
		int right = N;
		int mid;

		while (left < right) {
			mid = (left + right) / 2;

			int midYear = infos[mid].year;

			if (midYear < year) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	private static int query(int node, int start, int end, int queryLeft, int queryRight) {
		if (start > queryRight || end < queryLeft) return 0;

		if (start == end)
			return tree[node].rain;

		if (queryLeft <= start && end <= queryRight)
			return tree[node].rain;

		int mid = (start + end) / 2;
		return Math.max(query(node * 2, start, mid, queryLeft, queryRight),
				query(node * 2 + 1, mid + 1, end, queryLeft, queryRight));
	}

	private static class Info {

		int year;
		int rain;

		public Info(int year, int rain) {
			this.year = year;
			this.rain = rain;
		}
	}

	private static class IdxRain {
		int index;
		int rain;

		public IdxRain(int index, int rain) {
			this.index = index;
			this.rain = rain;
		}
	}
}
