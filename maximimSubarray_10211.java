import java.io.*;
import java.util.*;

public class maximimSubarray_10211 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] dp = new int[N];

            dp[0] = arr[0];
            int max = dp[0];
            for (int i = 1; i < arr.length; i++) {
                dp[i] = Math.max(dp[i - 1] + arr[i] , arr[i]);
                max = Math.max(dp[i], max);
            }
            str.append(max).append("\n");
        }
        System.out.println(str);
    }
}
