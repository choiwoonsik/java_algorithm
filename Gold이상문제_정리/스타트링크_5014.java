package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
10 1 10 2 1
 */
public class 스타트링크_5014 {
    static int F, S, G, U, D, answer = 100001;
    static boolean[] visited;
    static int[] count;

    private static class Unit {
        int click, pos;

        public Unit(int click, int pos) {
            this.click = click;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        count = new int[F + 1];
        Arrays.fill(count, Integer.MAX_VALUE);

        useElevator();

        if (count[G] != Integer.MAX_VALUE) System.out.print(count[G]);
        else System.out.print("use the stairs");
    }

    private static void useElevator() {

        Queue<Unit> queue = new LinkedList<>();
        queue.add(new Unit(0, S));
        count[S] = 0;

        while (!queue.isEmpty()) {
            Unit now = queue.poll();

            if (now.pos == G) break;

            if (now.pos + U <= F && count[now.pos + U] > now.click + 1) {
                count[now.pos + U] = count[now.pos] + 1;
                queue.add(new Unit(now.click + 1, now.pos + U));
            }

            if (now.pos - D > 0 && count[now.pos - D] > now.click + 1) {
                count[now.pos - D] = count[now.pos] + 1;
                queue.add(new Unit(now.click + 1, now.pos - D));
            }
        }
    }
}
