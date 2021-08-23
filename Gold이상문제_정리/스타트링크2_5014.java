package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
10 1 10 2 1
 */
public class 스타트링크2_5014 {
    static int F, S, G, U, D, answer = -1;
    static int[] clicked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        clicked = new int[F + 1];
        Arrays.fill(clicked, Integer.MAX_VALUE);
        clicked[S] = 0;

        useElevator(S);

        if (answer != -1) System.out.println(answer);
        else System.out.println("use the stairs");
    }

    private static void useElevator(int start) {
        if (start == G) {
            answer = clicked[start];
            return;
        }

        if (start + U <= F && clicked[start + U] > clicked[start] + 1) {
            clicked[start + U] = clicked[start] + 1;
            useElevator(start + U);
        }

        if (start - D > 0 && clicked[start - D] > clicked[start] + 1) {
            clicked[start - D] = clicked[start] + 1;
            useElevator(start - D);
        }
    }
}