import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수들의합2_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int L = 0;
        int R = 0;

        int sum = 0;
        int cnt = 0;
        while (R < N) {
            sum += nArr[R];
            if (sum == M)
                cnt++;
            else if (sum > M) {
                while (L <= R && sum > M) {
                    sum -= nArr[L];
                    L++;
                    if (sum == M)
                        cnt++;
                }
            }
            R++;
        }
        System.out.println(cnt);
    }
}
