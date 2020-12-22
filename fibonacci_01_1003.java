import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class fibonacci_01_1003 {
    static int[] arr;
    static int[] countZero;
    static int[] countOne;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[41];
        countZero = new int[41];
        countOne = new int[41];

        Arrays.fill(countZero, 0);
        Arrays.fill(countOne, 0);

        arr[0] = 0;
        arr[1] = 1;
        countZero[0] = 1;
        countZero[1] = 0;
        countOne[0] = 0;
        countOne[1] = 1;

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < N; i++)
        {
            int n = Integer.parseInt(br.readLine());
            fiboZ(n);
            fiboO(n);
            str.append(countZero[n]).append(" ").append(countOne[n]).append("\n");
        }
        System.out.println(str);
    }

    private static int fiboO(int n) {
        if (n <= 1)
            return countOne[n];
        else {
            if (countOne[n] == 0)
                countOne[n] = fiboO(n-1) + fiboO(n-2);
            return countOne[n];
        }
    }

    private static int fiboZ(int n) {
        if (n <= 1)
            return countZero[n];
        else {
            if (countZero[n] == 0)
                countZero[n] = fiboZ(n-1) + fiboZ(n-2);
            return countZero[n];
        }
    }
}
