package sds_algorithm_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main4 {
    static int N, M;
    static int A, B;
    static int aT, bT;
    static ArrayList<Integer>[] adj;
    static boolean[][] isPossible;
    static boolean[] isOccupied;
    static boolean[] aVisited;
    static boolean[] bVisited;
    static boolean[] isMid;
    static Queue<Integer> aQ;
    static Queue<Integer> bQ;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()) + 1;
            M = Integer.parseInt(st.nextToken()) + 1;
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            isPossible = new boolean[N][N];
            adj = new ArrayList[N];
            for (int j = 1; j < N; j++) adj[j] = new ArrayList<>();

            for (int j = 1; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                isPossible[from][to] = true;
                isPossible[to][from] = true;
                adj[from].add(to);
                adj[to].add(from);
            }
            fight(i);
        }
        System.out.print(answer);
    }

    private static void fight(int t) {
        aQ = new LinkedList<>();
        bQ = new LinkedList<>();
        isMid = new boolean[N];
        aVisited = new boolean[N];
        bVisited = new boolean[N];
        aQ.add(A);
        bQ.add(B);
        aT = 1;
        bT = 1;
        aVisited[A] = true;
        bVisited[B] = true;

        while (!aQ.isEmpty() && !bQ.isEmpty()) {
            isOccupied = new boolean[N];
            a_occupy();
            b_occupy();
        }

        int midCount = 0;
        int noccupy = 0;
        for (int i = 1; i < N; i++) {
            if (isMid[i]) {
                midCount++;
                continue;
            }
            if (!aVisited[i] && !bVisited[i]) {
                noccupy++;
            }
        }

        answer.append("#").append(t).append(" ");
        answer.append(midCount).append(" ");
        int need;
        if (((N - 1) % 2) == 0)
            need = (N / 2) + 1;
        else need = N / 2;
        if (aT >= need) answer.append(0);
        else if (need - (aT + midCount + noccupy) > 0) {
            answer.append("-1");
        } else {
            answer.append(need - aT);
        }
        answer.append("\n");
    }

    private static void a_occupy() {
        int t = aQ.size();
        while (t-- > 0)
        {
            if (aQ.isEmpty()) break;
            int now = aQ.poll();
            if (isMid[now]) continue;

            for (int next : adj[now]) {
                if (aVisited[next] || bVisited[next]) continue;
                if (isMid[next]) continue;
                if (!isOccupied[next]) {
                    isOccupied[next] = true;
                    aQ.add(next);
                    aT++;
                }
                aVisited[next] = true;
            }
        }
    }

    private static void b_occupy() {
        int t = bQ.size();
        while (t-- > 0)
        {
            if (bQ.isEmpty()) break;
            int now = bQ.poll();

            for (int next : adj[now]) {
                if (bVisited[next] || next == A) continue;
                if (isMid[next]) continue;
                if (!isOccupied[next]) {
                    isOccupied[next] = true;
                    bQ.add(next);
                    bT++;
                }
                else {
                    isMid[next] = true;
                    aT--;
                }
                bVisited[next] = true;
            }
        }
    }
}
