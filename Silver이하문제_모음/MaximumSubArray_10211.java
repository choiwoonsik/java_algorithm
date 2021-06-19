package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MaximumSubArray_10211 {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        while (N-- > 0)
        {
            st = new StringTokenizer(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int L = 0;
            int R;
            int pSum1 =0; //i값까지의 합
            int pSum2 =0; //k < i의 값중 최소값
            int max = -1000000;
            for (R = 0; R < arr.length; R++)
            {
                //새로운 값을 계속 더해가는 pSum1
                pSum1 += arr[R];
                //새롭게 더해진 pSum1 - 그전까지의 최소 부분합 pSum2 와 그전 최대값의 비교를 통해 최대값 구하기
                max = Math.max(pSum1 - pSum2, max);
                //지금까지 더한 psum1 와 그전까지 더한 psum2중 더 작은값이 최소부분합
                pSum2 = Math.min(pSum2, pSum1);
                //최소부분합을 구해서 계속 더해진 psum1에 빼주면 최대 부분합이 나온다
            }
            System.out.println(max);
        }
    }
}
