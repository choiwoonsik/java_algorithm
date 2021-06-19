package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prime_sum2_1644 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int target;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        target = Integer.parseInt(st.nextToken());
        int[] sosu = new int[target];
        int pos = 0;
        boolean isSosu;

        for (int i = 2; i <= target; i++)
        {
            if (i == 2) {
                sosu[pos] = i;
                pos++;
            }
            else{
                int j;
                isSosu = true;
                for (j = 2; j <= Math.sqrt(i); j++){
                    if (i % j == 0) {
                        isSosu = false;
                        break;
                    }
                }
                if (isSosu) {
                    sosu[pos] = i;
                    pos++;
                }
            }
        }
        int L = 0;
        int R;
        int sum = 0;
        int cnt = 0;
        for (R = 0; R < pos; R++)
        {
            sum += sosu[R];
            while (sum > target && L <= R)
            {
                sum -= sosu[L];
                L++;
            }
            if (sum == target)
                cnt++;
        }
        System.out.println(cnt);
    }
}
