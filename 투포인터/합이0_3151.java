package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0_3151 {
    static int N;
    static int[] sortMember;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sortMember = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String info = st.nextToken();
            sortMember[i] = Integer.parseInt(info);
        }
        Arrays.sort(sortMember);

        long total = 0;
        for (int i = 0; i < N - 2; i++) {
            int base = sortMember[i];

            if (base > 0) break;

            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int l = sortMember[left];
                int r = sortMember[right];
                int result = base + l + r;

                // 합이 0
                if (result == 0) {
                    // 값이 같다면
                    if (l == r) {
                        int cnt = right - left + 1;
                        total += ((long) cnt * (cnt - 1)) / 2;
                        break;
                    }
                    // 값이 다르다면
                    else {
                        int lcnt = 0;
                        int rcnt = 0;
                        while (sortMember[left] == l) {
                            left++;
                            lcnt++;
                        }
                        while (sortMember[right] == r) {
                            right--;
                            rcnt++;
                        }
                        total += (long) lcnt * rcnt;
                    }
                }
                else if (result > 0) right--;
                else left++;
            }
        }
        System.out.println(total);
    }
}
