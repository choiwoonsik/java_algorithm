package Gold이상문제_정리;

import java.util.*;

public class kakao_카드짝맞추기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(new int[][]{{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}}, 1, 0);
    }

    /*
    유저가 카드를 2장 선택하여 앞면으로 뒤집었을 때 같은 그림이 그려진 카드면 해당 카드는 게임 화면에서 사라지며,
    같은 그림이 아니라면 원래 상태로 뒷면이 보이도록 뒤집힙니다.
    -> 모두 사라지면 끝

    방향키로 카드선택
    - 카드가 없으면 해당 방향의 끝으로 이동
    - 사각형을 벗어나는 방향은 움지이지 않음

    Enter 입력시 뒤집음

    1. 어떤 카드부터 지울지 우선순위 정함
    2. 현재 좌표로 부터 우선순위에 해당하는 카드를 지우러 ㄱㄱ
    */
    private static class Solution {
        static int[][] Board = new int[4][4];
        static int INF = 987654321;
        static int R, C;

        private static class Point {
            int row, col, count;

            Point(int r, int c, int t) {
                row = r;
                col = c;
                count = t;
            }
        }

        public int solution(int[][] board, int r, int c) {
            Board = board;
            R = r;
            C = c;

            return permutation(new Point(r, c, 0));
        }

        private int permutation(Point now) {
            int ret = INF;
            ArrayList<Point> points = new ArrayList<>();

            for (int k = 1; k < 9; k++) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (Board[i][j] == k) points.add(new Point(i, j, 0));
                    }
                }
                // no num
                if (points.size() == 0) continue;

                int goFirst = bfs(now, points.get(0)) + bfs(points.get(0), points.get(1)) + 2;
                int goSecond = bfs(now, points.get(1)) + bfs(points.get(1), points.get(0)) + 2;

                for (Point point : points) {
                    Board[point.row][point.col] = 0;
                }

                ret = Math.min(ret, goFirst + permutation(points.get(1))); // goFirst
                ret = Math.min(ret, goSecond + permutation(points.get(0))); // goSecond

                for (Point point : points) {
                    Board[point.row][point.col] = k;
                }
            }
            return ret;
        }

        private int bfs(Point start, Point next) {
            boolean[][] visited = new boolean[4][4];
            int[] dy = {-1, 1, 0, 0};
            int[] dx = {0, 0, -1, 1};
            PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.count));
            pq.add(start);

            while (!pq.isEmpty()) {
                Point now = pq.poll();

                if (now.col == next.col && now.row == next.row) {
                    return now.count;
                }

                for (int d = 0; d < 4; d++) {
                    int ny = now.row + dy[d];
                    int nx = now.col + dx[d];

                    if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4) continue;

                    // 한칸 이동
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        pq.add(new Point(ny, nx, now.count + 1));
                    }

                    // 끝까지 이동
                    for (int m = 0; m < 2; m++) {
                        if (ny + dy[d] < 0 || nx + dx[d] < 0 || ny + dy[d] > 3 || nx + dx[d] > 3) {
                            break;
                        }
                        if (Board[ny][nx] != 0) {
                            break;
                        }
                        ny += dy[d];
                        nx += dx[d];
                    }
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        pq.add(new Point(ny, nx, now.count + 1));
                    }
                }
            }
            return -1;
        }
    }
}
