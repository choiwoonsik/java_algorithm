package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 합이0인네정수_7453 {
	static int N;
	static int[][] ABCD;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ABCD = new int[4][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int abcd = 0; abcd < 4; abcd++) {
				ABCD[abcd][i] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < 4; i++)
			Arrays.sort(ABCD[i]);


	}
}
