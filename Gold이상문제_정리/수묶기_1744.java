package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 수묶기_1744 {
	static int N;
	static boolean[] paired;
	static ArrayList<int[]> pairs = new ArrayList<>();
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		paired = new boolean[N];

		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(list);

		for (int i = 0; i < list.size() - 1; i++) {
			if (paired[i] || paired[i+1]) continue;
			int a = list.get(i);
			int b = list.get(i + 1);
			if (a < 0 && b < 0) {
				paired[i] = true;
				paired[i + 1] = true;
				pairs.add(new int[]{a, b});
			} else if (a < 0 && b == 0) {
				paired[i] = true;
				paired[i + 1] = true;
				pairs.add(new int[]{a, b});
			}
			else
				break;
		}
		for (int i = list.size()-1; i > 0 ; i--) {
			if (paired[i] || paired[i-1]) continue;
			int a = list.get(i);
			int b = list.get(i-1);
			if (a > 0 && b > 0 && b != 1) {
				paired[i] = true;
				paired[i - 1] = true;
				pairs.add(new int[]{a, b});
			}
		}
		long sum = 0;
		for (int i = 0; i < list.size(); i++) {
			if (!paired[i]) {
				sum += list.get(i);
			}
		}

		for (int[] s : pairs) {
			int a = s[0];
			int b = s[1];
			int ab = a * b;
			sum += ab;
		}
		System.out.println(sum);
	}
}
