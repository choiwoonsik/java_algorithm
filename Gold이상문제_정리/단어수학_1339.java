package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 단어수학_1339 {
	static int N;
	static boolean[] used;
	static ArrayList<Character> word_list;
	static HashSet<Character> words_set = new HashSet<>();
	static HashMap<Character, Integer> words_map = new HashMap<>();
	static char[][] words_arr;
	static int Max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		words_arr = new char[N][8];
		// 모든 단어 받기
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			words_arr[i] = st.nextToken().toCharArray();
			for (char c : words_arr[i])
				words_set.add(c);
		}
		word_list = new ArrayList<>(words_set);

		// 모든 단어들에 대해서 dfs
		used = new boolean[10];
		dfs(0);
		System.out.println(Max);
	}
	private static void dfs(int depth)
	{
		if (depth == words_set.size())
		{
			int sum = calc();
			Max = Math.max(Max, sum);
			return;
		}

		// 해당 알파벳에 0 ~ 9까지의 수 할당해보기
		for (int i = 0; i < 10; i++)
		{
			if (!used[i]) {
				used[i] = true;
				words_map.put(word_list.get(depth), i); // (A, 0), (A, 1), (A, 2), ...
				dfs(depth + 1);
				used[i] = false;
			}
		}
	}

	private static int calc() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			int inner_sum = 0;
			for (int j = 0; j < words_arr[i].length; j++)
			{
				inner_sum = inner_sum * 10 + words_map.get(words_arr[i][j]);
			}
			sum += inner_sum;
		}
		return sum;
	}

}