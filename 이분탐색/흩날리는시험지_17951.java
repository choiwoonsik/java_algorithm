package 이분탐색;
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
8 2
12 7 19 20 17 14 9 10
 */
public class 흩날리는시험지_17951 {
    static int N, M, Min;
    static int[] testPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        testPoints = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            testPoints[i] = Integer.parseInt(st.nextToken());
        }

        binarySearch();
    }

    private static void binarySearch() {
        int left = 0;
        int right = (int) Math.pow(10, 5) * 20;
        int mid;

        while (left + 1 < right) {
            mid = (left + right) / 2;

            int partCount = divide(mid);
//            System.out.println("점수 : " + mid + ", 조각 : " + partCount);
            if (partCount >= M) left = mid; // 점수 올리기
            if (partCount < M) right = mid; // 점수 낮추기
        }
        System.out.println(left);
    }

    private static int divide(int max) {
        int sum = 0;
        int partCount = 0;

        for (int point : testPoints) {
            sum += point;
            if (sum >= max) {
                Min = Math.min(Min, sum);
                sum = 0;
                partCount++;
            }
        }
        return partCount;
    }
}
