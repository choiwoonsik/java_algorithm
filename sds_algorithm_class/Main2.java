package sds_algorithm_class;

import java.io.*;

public class Main2 {
    static int T;
    static int N;
    static int L;
    static Card[] cards;
    static boolean[][] usedCard;

    private static class Card {
        char count;
        char color;
        char shape;
        char shadow;

        public Card(char count, char color, char shape, char shadow) {
            this.count = count;
            this.color = color;
            this.shape = shape;
            this.shadow = shadow;
        }
    }

    private static void comb() {
        L = 0;
        usedCard = new boolean[729][N];
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    int[] tmpCard = new int[]{i, j, k};
                    if (checkSetCondition(tmpCard)) {
                        usedCard[L][i] = true;
                        usedCard[L][j] = true;
                        usedCard[L][k] = true;
                        L++;
                    }
                }
            }
        }
    }

    private static boolean checkSetCondition(int[] myCard) {
        Card one = cards[myCard[0]];
        Card two = cards[myCard[1]];
        Card thr = cards[myCard[2]];

        if (!check(one.shape, two.shape, thr.shape)) return false;
        if (!check(one.color, two.color, thr.color)) return false;
        if (!check(one.shadow, two.shadow, thr.shadow)) return false;
        if (!check(one.count, two.count, thr.count)) return false;
        return true;
    }

    private static boolean check(char one, char two, char thr) {
        if (one == two && two == thr) return true;
        if (one != two && two != thr) return true;
        return false;
    }

    private static int findMax() {
        int max = 0;
        int count;

        for (int i = 0; i < L - 1; i++) {
            count = 1;
            boolean[] firstCard = usedCard[i].clone();

            for (int j = i + 1; j < L; j++) {
                boolean isMatch = true;

                for (int k = 0; k < N; k++) {
                    if (firstCard[k] && firstCard[k] == usedCard[j][k]) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    max = Math.max(++count, max);
                    for (int k = 0; k < N; k++) {
                        if (usedCard[j][k]) firstCard[k] = usedCard[j][k];
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            cards = new Card[N];

            for (int j = 0; j < N; j++) {
                String line = br.readLine();
                Card card = new Card(line.charAt(0), line.charAt(1), line.charAt(2), line.charAt(3));
                cards[j] = card;
            }
            comb();
            answer.append("#").append(i + 1).append(" ").append(findMax()).append("\n");
        }
        System.out.print(answer);
    }
}
