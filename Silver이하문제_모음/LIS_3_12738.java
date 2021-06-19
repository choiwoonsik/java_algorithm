package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS_3_12738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] vector = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int  idx = 0;
        vector[0] = Integer.MIN_VALUE;
        for (int now = 0; now < N; now++)
        {
            if (arr[now] > vector[idx])
                vector[++idx] = arr[now];
            else {
                int left = 0;
                int right = idx;
                while (left < right)
                {
                    int mid = (left + right) / 2;
                    if (arr[now] > vector[mid])
                        left = mid + 1;
                    else
                        right = mid;
                }
                vector[right] = arr[now];
            }
        }
        System.out.println(idx);
    }
}
/*
7
1 6 2 4 5 3 7
 */