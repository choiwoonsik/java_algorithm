package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Polygon_Warehouse_2304 {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] map = new int[1001];
        int last = 0;
        int start = Integer.MAX_VALUE;
        int size = 0;

        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            map[x] = h;
            start = Math.min(start, x);
            last = Math.max(last, x);
        }

        int Left = start, Right = start;
        int Min;

        while (Right < last)
        {
            Min = 0;
            Min = Math.max(Min, map[Right]);
            System.out.println(Right);
            // 오른쪽 기둥이 더 크면
            if (Min > map[Left]){
                size += (Right - Left) * map[Left];
            }
            //오른쪽 기둥이 더 작으면
            else if (Min < map[Left]) {
                size += map[Left++];
                size += (Right - Left + 1) * Min;
            }
            // 같으면
            else if (Min == map[Left]) {
                size += (Right - Left + 1) * Min;
                //Left++;
            }
            Left = Right;
        }
        System.out.println(size);
    }
}
/*
5
1 11
2 11
3 11
4 11
5 11
 */