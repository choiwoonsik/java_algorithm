package Gold이상문제_정리;

/*
4
1 10
3 15
1 3
4 8

10
1 10
1 10
2 10
3 10
1 9
1 8
1 7
2 3
3 1
3 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class 컬러볼_10800 {
    static ArrayList<Ball> balls = new ArrayList<>();
    static int[] sizePerColor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        sizePerColor = new int[N + 1];
        int[] playerSum = new int[N];

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");

            int color = Integer.parseInt(s[0]);
            int size = Integer.parseInt(s[1]);

            balls.add(new Ball(i, color, size));
        }
        balls.sort(Comparator.comparingInt(b -> b.size));

        StringBuilder answer = new StringBuilder();
        int totalSum = 0;
        int idx = 0;
        for (Ball nowBall : balls) {

            while (balls.get(idx).size < nowBall.size) {

                totalSum += balls.get(idx).size;
                sizePerColor[balls.get(idx).color] += balls.get(idx).size;
                idx++;
            }

            playerSum[nowBall.i] = totalSum - sizePerColor[nowBall.color];
        }

        for (int sum : playerSum) answer.append(sum).append("\n");
        System.out.print(answer);
    }

    private static class Ball
    {
        int i;
        int color;
        int size;

        public Ball(int i, int color, int size) {
            this.i = i;
            this.color = color;
            this.size = size;
        }
    }
}
