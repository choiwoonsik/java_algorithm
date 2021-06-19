package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class almost_min_dis_5719 {
    static int N, M, start, end;
    static int[] span;
    static boolean[][] vst;
    static ArrayList<node>[] adj;
    static ArrayList<node>[] reverse_adj;
    static Queue<node> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == M && M == 0)
                break;

            span = new int[N + 1];
            vst = new boolean[N + 1][N + 1];
            adj = new ArrayList[N + 1];
            reverse_adj = new ArrayList[N + 1];

            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                adj[i] = new ArrayList<>();
                reverse_adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int go = Integer.parseInt(st.nextToken());
                int stop = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                adj[go].add(new node(stop, time));
                reverse_adj[stop].add(new node(go, time));
            }

            min_dijkstra(start);
            BFS(end);
            min_dijkstra(start);
            if (span[end] != Integer.MAX_VALUE)
                System.out.println(span[end]);
            else
                System.out.println(-1);
        }
    }

    private static void BFS(int end) {
        Queue<Integer> que = new LinkedList<>();
        que.add(end);

        while (!que.isEmpty())
        {
            int now = que.poll();
            if (now == start) {
                continue;
            }
            for (node next : reverse_adj[now]) {
                if (next.time + span[next.spot] == span[now]) {
                    vst[next.spot][now] = true;
                    que.add(next.spot);
                }
            }
        }
    }

    private static void min_dijkstra(int start) {
        que.clear();
        Arrays.fill(span, Integer.MAX_VALUE);
        que.add(new node(start, 0));
        span[start] = 0;

        while (!que.isEmpty())
        {
            node now = que.poll();
            if (span[now.spot] < now.time)
                continue;
            for (node next : adj[now.spot])
            {
                if (span[next.spot] > span[now.spot] + next.time && !vst[now.spot][next.spot]) {
                    span[next.spot] = span[now.spot] + next.time;
                    que.add(new node(next.spot, span[next.spot]));
                }
            }
        }
    }

    private static class node
    {
        int spot;
        int time;

        public node(int spot, int time) {
            this.spot = spot;
            this.time = time;
        }
    }
}
