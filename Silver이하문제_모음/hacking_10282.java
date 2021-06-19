package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class hacking_10282 {
    static ArrayList<node>[] map;
    static int[] timeArr;
    static int n, d, c, a, b, s, Time, Cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder str = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0)
        {
            map = new ArrayList[10001];
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= n; i++)
                map[i] = new ArrayList<>();

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());
                map[b].add(new node(a, s));
            }

            timeArr = new int[n + 1];
            Time = 0;
            Cnt = 0;
            BFS(c);
            for (int i = 1; i <= n; i++) {
                if (timeArr[i] != Integer.MAX_VALUE) {
                    Cnt++;
                    Time = Math.max(Time, timeArr[i]);
                }
            }
            str.append(Cnt).append(" ").append(Time).append("\n");
        }
        System.out.println(str);
    }

    private static void BFS(int c) {
        Queue<node> que = new LinkedList<>();
        Arrays.fill(timeArr, Integer.MAX_VALUE);
        que.add(new node(c, 0));
        timeArr[c] = 0;
        while (!que.isEmpty()) {
            node now = que.poll();
            for (node next : map[now.com]) {
                if (timeArr[next.com] > timeArr[now.com] + next.s) {
                    timeArr[next.com] = timeArr[now.com] + next.s;
                    que.add(new node(next.com, timeArr[next.com]));
                }
            }
        }
    }

    private static class node
    {
        int com;
        int s;

        public node(int com, int s) {
            this.com = com;
            this.s = s;
        }
    }
}
