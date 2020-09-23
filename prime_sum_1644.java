import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class prime_sum_1644 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] prime;
    public static void main(String[] args) throws IOException
    {
        int N = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        prime = new int[N];
        int start = 0;
        boolean notPrime = false;
        for (int i = 2; i <= N; i++) {
            if (i == 2) prime[start++] = i;
            else {
                for (int div = 2; div * div <= i; div++)
                    if (i % div == 0) {
                        notPrime = true;
                        break;
                    }
                if (!notPrime)
                    prime[start++] = i;
            }
            notPrime = false;
        }
        int count = 0;
        int right = 0;
        int left = 0;
        int sum = prime[right];
        if (N < sum) {
            System.out.print(0);
            return;
        }
        while(right < start) {
            if (sum < N)
                sum += prime[++right];
            else if (sum > N) {
                if (right == left)
                    sum += prime[++right];
                else
                    sum -= prime[left++];
            } else if (sum == N){
                count++;
                sum += prime[++right];
            }
        }
        System.out.print(count);
    }
}
