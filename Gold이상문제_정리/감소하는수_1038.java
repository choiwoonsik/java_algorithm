package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 감소하는수_1038 {
    static int N;
    public static void main(String[] args) throws IOException {

        input();
        if (N < 10) System.out.println(N);
        else {
            String dfs = dfs("10", 10);
            System.out.println(dfs);
        }
    }

    private static String dfs(String number, int idx) {

        // 9876543210
        if (number.equals("9876543210") && N > idx) {
            return "-1";
        } else if (idx == N) {
            return number;
        }

        char[] nums = number.toCharArray();
        int pos = -1;

        for (int i = nums.length - 1; i > 0; i--) {
            // 310
            if (nums[i - 1] - '0' > nums[i] - '0' + 1 && nums[i] - '0' != 9) {
                pos = i;
                break;
            }
        }

        // 1차이로 증가
        if (pos == -1) {
            if (nums[0] - '0' != 9) { // 수를 증가 시킨다.
                for (int i = 1; i < nums.length; i++) {
                    nums[i] = (char) (nums.length - i - 1 + '0');
                }
                nums[0]++;
                return dfs(makeStr(nums), idx + 1);
            }
            else { // 자릿수를 증가시킨다.
                StringBuilder tmp = new StringBuilder();
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = (char) (nums.length - i - 1 + '0');
                }
                tmp.append(nums.length);
                tmp.append(nums);
                return dfs(tmp.toString(), idx + 1);
            }
        }
        // 그 이상으로 증가
        else {
            for (int i = pos + 1; i < nums.length; i++) {
                nums[i] = (char) (nums.length - i - 1 + '0');
            }
            nums[pos]++;
            return dfs(makeStr(nums), idx + 1);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    private static String makeStr(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }
}
