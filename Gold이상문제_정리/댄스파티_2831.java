package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
2
-1800 -2200
1900 1700

5
-1200 -1400 -2000 -2500 -1300
2100 1900 1200 1000 1500

-1200 -1300 -1400 -2000 -2500
1000 1200 1500 1900 2100
 */
public class 댄스파티_2831 {
    static int N;
    static PriorityQueue<Integer> manWantTaller = new PriorityQueue<>();
    static PriorityQueue<Integer> manWantSmaller = new PriorityQueue<>();
    static PriorityQueue<Integer> womanWantTaller = new PriorityQueue<>();
    static PriorityQueue<Integer> womanWantSmaller = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int myHeight = Integer.parseInt(st.nextToken());
            if (myHeight >= 0) {
                manWantTaller.add(myHeight);
            } else {
                manWantSmaller.add(-1 * myHeight);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int myHeight = Integer.parseInt(st.nextToken());
            if (myHeight >= 0) {
                womanWantTaller.add(myHeight);
            } else {
                womanWantSmaller.add(-1 * myHeight);
            }
        }

        int totalPairCount = 0;

        while (!manWantSmaller.isEmpty() && !womanWantTaller.isEmpty()) {

            int womanH = womanWantTaller.peek();
            int manH = manWantSmaller.poll();

            if (manH > womanH) {
                womanWantTaller.poll();
                totalPairCount++;
            }
        }
        while (!manWantTaller.isEmpty() && !womanWantSmaller.isEmpty()) {

            int manH = manWantTaller.peek();
            int womanH = womanWantSmaller.poll();


            if (manH < womanH) {
                manWantTaller.poll();
                totalPairCount++;
            }
        }

        System.out.println(totalPairCount);
    }
}
