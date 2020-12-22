public class WoTechCo_TEST_7 {
    public static void main(String[] args) {

    }
    public static int[][] solution(int n, boolean horizontal) {
        int[][] answer = {};

        int count = 0;
        int[][] board = new int[n][n];
        if (horizontal)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < i; j++)
                {
                    board[i][j] = count;
                }
            }
        }

        return answer;
    }
}
