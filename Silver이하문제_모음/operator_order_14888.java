package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class operator_order_14888 {
    static int N;
    static int max = -2000000000, min = 2000000000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] operator_n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sum = arr[0];
        int plus = operator_n[0], minus = operator_n[1], mul = operator_n[2], div = operator_n[3];
        recursive(arr,1, sum, plus, minus, mul, div);
        System.out.print(max+"\n"+min);
    }
    private static void recursive(int[] arr, int start, int sum, int plus, int minus, int mul, int div)
    {
        if (start == N)
        {
            if (max < sum)
                max = sum;
            if (min > sum)
                min = sum;
            return;
        }
        if (plus > 0) recursive(arr, start + 1, sum + arr[start], plus - 1, minus, mul, div);
        if (minus > 0) recursive(arr, start + 1, sum - arr[start], plus, minus - 1, mul, div);
        if (mul > 0) recursive(arr, start + 1, sum * arr[start], plus, minus, mul - 1, div);
        if (div > 0) recursive(arr, start + 1, sum / arr[start], plus, minus, mul, div - 1);
    }
}
