package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 모자이크_2539 {
	static int r, c;
	static int page;
	static int N;
	static int Min;
	static ArrayList<Integer> columnList = new ArrayList<>();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		page = Integer.parseInt(br.readLine().trim());
		N = Integer.parseInt(br.readLine().trim());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			Min = Math.max(Min, y);
			columnList.add(x);
		}
		Collections.sort(columnList);
		binarySearch();
	}

	private static void binarySearch() {
		int low = Min;
		int high = 1000000;
		int mid;

		while (low < high) {
			mid = (low + high) / 2;

			int pageCnt = matching(mid);

			if (pageCnt > page)
				low = mid + 1;
			else
				high = mid;
		}
		System.out.println(low);
	}

	private static int matching(int size) {
		int start = columnList.get(0);
		int pageCount = 0;

		int i = 0;
		loop:
		while (true) {
			while (columnList.get(i) < start + size) {
				i++;
				if (i >= columnList.size()) {
					pageCount++;
					break loop;
				}
			}
			start = columnList.get(i);
			pageCount++;
		}

		return pageCount;
	}
}
