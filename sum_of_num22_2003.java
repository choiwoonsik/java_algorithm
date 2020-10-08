import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sum_of_num22_2003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int target;
    static int[] N;
    public static void main(String[] args) throws IOException
    {
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = new int[n[0]];
        target = n[1];
        N = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int L = 0;
        int R;
        int sum = 0;
        int cnt = 0;
        for (R = 0; R < N.length; R++)
        {
            sum += N[R];
            if (sum == target)
                cnt++;
            else if (sum > target) {
                while (sum > target && L <= R) {
                    sum -= N[L];
                    L++;
                    if (sum == target)
                        cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
