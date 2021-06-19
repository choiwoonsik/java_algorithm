package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class find_min2_11003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int Arraylen = Integer.parseInt(st.nextToken());
        int part = Integer.parseInt(st.nextToken());
        int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int R;
        int L = 0;
        int len = 0;
        int MIN = Integer.MAX_VALUE;
        for (R = 0; R < Arraylen; R++)
        {
            MIN = MIN < numArr[R] ? MIN : numArr[R];
            len++;
            if (len == part)
            {

            }
        }
    }
}
