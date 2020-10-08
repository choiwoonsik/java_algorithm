import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class num_baseball_2503 {
    static int strike;
    static int ball;
    static int N;
    static int count;
    static int[][] check_num;
    static int[] start = new int[3];
    static boolean[] check = new boolean[10];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = n[0];
        check_num = new int[N][3];
        for (int i = 0; i < N; i++)
            check_num[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        find(0);
        System.out.println(count);
    }
    private static void find(int pos) {
        if (pos >= 3) {
            if (compare_number())
                count++;
            return ;
        }
        for (int i = 1; i < 10; i++) {
            if (check[i] == false){
                start[pos] = i;
                check[i] = true;
                find(pos + 1);
                check[i] = false;
                start[pos] = 0;
            }
        }
    }
    private static boolean compare_number()
    {
        boolean correct = false;
        for (int i = 0; i < N; i++) {
            strike = 0;
            ball = 0;
            int tmp = check_num[i][0];
            int inner_tmp = check_num[i][0];
            for (int j = 0; j < 3; j++) {
                if (start[j] == tmp / (int)Math.pow(10, 2 - j))
                    strike++;
                else {
                    for (int k = 0; k < 3; k++) {
                        if (start[j] == inner_tmp / (int)Math.pow(10, 2 - k)) {
                            ball++;
                            break;
                        }
                        inner_tmp %= (int)Math.pow(10, 2 - k);
                    }
                }
                tmp %= (int)Math.pow(10, 2 - j);
                inner_tmp = check_num[i][0];
            }
            if (check_num[i][1] == strike && check_num[i][2] == ball)
                correct = true;
            else {
                correct = false;
                break;
            }
        }
        return correct;
    }
}
