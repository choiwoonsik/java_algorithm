package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 카누선수_9007 {
	static int T, K, N;
	static long[][] students;
	static long[][] upDown;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			students = new long[4][N];

			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					students[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			upDown = new long[2][N*N];
			int idx = 0;
			for (int up = 0; up < N; up++) {
				long personA = students[0][up];
				for (int down = 0; down < N; down++) {
					long personB = students[1][down];
					upDown[0][idx++] = personA + personB;
				}
			}
			idx = 0;
			for (int up = 0; up < N; up++) {
				long personA = students[2][up];
				for (int down = 0; down < N; down++) {
					long personB = students[3][down];
					upDown[1][idx++] = personA + personB;
				}
			}

			Arrays.sort(upDown[0]);
			Arrays.sort(upDown[1]);
			boolean find = false;
			long MinDiff = Long.MAX_VALUE;
			long answer = 0;

			for (int index = 0; index < N * N; index++) {
				long A = upDown[0][index];

				int left = 0;
				int right = N * N;
				while (left < right)
				{
					int mid = (left + right) / 2;
					long sum_person = A + upDown[1][mid];
					if (sum_person < K) {
						left = mid + 1;
						if (Math.abs(K - sum_person) <= MinDiff) {
							MinDiff = Math.abs(K - sum_person);
							answer = sum_person;
						}
					}
					else if (sum_person > K) {
						right = mid;
						if (Math.abs(K - sum_person) < MinDiff) {
							MinDiff = Math.abs(K - sum_person);
							answer = sum_person;
						}
					}
					else {
						answer = sum_person;
						find = true;
						break;
					}
				}
				if (find) break;
			}
			result.append(answer).append("\n");
		}
		System.out.println(result);
	}
}
