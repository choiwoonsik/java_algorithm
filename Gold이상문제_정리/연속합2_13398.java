package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 연속합2_13398 {
    static int[] nArr;
    static int[] dp1;
    static int[] dp2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int ans;

        nArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp1 = new int[N];
        dp1[0] = nArr[0];
        ans = nArr[0];
        for (int i = 1; i < N; i++) {
            dp1[i] = Math.max(dp1[i - 1] + nArr[i], nArr[i]);
            ans = Math.max(ans, dp1[i]);
        }

        dp2 = new int[N];
        dp2[N - 1] = nArr[N - 1];
        for (int i = N - 2; i >= 0; i--)
            dp2[i] = Math.max(dp2[i + 1] + nArr[i], nArr[i]);

        for (int i = 1; i < N - 1; i++)
            ans = Math.max(dp1[i - 1] + dp2[i + 1], ans);
        System.out.println(ans);
    }
}
