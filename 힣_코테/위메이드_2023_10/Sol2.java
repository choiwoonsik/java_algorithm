package 힣_코테.위메이드_2023_10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sol2 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String solution = sol.solution(new String[]{
                "110111101111111111110111",
                "010101101100101101010100",
                "010111111111101111010111",
                "010001001001101101010001",
                "111111001111111111111111"
        });
        System.out.println(solution);
    }

    static int[][] number;
    static Map<Integer, List<Integer>> numberMap = new HashMap<>();
    static int[] numbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    private static class Solution {

        public String solution(String[] pixels) {
            StringBuilder sb = new StringBuilder();

            makeAnswerNumberMap();
            for (int time = 0; time < pixels[0].length() / 3; time++) {
                makeNumber(pixels, time);
                int num = decideNumber();
                sb.append(num);
            }

            return sb.toString();
        }

        private static void makeNumber(String[] pixels, int time) {
            number = new int[5][3];
            for (int i = 0; i < 3; i++) {
                int pixelIdx = time * 3 + i;

                for (int j = 0; j < pixels.length; j++) {
                    String pixelLine = pixels[j];
                    number[j][i] = pixelLine.charAt(pixelIdx) - '0';
                }
            }
        }

        private int decideNumber() {
            int answer = 0;
            for (int i = 0; i < 10; i++) {
                List<Integer> numberBlankList = numberMap.get(i);
                boolean isSame = true;

                for (int blankPos : numberBlankList) {
                    int y = blankPos / 3;
                    int x = blankPos % 3;
                    if (number[y][x] != 0) {
                        isSame = false;
                        break;
                    }
                }
                if (!isSame) break;

                for (int writePos : numbers) {
                    if (numberBlankList.contains(writePos)) continue;

                    int y = writePos / 3;
                    int x = writePos % 3;
                    if (number[y][x] == 0) {
                        isSame = false;
                        break;
                    }
                }

                if (isSame) {
                    answer = i;
                    break;
                }
            }

            return answer;
        }

        /*
        0, 1, 2
        3, 4, 5
        6, 7, 8
        9, 10, 11
        12, 13, 14
         */
        private void makeAnswerNumberMap() {
            numberMap.put(
                    0,
                    List.of(4, 7, 10)
            );
            numberMap.put(
                    1,
                    List.of(
                            2, 3, 5, 6, 7, 8, 9, 11
                    )
            );
            numberMap.put(
                    2,
                    List.of(
                            3, 4, 10, 11
                    )
            );
            numberMap.put(
                    3,
                    List.of(
                            3, 4, 9, 10
                    )
            );
            numberMap.put(
                    4,
                    List.of(
                            1, 4, 9, 10, 12, 13
                    )
            );
            numberMap.put(
                    5,
                    List.of(
                            4, 5, 9, 10
                    )
            );
            numberMap.put(
                    6,
                    List.of(
                            4, 5, 10
                    )
            );
            numberMap.put(
                    7,
                    List.of(
                            4, 6, 7, 9, 10, 12, 13
                    )
            );
            numberMap.put(
                    8,
                    List.of(
                            4, 10
                    )
            );
            numberMap.put(
                    9,
                    List.of(
                            4, 9, 10
                    )
            );
        }
    }
}
