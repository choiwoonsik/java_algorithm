import java.io.*;
import java.util.*;

public class 다리놓기_1010 {
    public static void main(String[] args) throws IOException
    {
        /*
        순서를 가지고 M개의 다리중에서 N개의 다리뽑기  - 조합
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for (int t = 0; t < N; t++)
        {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            //mCn
            n = Math.min(m - n, n);
            long up = 1;
            for (int count = n; count > 0; count--)
            {
                up *= m;
                m--;
            }
            long down = 1;
            while (n >= 1)
            {
                down *= n;
                n--;
            }
            long ret = up / down;
            sb.append(ret).append("\n");
        }
        System.out.print(sb.toString());
    }
}
