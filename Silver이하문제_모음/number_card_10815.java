package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class number_card_10815 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M;
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static int all;
    public static void main(String[] args) throws IOException
    {
        M = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        int[] sang_number = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < M; i++) {
            hashMap.put(sang_number[i], 0);
        }
        all = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        int[] all_number = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < all; i++)
        {
            if (hashMap.containsKey(all_number[i]))
                System.out.print(1+" ");
            else
                System.out.print(0+" ");
        }
    }
}
