package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
6 7 800
622 411 201 555 755 82
 */
public class 휴게소세우기_1477 {
    static int N, M, L;
    static int[] restHouse;
    static PriorityQueue<Integer> housePosQ = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        restHouse = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            housePosQ.add(Integer.parseInt(st.nextToken()));
        }
        housePosQ.add(L);

        findPos();
    }

    private static void findPos() {
        int left = 0;
        int right = 1000;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            int restHouseCount = houseCheck(mid);

            if (restHouseCount <= M) right = mid - 1; // 간격 더 줄임
            if (restHouseCount > M) left = mid + 1; // 간격 더 늘림
        }
        System.out.println(left);
    }

    private static int houseCheck(int span) {
        int start = 0;
        int houseCount = 0;
        PriorityQueue<Integer> tmpQ = new PriorityQueue<>();
        tmpQ.addAll(housePosQ);

        while (!tmpQ.isEmpty()) {
            int pos = tmpQ.peek();

            if ((pos - start) > span) { // 휴게소 설치
                tmpQ.add(start + span);
                start = start + span;
                houseCount++;
            }
            else {
                tmpQ.poll();
                start = pos;
            }
            if (houseCount > M) break;
        }
        return houseCount;
    }
}
