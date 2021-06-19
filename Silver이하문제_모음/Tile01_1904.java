package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Tile01_1904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[1000001];
        Arrays.fill(arr, 0);

        arr[1] = 1;
        arr[2] = 2;

        for (int i = 3; i <= N; i++)
            arr[i] = (arr[i - 1] + arr[i - 2]) % 15746;

        int ret = arr[N];
        //ret = find_all(arr, N);
        System.out.println(ret);
    }

    private static int find_all(int[] arr, int n) {
        if (n <= 2)
            return arr[n];
        else {
            if (arr[n] == 0)
                arr[n] = (find_all(arr, n - 1) + find_all(arr, n - 2)) % 15746;
            return arr[n];
        }
    }
}
