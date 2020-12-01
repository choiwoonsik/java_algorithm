import java.io.BufferedReader;

public class programmers_block_moving {
    static int[][] board;
    static int[] dy = {-1, 0, 1};
    static int[] dx = {0, 1, 0};
    static int N;
    public static void main(String[] args) {
        int[][] board = {};
        sol(board);
    }

    private static int sol(int[][] board) {
        int ret = 0;

        Dot tail = new Dot(0, 0);
        Dot head = new Dot(0, 1);

        N = board.length;
        DFS(tail, head);

        return ret;
    }

    private static void DFS(Dot tail, Dot head) {
        if ((tail.y == N - 1 && tail.x == N - 1)
        || (head.y == N - 1 && head.x == N - 1)) {
            return;
        }
        for (int i = 0; i < 3; i++)
        {
            int nHeadY = head.y + dy[i];
            int nHeadX = head.x + dx[i];
            int nTailY = tail.y + dy[i];
            int nTailX = tail.x + -dx[i];
            if (nHeadX >= 0 && nHeadX < N && nHeadY >= 0 && nHeadY < N
            && nTailX >= 0 && nTailX < N && nTailY >= 0 && nTailY < N) {
                DFS(new Dot(nTailY, nTailX), new Dot(nHeadY, nHeadX));
            }
        }
    }

    private static class Dot
    {
        int y;
        int x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
