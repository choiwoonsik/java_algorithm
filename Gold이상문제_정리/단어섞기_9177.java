package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 단어섞기_9177 {

	static boolean flag;
	static int N, LEN;
	static String first, second;
	static StringBuilder answer = new StringBuilder();
	static HashSet<Character> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			first = st.nextToken();
			second = st.nextToken();
			LEN = first.length()+second.length();
			String third = st.nextToken();

			if (!check(third) || third.length() != LEN)
				answer.append(String.format("Data set %d: no\n", i + 1));
			else confirm(i, third);
		}
		System.out.print(answer);
	}

	private static void confirm(int i, String third) {
		flag = false;
		DFS(0, 0, third);

		if (flag) answer.append(String.format("Data set %d: yes\n", i + 1));
		else answer.append(String.format("Data set %d: no\n", i + 1));
	}


	private static boolean check(String word) {
		for (int i = 0; i < first.length(); i++)
			set.add(first.charAt(i));
		for (int i = 0; i < second.length(); i++)
			set.add(second.charAt(i));

		for (int i = 0; i < word.length(); i++)
			if (!set.contains(word.charAt(i))) return false;

		return true;
	}

	private static void DFS(int lcnt, int rcnt, String third) {
		if (flag) return;

		if (lcnt + rcnt == LEN) {
			flag = true;
			return;
		}

		if (lcnt < first.length() && third.charAt(lcnt+rcnt) == first.charAt(lcnt))
			DFS(lcnt + 1, rcnt, third);
		if (rcnt < second.length() && third.charAt(lcnt+rcnt) == second.charAt(rcnt))
			DFS(lcnt, rcnt + 1, third);
	}
}
