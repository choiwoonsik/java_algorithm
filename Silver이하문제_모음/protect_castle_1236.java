package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class protect_castle_1236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int countY = 0;
        int countX = 0;

        Map<Integer, Integer> checkMapX = new HashMap<>();
        Map<Integer, Integer> checkMapY = new HashMap<>();

        String c;

        for (int y = 0; y < Y; y++)
        {
            String[] str = br.readLine().split("");
            for (int x = 0; x < X; x++) {
                c = str[x];
                if (c.equals("X")){
                    if (!checkMapX.containsKey(x)) {
                        checkMapX.put(x, 1);
                        countX++;
                    }
                    if (!checkMapY.containsKey(y)) {
                        checkMapY.put(y, 1);
                        countY++;
                    }
                }
            }
        }
        int needY = Y - countY;
        int needX = X - countX;
        System.out.println(Math.max(needX, needY));
    }
}

/*
5 4
....
...X
...X
....
....
 */