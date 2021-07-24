package Gold이상문제_정리;
/*
7 4
apple
ant
sand
apple
append
sand
sand
 */

import java.util.*;
import java.io.*;

public class 영단어암기는어려워_20920 {
	static int N, M;
	static HashMap<String, Integer> map = new HashMap<>();
	static ArrayList<String> words = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			if (word.length() >= M) {
				if (!map.containsKey(word)) map.put(word, 1);
				else map.put(word, map.get(word) + 1);
			}
		}

		words.addAll(map.keySet());
		words.sort((o1, o2) -> {
			if (!map.get(o1).equals(map.get(o2))) return map.get(o2) - map.get(o1);
			else if (o1.length() != o2.length()) return o2.length() - o1.length();
			else return o1.compareTo(o2);
		});

		StringBuilder answer = new StringBuilder();
		for (String s : words) answer.append(s).append("\n");
		bw.write(answer.toString());
		bw.flush();
	}
}
