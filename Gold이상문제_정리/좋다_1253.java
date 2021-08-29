package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 좋다_1253 {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        int ans = solve();
        System.out.print(ans);
    }

    private static int solve() {
        int GOOD = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (makeNum(nums[i], i)) GOOD++;
        }
        return GOOD;
    }

    private static boolean makeNum(int num, int idx) {
        int left = 0;
        int right = N - 1;
        int sum;

        while (left < right) {

            if (right == idx) {
                right--;
                continue;
            } else if (left == idx) {
                left++;
                continue;
            }

            sum = nums[left] + nums[right];

            if (sum < num) {
                left++;
            } else if (sum > num) {
                right--;
            }
            else return true;
        }
        return false;
    }
}
