package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 나머지합_10986 {
    static int N, M;
    static long[] remains;
    public static void main(String[] args) throws IOException {
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
            sum += n;
            remains[(int)(sum % M)]++;
        }

        long count = 0;
        count += remains[0];
        for (int i = 0; i < M; i++) {
            if (remains[i] == 0)
                continue;
            count += (remains[i] * (remains[i] - 1)) / 2; // (n(n+1)) / 2
        }
        System.out.println(count);
    }
}
