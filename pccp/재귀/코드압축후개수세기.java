package pccp.재귀;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class 코드압축후개수세기 {
    public static void main(String[] args) {
        int[] solution = new Solution().solution(
                new int[][]{
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 1},
                        {1, 1, 1, 1}
                }
        );
        System.out.println(Arrays.toString(solution));
    }

    private static class Solution {
        public int[] solution(int[][] arr) {
            List<Integer> integers = quadCompress(arr);
            Integer[] array = integers.toArray(new Integer[0]);

            int zero = (int) Arrays.stream(array)
                    .mapToInt(Integer::intValue)
                    .filter(it -> it == 0)
                    .count();

            int one = (int) Arrays.stream(array)
                    .mapToInt(Integer::intValue)
                    .filter(it -> it == 1)
                    .count();

            return new int[]{zero, one};
        }

        private List<Integer> quadCompress(int[][] arr) {
            if (isAllSame(arr)) {
                return List.of(arr[0][0]);
            } else if (arr.length == 1) {
                return List.of(arr[0][0]);
            } else {
                ArrayList<Integer> nums = new ArrayList<>();
                int[][] leftUp = leftUp(arr);
                int[][] rightUp = rightUp(arr);
                int[][] leftDown = leftDown(arr);
                int[][] rightDown = rightDown(arr);
                nums.addAll(quadCompress(leftUp));
                nums.addAll(quadCompress(rightUp));
                nums.addAll(quadCompress(leftDown));
                nums.addAll(quadCompress(rightDown));

                return nums;
            }
        }

        private int[][] leftUp(int[][] arr) {
            int size = arr.length / 2;
            int[][] copyMap = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    copyMap[i][j] = arr[i][j];
                }
            }
            return copyMap;
        }

        private int[][] rightUp(int[][] arr) {
            int size = arr.length / 2;
            int[][] copyMap = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    copyMap[i][j] = arr[i][j + size];
                }
            }

            return copyMap;
        }

        private int[][] leftDown(int[][] arr) {
            int size = arr.length / 2;
            int[][] copyMap = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    copyMap[i][j] = arr[i + size][j];
                }
            }

            return copyMap;
        }

        private int[][] rightDown(int[][] arr) {
            int size = arr.length / 2;
            int[][] copyMap = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    copyMap[i][j] = arr[i + size][j + size];
                }
            }

            return copyMap;
        }

        private boolean isAllSame(int[][] arr) {
            return Arrays.stream(arr)
                    .flatMapToInt(Arrays::stream)
                    .distinct()
                    .count() == 1;
        }
    }

}
