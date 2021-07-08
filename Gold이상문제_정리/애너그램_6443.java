package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
2
abc
acba
 */

public class 애너그램_6443 {

	static int N;
	static HashSet<String> set = new HashSet<>();
	static boolean[] visited;
	static char[] selected;
	static char[] words;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		while (N-- > 0)
		{
			String s = br.readLine();

			words = s.toCharArray();
			Arrays.sort(words);

			visited = new boolean[words.length];
			selected = new char[words.length];
			DFS(0);
			set.clear();
		}
		bw.flush();
		bw.close();
	}

	private static void DFS(int depth) throws IOException {
		if (depth == words.length)
		{
			bw.write(selected);
			bw.write("\n");
			return;
		}

		selected[depth] = 0;
		for (int i = 0; i < words.length; i++) {
			if (!visited[i])
			{
				if (selected[depth] >= words[i]) continue;

				visited[i] = true;
				selected[depth] = words[i];
				DFS(depth + 1);
				visited[i] = false;
			}
		}
	}
}
