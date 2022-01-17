package SDS_알고리즘.Day01.한수_1065;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        if (N < 100) {
            System.out.println(N);
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (i >= 100) {
                int hundred = i / 100;
                int ten = (i / 10) % 10;
                int one = i % 10;
                if (hundred - ten == ten - one) {
                    count++;
                }
            }
        }
        System.out.println(count + 99);
    }
}
