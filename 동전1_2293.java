import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전1_2293 {
	static int[] DP;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		DP = new int[10001];
		DP[0] = 1;

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		for (int n = 0; n < N; n++) {
			int coin = Integer.parseInt(br.readLine());
			for (int k = 1; k <= K; k++) {
				if (k >= coin) {
					DP[k] = DP[k] + DP[k - coin];
				}
			}
		}
		System.out.println(DP[K]);
	}
}
