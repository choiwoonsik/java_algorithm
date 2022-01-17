import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
2
4
1 2 5 2
9
1 1 1 1 2 2 2 2 2

 */
public class 카드게임_11062 {
    static int T;
    static int N;
    static int Max;
    static int[][] dp;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            Max = 0;
            nums = new int[N];
            dp = new int[N][N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            answer.append(solution()).append("\n");
        }
        System.out.print(answer);
    }

    private static int solution() {
        int left = 0;
        int right = N - 1;
        boolean turn = true;

        return backtracking(turn, left, right);
    }

    private static int backtracking(boolean myTurn, int left, int right) {
        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        if (left == right) {
            if (myTurn) return nums[left];
            else return 0;
        }


        if (myTurn) {
            dp[left][right] = Math.max(
                    nums[left] + backtracking(false, left + 1, right),
                    nums[right] + backtracking(false, left, right - 1));
        } else {
            dp[left][right] = Math.min(
                    backtracking(true, left + 1, right),
                    backtracking(true, left, right - 1));
        }
        return dp[left][right];
    }
}
