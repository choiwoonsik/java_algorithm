package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class part_sum_1806 {
    static int N;
    static int target;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = n[0];
        target = n[1];
        arr = new int[N + 1];
        n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++)
            arr[i] = n[i];
        int len = 1;
        int min = N;
        int right = 0;
        int left = 0;
        int sum = arr[left];
        boolean changed = false;
        while (right < N)
        {
            if (sum < target) {
                sum += arr[++right];
                ++len;
            }
            else if (sum >= target)
            {
                changed = true;
                if (len < min)
                    min = len;
                if (right == left) {
                    sum += arr[++right];
                    ++len;
                }
                else {
                    sum -= arr[left++];
                    --len;
                }
            }
        }
        if (changed) {
            System.out.print(min);
            return;
        }
        System.out.print(0);
    }
}
