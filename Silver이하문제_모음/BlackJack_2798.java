package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BlackJack_2798 {
    /*
    N장의 카드중 M이하의 합이 되도록 3장의 카드를 고른다
    5 21
    5 6 7 8 9
    만들수있는 M이하의 최대값을 출력 => 21
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] Narr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NM[0];
        int M = NM[1];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < Narr.length; i++){
            for (int j = i+1; j < Narr.length; j++){
                for (int k = j + 1; k < Narr.length; k++){
                     sum = Narr[i] + Narr[j] + Narr[k];
                     if (sum <= M)
                         max = Math.max(max, sum);
                }
            }
        }
        System.out.println(max);
    }
}
