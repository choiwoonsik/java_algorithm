package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class quiz_ox_8958 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static String ox[];
    static int sum = 0;
    public static void main(String[] args) throws IOException {
        int n[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = n[0];
        ox = new String[N];
        for (int i = 0; i < N; i++) {
            ox[i] = bf.readLine();
            calc_point(ox[i]);
            System.out.println(sum);
            sum = 0;
        }
    }
    private static void calc_point(String check)
    {
        int o_point = 0;
        for (int i = 0; i < check.length(); i++)
        {
            if (check.charAt(i) == 'O')
                sum += ++o_point;
            else
                o_point = 0;
        }
    }
}
