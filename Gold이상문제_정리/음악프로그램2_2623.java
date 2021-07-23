package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
6 3
3 1 4 3
4 6 2 5 4
2 2 3
 */
public class 음악프로그램2_2623 {
	static int N, K;
	static ArrayList<Integer>[] songAdj;
	static int[] degree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;

		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]) + 1;
		K = Integer.parseInt(input[1]);

		degree = new int[N];
		songAdj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			songAdj[i] = new ArrayList<>();
		}

		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int len = Integer.parseInt(input[0]);
			for (int song = 1; song < len; song++) {
				int nowSong = Integer.parseInt(input[song]);
				int nextSong = Integer.parseInt(input[song + 1]);
				songAdj[nowSong].add(nextSong);
				degree[nextSong] += 1;
			}
		}

		List<Integer> answer = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i < N; i++) {
			if (degree[i] == 0) queue.add(i);
		}

		while (!queue.isEmpty()) {
			int nowSong = queue.poll();
			answer.add(nowSong);
			for (int nextSong : songAdj[nowSong]) {
				degree[nextSong] -= 1;
				if (degree[nextSong] == 0) queue.add(nextSong);
			}
		}

		StringBuilder result = new StringBuilder();
		if (answer.size() == N - 1) {
			for (int song : answer)
				result.append(song).append("\n");
			System.out.println(result);
		} else System.out.println(0);
	}
}
