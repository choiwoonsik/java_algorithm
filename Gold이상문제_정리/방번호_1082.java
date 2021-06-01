package Gold이상문제_정리;

import java.io.*;
import java.util.*;
/*
3
6 7 8
21
 */
public class 방번호_1082 {
	static int N, total;
	static HashMap<Integer, Integer> num_co = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num_co.put(i, Integer.parseInt(st.nextToken()));
		total = Integer.parseInt(br.readLine());

		int one_min = find_min_num(1, N);
		int zero_min = find_min_num(0, N);

		StringBuilder answer = new StringBuilder();
		if (total >= num_co.get(one_min)) {
			total -= num_co.get(one_min);
			answer.append(one_min);
		}

		int count = total / num_co.get(zero_min);
		total -= num_co.get(zero_min) * count;
		while (count-- > 0) answer.append(zero_min);

		StringBuilder result;
		if (total < 0)
			result = answer;
		else {
			StringBuilder change_answer = new StringBuilder();
			for (int idx = 0; idx < answer.length(); idx++) {
				int now_num = answer.charAt(idx) - '0';
				boolean flag = false;

				for (int num = N - 1; num > now_num; num--) {
					if (total >= num_co.get(num) - num_co.get(now_num)) {
						total -= num_co.get(num) - num_co.get(now_num);
						change_answer.append(num);
						flag = true;
						break;
					}
				}
				if (!flag) change_answer.append(now_num);
			}
			result = change_answer;
		}

		for (int i = 0; i < result.length() - 1; i++) {
			if (result.charAt(i) == '0' && result.charAt(i + 1) == '0') {
				result.replace(i, i + 1, "");
				i--;
			}
			else break;
		}
		System.out.println(result);
	}

	private static int find_min_num(int s, int e) {
		int min = 100;
		int num = 0;
		for (int i = s; i < e; i++) {
			if (num_co.get(i) < min) {
				min = num_co.get(i);
				num = i;
			}
		}
		return num;
	}
}
