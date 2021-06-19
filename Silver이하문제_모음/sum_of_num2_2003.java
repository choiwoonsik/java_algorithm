package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class sum_of_num2_2003 {
    static int N;
    static int target;
    static int[] numbers;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        int tmp[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        target = tmp[1];
        numbers = new int[N + 1];
        tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++)
            numbers[i] = tmp[i];
        int front = 0;
        int back = 0;
        int sum = numbers[0];
        int count = 0;
        while (front < N)
        {
            if (sum < target)
                sum += numbers[++front];
            else if (sum > target) {
                if (front == back)
                    sum += numbers[++front];
                else
                    sum -= numbers[back++];
            }
            else if (sum == target) {
                count++;
                sum += numbers[++front];
            }
        }
        System.out.print(count);
    }
}
