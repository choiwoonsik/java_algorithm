package 문자열;

import java.io.*;

public class 이친수찾기_2201 {
	static long MAX = 1_000_000_000_000_000_000L;
	static long[] partCount;
	static long SUM = 0;
	static int now;
	static long K;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Long.parseLong(br.readLine());
		partCount = new long[88];

		partCount[0] = 1;
		partCount[1] = 1;
		partCount[2] = 1;
		partCount[3] = 2;

		SUM = 4;
		int next = 4;
		while (SUM <= MAX) {
			partCount[next] += 2;
			for (int i = next - 2; i >= 2; i--) {
				partCount[next] += partCount[i];
			}
			SUM += partCount[next];
			next++;
		}

		now = 1;
		while (K > partCount[now]) {
			K -= partCount[now++];
		}

		if (now == 1) {
			answer.append("1");
		}
		else if (now == 2) {
			answer.append("10");
		}
		else {
			answer.append("10");
			make(K, now);
		}
		System.out.print(answer);
	}

	private static void make(long remain, int floor) {
		if (floor == 3) {
			if (remain == partCount[floor] / 2) {
				answer.append("0");
			} else {
				answer.append("1");
			}
		} else {
			long cutLine;
			cutLine = partCount[floor - 1];
			if (remain <= cutLine) {
				answer.append("0");
				make(remain, floor - 1);
			} else {
				answer.append("1");
				make(remain - cutLine, floor - 1);
			}
		}
	}
}
