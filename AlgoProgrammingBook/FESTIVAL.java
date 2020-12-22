package AlgoProgrammingBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FESTIVAL {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        while (C-- > 0)
        {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int[] daysPrice = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < daysPrice.length; i++)
                daysPrice[i] = Integer.parseInt(st.nextToken());

            int left = 0;
            int right = 0;
            double days = 0;
            double sum = 0;
            double MIN = Integer.MAX_VALUE;
            while (right < N){
                while (days < L) {
                    sum += daysPrice[right];
                    days++;
                    MIN = Math.min(MIN, sum / days);
                }

            }
        }
    }
}
