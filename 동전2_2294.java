import java.io.*;
import java.util.*;

public class 동전2_2294 {
	static int[] DP;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] coin_arr = new int[N];
		for (int i = 0; i < N; i++) {
			int c = Integer.parseInt(br.readLine());
			coin_arr[i] = c;
		}

		DP = new int[10001];
		Arrays.fill(DP, 987654321);
		DP[0] = 0;

		for (int k = 1; k <= K; k++) {
			for (int c = 0; c < N; c++) {
				int coin = coin_arr[c];
				if (k - coin >= 0) {
					DP[k] = Math.min(DP[k], DP[k - coin] + 1); // k원 - coin원 일때 dp + coin 한 개
				}
			}
		}
		if (DP[K] >= 987654321) System.out.println(-1);
		else System.out.println(DP[K]);
	}
}
