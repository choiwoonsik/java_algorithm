package Silver이하문제_모음;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class 조합_2407 {
    public static void main(String[] args) throws IOException
    {
        BigInteger up, down, ret;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        m = Math.min(n - m, m);

        up = new BigInteger("1");
        down = new BigInteger("1");
        for (int i = m; i >= 1; i--) {
            up = up.multiply(BigInteger.valueOf(n));
            n--;
        }
        for (int i = m; i >= 1; i--)
            down = down.multiply(BigInteger.valueOf(i));
        ret = up.divide(down);
        System.out.println(ret);
    }
}
