package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 구간합구하기4_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // N개의 수 , M개의 구간
        int[] nArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sArr = new int[N];

        // 구간합 구하기
        sArr[0] = nArr[0];
        for (int i = 1; i < N; i++) {
            sArr[i] = sArr[i - 1] + nArr[i];
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            int sum = 0;
            if (start == 0)
                sum = sArr[end];
            else
                sum = sArr[end] - sArr[start - 1];
            str.append(sum).append("\n");
        }
        System.out.println(str);
    }
}
