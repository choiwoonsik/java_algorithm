package 자료구조;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
양의 수보다 늑대의 수가 같거나 더 많아지면 바로 모든 양을 잡아먹어 버립니다


 */
public class 양과늑대 {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int solution = sol.solution(
                new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});
        System.out.println(solution);
    }

    private static class Solution {
        static ArrayList<Integer>[] tree;
        static boolean[][] visited;
        static int[] infos;
        static final int SHEEP = 0;
        static final int WOLF = 1;
        static int Max;

        public int solution(int[] info, int[][] edges) {
            tree = new ArrayList[info.length];
            visited = new boolean[info.length][1 << 18];
            infos = info.clone();

            for (int i = 0; i < info.length; i++) {
                tree[i] = new ArrayList<>();
            }

            makeTree(edges);
            bfs();
            return Max;
        }

        private static void bfs() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(0, 1, 0, 1));
            visited[0][1] = true;

            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                Max = Math.max(Max, cur.sheep);

                for (int next : tree[cur.idx]) {
                    int nextSheep = cur.sheep;
                    int nextWolf = cur.wolf;
                    int nextVisited = cur.visited;

                    if (infos[next] == SHEEP && (cur.visited & 1 << next) == 0) nextSheep += 1;
                    if (infos[next] == WOLF && (cur.visited & 1 << next) == 0) nextWolf += 1;

                    // 방문 체크
                    if (visited[next][nextVisited]) continue;
                    visited[next][nextVisited] = true;

                    if (nextSheep <= nextWolf) continue;
                    nextVisited |= 1 << next;
                    queue.add(new Node(next, nextSheep, nextWolf, nextVisited));
                }
            }
        }

        private void makeTree(int[][] edges) {
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];

                tree[from].add(to);
                tree[to].add(from);
            }
        }
    }

    private static class Node {
        int idx;
        int sheep;
        int wolf;
        int visited;

        public Node(int idx, int sheep, int wolf, int visited) {
            this.idx = idx;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visited = visited;
        }
    }
}
