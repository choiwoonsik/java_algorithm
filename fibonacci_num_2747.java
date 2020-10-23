import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class fibonacci_num_2747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int target = Integer.parseInt(st.nextToken());

        int[] arr = new int[46];
        Arrays.fill(arr, 0);
        arr[0] = 0;
        arr[1] = 1;
        int result = fibo(arr, target);
        System.out.println(result);
    }
    private static int fibo(int[] arr, int n)
    {
        if (n < 2)
            return arr[n];
        else {
            if (arr[n] == 0)
                arr[n] = fibo(arr, n-1) + fibo(arr, n - 2);
            return arr[n];
        }
    }
}
