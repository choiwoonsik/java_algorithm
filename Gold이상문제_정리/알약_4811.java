package Gold이상문제_정리;

import java.io.*;

public class 알약_4811 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder str = new StringBuilder();

		long[][] DP;
		long[] ANS = new long[31];

		for (int pill = 1; pill <= 30; pill++) {
			DP = new long[pill+1][pill+1];
			DP[pill][0] = 1;
			for (int w = pill-1; w >= 0; w--) {
				for (int h = pill; h >= 0; h--) {
					if (h > 0)
						DP[w][h] += DP[w + 1][h - 1];
					if (h < pill)
						DP[w][h] += DP[w][h + 1];
				}
			}
			ANS[pill] = DP[0][0];
		}

		while (true)
		{
			int N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			str.append(ANS[N]).append("\n");
		}
		System.out.print(str);
	}
}
