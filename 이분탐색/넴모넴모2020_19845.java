package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 넴모넴모2020_19845 {
    static int N, Q;
    static int[] floorInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        floorInfo = new int[250001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            floorInfo[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int countR = removeRight(y, x);
            int countU = removeUp(y, x);
            answer.append((countR + countU)).append("\n");
        }
        System.out.print(answer);
    }

    private static int removeUp(int y, int x) {
        int left = 0;
        int right = 250001;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (floorInfo[mid] < x) {
                right = mid; // 왼쪽 탐색
            }
            else {
                left = mid + 1; // 오른쪽 탐색
            }
        }
        int tmp = right - y;
        if (tmp < 0) tmp = 0;
        return tmp;
    }

    private static int removeRight(int y, int x) {
        int c = floorInfo[y - 1] - x + 1;
        if (c < 0) c = 0;
        return c;
    }
}
