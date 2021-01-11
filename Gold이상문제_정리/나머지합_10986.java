package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 나머지합_10986 {
	static int N, M;
	static long[] remains;
	public static void main(String[] args) throws IOException {
        /*
        나머지합을 구하는 의미

        DP배열에 누적합의 나머지를 담는다
         그 뜻은 1~i까지의 합이 M으로 나누어 떨이지는지, 나머지는 얼마인지 확인하는 것

        dp배열에 1~N까지의 누적합의 나머지가 담기면
		특정 i~j구간의 합이 M으로 나눠지는 지 알기 위해서는 DP[j] - DP[i] == 0이면 된다
		(=> 1~j 구간 - 1~i구간이므로)
		결국 이는 DP[j] == DP[i]를 구하면 되는 것이므로
		나머지들이 같은 구간들을 모두 2개씩 뽑는 조합으로 모든 구간을 확인할 수 있
         */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		remains = new long[M];
		long sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
		{
			long n = Long.parseLong(st.nextToken());
			sum += n; // 누적합
			remains[(int)(sum % M)]++;  // 누적합의 나머지에 대해서 해당 나머지가 나온 수를 카운트
		}

		long count = 0;
		count += remains[0]; // 기본적으로 나머지가 0인거는 다 더해놓고
		for (int i = 0; i < M; i++) {
			if (remains[i] == 0)
				continue;
			// (n(n+1)) / 2 == 나머지가 같은 애들중에서 2개를 뽑는 경우의 수
			count += (remains[i] * (remains[i] - 1)) / 2;
		}
		System.out.println(count);
	}
}
