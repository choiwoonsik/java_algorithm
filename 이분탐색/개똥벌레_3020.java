package 이분탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
14 5
1
3
4
2
2
4
3
4
3
3
3
2
3
3
 */
public class 개똥벌레_3020 {
    static int[] floor;
    static int[] ceil;
    static int N, H;
    static int TotalMin = 987654321;
    static int Count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        floor = new int[N / 2];
        ceil = new int[N / 2];

        for (int i = 0; i < N / 2; i++) {
            int h = Integer.parseInt(br.readLine());
            floor[i] = h;
            h = Integer.parseInt(br.readLine());
            ceil[i] = h;
        }

        Arrays.sort(floor);
        Arrays.sort(ceil);

        findAll();

        System.out.println(TotalMin + " " + Count);
    }

    private static void findAll() {
        for (int i = 1; i <= H; i++) {
            int floorN = binarySearch(floor, i);
            int ceilN= binarySearch(ceil, H - (i - 1));

            int total = floorN + ceilN;

            if (total < TotalMin) {
                TotalMin = total;
                Count = 1;
            } else if (total == TotalMin) {
                Count++;
            }
        }
    }

    private static int binarySearch(int[] arrs, int h) {
        int low = 0;
        int high = arrs.length;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            int height = arrs[mid];

            if (height < h) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return arrs.length - high;
    }
}
