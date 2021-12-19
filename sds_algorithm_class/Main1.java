package sds_algorithm_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1 {
    static int N;
    static int[] cards;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int count = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            cards = new int[count];
            for (int j = 0; j < count; j++) {
                cards[j] = Integer.parseInt(st.nextToken());
            }

            answer.append("#").append(i+1).append(" ").append(play()).append("\n");
            /*
            1 4 4 12 3 17 18 4

            1 3 4 4 4 12 17 18

            4 18 -> 18

            1 3 4 4 12 17

            1 17 -> 17

            3 4 4 12

            4 12 -> 12

            3 4 -> 3

1
20
3 3 5 14 5 11 17 18 1 5 13 14 3 14 18 6 2 7 13 13
             */
        }
        System.out.print(answer);
    }

    private static int play() {
        Arrays.sort(cards);

        int S = 0;
        int T = cards.length / 2;
        int last = cards.length - 1;

        while (T-- > 0) {

            boolean isMatch = false;

            for (int left = 0; left < last; left++) {
                if (cards[left] == -1) continue;
                if ((cards[left] + cards[last]) % 2 == 0) {
                    isMatch = true;
                    S += cards[last];
                    cards[left] = -1;
                    last--;
                    break;
                }
            }

            if (!isMatch) {
                for (int right = last - 1; right >= 0; right--) {
                    if (cards[right] != -1) {
                        S += cards[right];
                        cards[right] = -1;
                        last--;
                        break;
                    }
                }
            }
            while (cards[last] == -1 && last > 0) last--;
        }
        return S;
    }
}
