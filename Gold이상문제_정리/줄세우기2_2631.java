package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 줄세우기2_2631 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<>();
		list.add(-1);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int child = Integer.parseInt(st.nextToken());
			int pos = Math.abs(Collections.binarySearch(list, child) + 1);
			if (pos >= list.size()) {
				list.add(child);
			} else {
				list.remove(pos);
				list.add(pos, child);
			}
		}
		System.out.println( N - (list.size() - 1));
	}
}
