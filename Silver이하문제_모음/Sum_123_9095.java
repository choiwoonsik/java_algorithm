package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sum_123_9095 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[12];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < N; i++)
        {
            int n = Integer.parseInt(br.readLine());
            str.append(combination(n)).append("\n");
        }
        System.out.println(str);
    }

    private static int combination(int n) {
        if (n < 4)
            return arr[n];
        else {
            if (arr[n] == 0)
                arr[n] = combination(n-1) + combination(n-2) + combination(n-3);
            return arr[n];
        }
    }
}
