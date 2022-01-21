package SDS_알고리즘.Day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 암호제작_1837 {
    static int K;
    static boolean[] isNotprimes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String password = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        isNotprimes = new boolean[K + 1];

        for (int i = 2; i <= K; i++) {
            int div = i * 2;
            while (div <= K && !isNotprimes[div]) {
                isNotprimes[div] = true;
                div *= 2;
            }
        }

        boolean isGood = true;
        int i;
        for (i = 2; i < K; i++) {
            if (isNotprimes[i]) continue;
            if (isDivied(password, i)) {
                isGood = false;
                break;
            }
        }
        System.out.println(isGood ? "GOOD" : "BAD " + i);
    }

    private static boolean isDivied(String password, int d) {
        int remain = 0;
        for (int i = 0; i < password.length(); i++) {
            remain = (remain * 10 + (password.charAt(i) - '0')) % d;
        }
        return remain == 0;
    }
}
