package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 사냥꾼_8983 {
    static int M, N, L, killCount;
    static int[] arrows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arrows = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arrows);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Dot bird = new Dot(y, x);

            binarySearch(bird);
        }
        System.out.println(killCount);
    }

    private static void binarySearch(Dot bird) {
        int dist = L - bird.y;
        if (dist < 0) return;

        int left = Math.max(bird.x - dist, 0);
        int right = bird.x + dist;

        int s = 0;
        int e = arrows.length - 1;
        int m;
        while (s <= e) {
            m = (s + e) / 2;
            if (arrows[m] <= right && arrows[m] >= left) {
                killCount++;
                break;
            }
            if (arrows[m] > right) e = m - 1;
            if (arrows[m] < left) s = m + 1;
        }
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
