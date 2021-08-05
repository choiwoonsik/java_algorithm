package Gold이상문제_정리;

/*
2
8 3 15
3 4 7 5 6 4 2 9
2 1 5
4 5
 */

import java.io.*;
import java.util.*;

public class 도둑_13422 {
    static int T, N, M, MAX;
    static int[] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            MAX = Integer.parseInt(input[2]);

            houses = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            answer.append(solution()).append("\n");
        }
        System.out.print(answer);
    }

    private static int solution() {
        int left = 0;
        int right = 0;
        int total = 0;
        int cnt = 0;

        while (left < N) {

            total += houses[right % N];

            // find
            if (right - left + 1 == M && total < MAX) {
                cnt++;
                if (N == M) cnt = 1;
            }

            // over
            while ((left < N && total >= MAX) || right - left + 1 == M)
                total -= houses[left++];

            ++right;
        }

        return cnt;
    }
}
