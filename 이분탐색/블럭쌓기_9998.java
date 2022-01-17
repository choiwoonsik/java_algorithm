package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 블럭쌓기_9998 {
    static int N;
    static long[] boardOne;
    static long[] boardTwo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        boardOne = new long[N];
        boardTwo = new long[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long h1 = Long.parseLong(st1.nextToken());
            boardOne[i] = h1;
            long h2 = Long.parseLong(st2.nextToken());
            boardTwo[i] = h2;
        }

        solution();
    }

    private static void solution() {
        long left = 0;
        long right = (long) Math.pow(10, 12) - N / 2;
        long maxBlockCount = countBlock(right);
        long minBlockCount = countBlock(left);
        long mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (minBlockCount < maxBlockCount) // 낮은 곳 블럭개수가 더 적음. 낮춘다
            {
                right = mid;
                maxBlockCount = countBlock(right);
            } else { // 높은 곳 블럭개수가 더 작거나 같음. 높인다
                left = mid + 1;
                minBlockCount = countBlock(left);
            }
        }
        System.out.println(minBlockCount);
    }

    private static long countBlock(long midH) {
        long total = 0;
        long h = midH;
        for (int i = N / 2; i >= 0; i--) {
            total += Math.abs(boardOne[i] - h);
            total += Math.abs(boardTwo[i] - h);
            h++;
        }
        h = midH + 1;
        for (int i = N / 2 + 1; i < N; i++) {
            total += Math.abs(boardOne[i] - h);
            total += Math.abs(boardTwo[i] - h);
            h++;
        }
        return total;
    }
}
