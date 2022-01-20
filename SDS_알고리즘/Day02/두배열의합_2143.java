package SDS_알고리즘.Day02;

/*
5
4
1 3 1 2
3
1 3 2
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class 두배열의합_2143 {
    static int T;
    static int aLen, bLen;
    static int[] A;
    static int[] B;
    static ArrayList<Long> subA = new ArrayList<>();
    static ArrayList<Long> subB = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        aLen = Integer.parseInt(st.nextToken());
        A = new int[aLen];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aLen; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        bLen = Integer.parseInt(st.nextToken());
        B = new int[bLen];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bLen; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < aLen; i++) {
            long sum = 0;
            for (int j = i; j < aLen; j++) {
                sum += A[j];
                subA.add(sum);
            }
        }

        for (int i = 0; i < bLen; i++) {
            long sum = 0;
            for (int j = i; j < bLen; j++) {
                sum += B[j];
                subB.add(sum);
            }
        }

        subA.sort(null);
        subB.sort(Collections.reverseOrder());

        int adx = 0;
        int bdx = 0;
        long total = 0;

        while (adx < subA.size() && bdx < subB.size()) {

            long nowNumA = subA.get(adx);
            long nowNumB = subB.get(bdx);
            long SUM = nowNumA + nowNumB;

            if (SUM == T) {
                long cntA = 0;
                while (adx < subA.size() && subA.get(adx) == nowNumA) {
                    adx++;
                    cntA++;
                }

                long cntB = 0;
                while (bdx < subB.size() && subB.get(bdx) == nowNumB) {
                    bdx++;
                    cntB++;
                }

                long sum = (cntA * cntB);
                total += sum;
            } else if (SUM > T) {
                bdx++;
            } else {
                adx++;
            }
        }
        System.out.println(total);
    }
}
