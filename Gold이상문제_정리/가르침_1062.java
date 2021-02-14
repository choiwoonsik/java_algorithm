package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 가르침_1062 {
	static int N, K, learn;
	static boolean[] set = new boolean[26];
	static String[] words;
	public static void main(String[] args) throws IOException {
		// a, n, t, i, c
		// 단어의 개수 N, 글자 K개
		// 5개 미만의 경우 배울수 있는 글자x -> 최소 5이상
		// k글자 배울수 있을때 읽을수 있는 최대의 단어 개수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		set['a' - 'a'] = true;
		set['n' - 'a'] = true;
		set['t' - 'a'] = true;
		set['i' - 'a'] = true;
		set['c' - 'a'] = true;

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		words = new String[N];
		for (int i = 0; i < N; i++)
		{
			String tmp = br.readLine().replaceAll("anta|tica", "");
			words[i] = tmp;
		}
		if (K < 5)
			System.out.println(0);
		else if (K == 26)
			System.out.println(N);
		else {
			DFS(0, 0);
			System.out.println(learn);
		}
	}

	private static void DFS(int alpha, int learn_count) {
		if (learn_count == K - 5) {
			int count = 0;
			for (String word : words) {
				boolean learn_fail = false;
				for (int i = 0; i < word.length(); i++) {
					if (!set[word.charAt(i) - 'a']) {
						learn_fail = true;
						break;
					}
				}
				if (!learn_fail)
					count++;
			}
			learn = Math.max(learn, count);
			return;
		}

		for (int i = alpha; i < 26; i++) {
			if (!set[i]) {
				set[i] = true;
				DFS(i, learn_count + 1);
				set[i] = false;
			}
		}

	}
}
