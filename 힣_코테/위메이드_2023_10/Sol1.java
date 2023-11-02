package 힣_코테.위메이드_2023_10;

import java.util.ArrayList;
import java.util.Arrays;

public class Sol1 {
    public static void main(String[] args) {
        Solution sol = new Solution();
//        int[] solution = sol.solution(new int[]{1, 2, 5, 6, 4, 3}, new String[]{"Cut", "Cut", "Cut"});
//        int[] solution = sol.solution(new int[]{5, 2, 4, 6, 1, 3}, new String[]{"Riffle", "Riffle", "Riffle"});
        int[] solution = sol.solution(new int[]{2, 3, 6, 5, 4, 1}, new String[]{"Cut", "Riffle", "Cut"});
        System.out.println(Arrays.toString(solution));
    }

    private static class Solution {

        public int[] solution(int[] cards, String[] shuffles) {
            for (String shuffleStr : shuffles) {
                Shuffle shuffle = Shuffle.valueOf(shuffleStr);
                if (shuffle == Shuffle.Cut) cards = cut(cards);
                else if (shuffle == Shuffle.Riffle) cards = riffle(cards);
                else throw new IllegalArgumentException("예외 케이스");
            }
            return cards;
        }

        private int[] riffle(int[] cards) {
            int[] riffledCards = new int[cards.length];

            int leftIdx = cards.length / 2 - 1;
            int rightIdx = cards.length - 1;

            int turn = 1;
            for (int i = cards.length - 1; i >= 0; i--) {
                if (turn % 2 == 1) riffledCards[i] = cards[leftIdx--];
                else riffledCards[i] = cards[rightIdx--];
                turn++;
            }

            return riffledCards;
        }

        private int[] cut(int[] cards) {
            int mid = cards.length / 2;
            int[] cuttedCards = new int[cards.length];

            for (int i = 0; i < cards.length; i++) {
                if (i < mid) {
                    cuttedCards[i] = cards[i + mid];
                } else {
                    cuttedCards[i] = cards[i - mid];
                }
            }

            return cuttedCards;
        }
    }

    private enum Shuffle {
        Cut,
        Riffle
    }
}
