package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LDS_11722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] count = new int[N];
        Arrays.fill(count, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        for (int now = 0; now < N; now++)
        {
            for (int before = 0; before < now; before++)
            {
                if (arr[now] < arr[before])
                    count[now] = Math.max(count[now], count[before] + 1);
            }
        }
        int ret = 0;
        for (int i = 0; i < count.length; i++)
            ret = Math.max(ret, count[i]);
        System.out.println(ret);
    }
}
