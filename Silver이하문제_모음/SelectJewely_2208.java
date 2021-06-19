package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SelectJewely_2208 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        int n[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = n[0];
        int MIN = n[1];
        int[] jew = new int[N];
        int L = 0;
        int sum = 0;
        int max = 0;
        int len = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            jew[i] = Integer.parseInt(st.nextToken());

        for (int R = 0; R < N; R++)
        {
            sum += jew[R];
            len++;
            if (len >= MIN)
            {
                //처음 담은 보석이 음수이고 다음 보석이 더 가치있다면
                while (R < N - 1 && jew[R+1] >= jew[L] && jew[L] < 0)
                {
                    R++;
                    sum -= jew[L];
                    sum += jew[R];
                }
                if (sum > max)
                    max = sum;
                while (R < N && jew[R] < 0)
                {
                    sum += jew[R++];
                    len++;
                }
                while (L <= R && sum < max) {
                    sum -= jew[L++];
                    len--;
                }
            }

        }
    }
}
