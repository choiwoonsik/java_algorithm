package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetromino_14500 {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        int[][][] tetromino =
                {
                        // 가로
                        {
                                {1, 1, 1, 1},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 0, 0, 0}
                        },
                        // 네모
                        {
                                {1, 1, 0, 0},
                                {1, 1, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        // 니은
                        {
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {1, 1, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {0, 0, 1, 0},
                                {1, 1, 1, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 1, 1, 0},
                                {1, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {0, 1, 0, 0},
                                {0, 1, 0, 0},
                                {1, 1, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 1, 1, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 1, 0, 0},
                                {1, 0, 0, 0},
                                {1, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 0, 0, 0},
                                {1, 1, 1, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        // 지그재그
                        {
                                {1, 0, 0, 0},
                                {1, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {0, 1, 1, 0},
                                {1, 1, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {0, 1, 0, 0},
                                {1, 1, 0, 0},
                                {1, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 1, 0, 0},
                                {0, 1, 1, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        // 산 모양
                        {
                                {0, 1, 0, 0},
                                {1, 1, 1, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 0, 0, 0},
                                {1, 1, 0, 0},
                                {1, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {1, 1, 1, 0},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 0}
                        },
                        {
                                {0, 1, 0, 0},
                                {1, 1, 0, 0},
                                {0, 1, 0, 0},
                                {0, 0, 0, 0}
                        }
                };

        map = new int[503][503];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        for (int h = 0; h < H; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < W; w++)
                map[h][w] = Integer.parseInt(st.nextToken());
        }

        // 밑 3줄 최소값 설정
        for (int i = H; i < H+3; i++) {
            for (int j = 0; j < W+3; j++)
                map[i][j] = Integer.MIN_VALUE;
        }

        for (int i = W; i < W+3; i++) {
            for (int j = 0; j < H; j++)
                map[j][i] = Integer.MIN_VALUE;
        }

        int max = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++)
                for (int shape = 0; shape < 19; shape++)
                    max = Math.max(max, find_max(i, j, shape, tetromino));
        }
        System.out.println(max);
    }

    private static int find_max(int h, int w, int shape, int[][][] tetromino) {

        int sum = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                sum += map[h+i][w+j] * tetromino[shape][i][j];
            }
        }
        return sum;
    }
}
