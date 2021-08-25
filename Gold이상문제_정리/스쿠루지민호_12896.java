package Gold이상문제_정리;

/*
5
4 5
4 2
2 3
1 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스쿠루지민호_12896 {
    static int N, last = -1, maxMove;
    static ArrayList<Node>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, 1));
            adj[v].add(new Node(u, 1));
        }

        DFS(new Node(1, 0));
        Arrays.fill(visited, false);
        DFS(new Node(last, 0));

        System.out.println((maxMove + 1) / 2);
    }

    private static void DFS(Node now) {
        if (visited[now.pos]) return;
        visited[now.pos] = true;

        if (now.move > maxMove) {
            last = now.pos;
            maxMove = now.move;
        }

        for (Node next : adj[now.pos]) {
            if (!visited[next.pos]) {
                DFS(new Node(next.pos, next.move + now.move));
            }
        }
    }

    private static class Node {
        int pos;
        int move;

        public Node(int pos, int move) {
            this.pos = pos;
            this.move = move;
        }
    }
}
