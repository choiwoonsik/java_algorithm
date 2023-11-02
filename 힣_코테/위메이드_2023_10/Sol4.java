package 힣_코테.위메이드_2023_10;

import java.util.*;
import java.util.stream.Collectors;

public class Sol4 {
    public static void main(String[] args) {
        int[][] games = {
                {66000, 0, 50},
                {16000, 2, 10},
                {84500, 3, 20},
                {5500, 2, 75}
        };
        int result = solution(games);
        System.out.println(result); // 출력 결과: 117975
    }

    static int mergeSize = 0;

    public static int solution(int[][] games) {
        List<Game> gameList = new ArrayList<>();

        for (int[] game : games) {
            Game g = new Game(game[0], game[1], game[2]);
            gameList.add(g);
        }

        int maxDiscountDay = gameList.stream().map(game -> game.discountDay).max(Integer::compareTo).orElse(0);
        mergeSize = gameList.size() - 1 - maxDiscountDay;

        Map<Integer, List<Game>> dayPerGame = gameList.stream().collect(Collectors.groupingBy(game -> game.discountDay));

        for (int i = 0; i < maxDiscountDay; i++) {
            if (dayPerGame.get(i) == null) continue;
            if (dayPerGame.get(i).size() == 1)
                continue;
        }

        return 0;
    }

    private static class Game {
        public Game(int price, int discountDay, int percent) {
            this.price = price;
            this.discountDay = discountDay;
            this.percent = percent;
        }

        int price;
        int discountDay;
        int percent;
    }
}
