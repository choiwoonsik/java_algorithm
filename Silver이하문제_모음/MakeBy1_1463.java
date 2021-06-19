package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MakeBy1_1463 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[1000001];
        arr[2] = 1;
        arr[3] = 1;
        System.out.println(make_N(N));
    }

    private static int make_N(int n) {
        if (n <= 3)
            return arr[n];
        else if (arr[n] == 0) {
            int min = Integer.MAX_VALUE;
            if (n % 3 == 0)
                min = Math.min(min, 1 + make_N(n / 3));
            if (n % 2 == 0)
                min = Math.min(min, 1 + make_N(n / 2));
            min = Math.min(min, 1 + make_N(n - 1));
            arr[n] = min;
            return arr[n];
        }
        else
            return arr[n];
    }
}
