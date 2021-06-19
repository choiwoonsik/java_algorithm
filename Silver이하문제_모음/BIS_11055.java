package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BIS_11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] biggest = new int[N];

        Arrays.fill(biggest, 0);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        biggest[0] = arr[0];
        int big = biggest[0];
        for (int now = 1; now < N; now++) {
            for (int before = 0; before < now; before++)
            {
                // 증가 형태 이면
                 if (arr[before] < arr[now]) {
                     biggest[now] = Math.max(biggest[now], biggest[before]);
                 }
            }
            // 증가형태가 아니면 자기자신이 된다
            biggest[now] += arr[now];
            big = Math.max(biggest[now], big);
        }

        Arrays.stream(biggest).forEach(s -> System.out.print(s+" "));
        System.out.println();
        System.out.println(big);
    }
}
/*
12
1 100 2 50 60 3 5 6 7 8 50 60
 */