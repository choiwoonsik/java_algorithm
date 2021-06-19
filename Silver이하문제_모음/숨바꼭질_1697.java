package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BFS(N, M);
    }

    private static void BFS(int n, int m) {
        Queue<move_time> pq = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        Arrays.fill(visited, false);
        pq.add(new move_time(n, 0));

        while (!pq.isEmpty()) {
            move_time now = pq.poll();
            visited[now.pos] = true;
            if (now.pos == m){
                System.out.println(now.time);
                break;
            }
            else {
                if (now.pos + 1 < 100001 && !visited[now.pos + 1])
                    pq.add(new move_time(now.pos + 1, now.time + 1));
                if (now.pos * 2 < 100001 && !visited[now.pos * 2])
                    pq.add(new move_time(now.pos * 2, now.time + 1));
                if (now.pos > 0 && !visited[now.pos - 1])
                    pq.add(new move_time(now.pos - 1, now.time + 1));
            }
        }
    }
    private static class move_time
    {
        int pos;
        int time;

        public move_time(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
}
