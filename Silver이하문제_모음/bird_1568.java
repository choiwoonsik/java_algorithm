package Silver이하문제_모음;

import java.util.Scanner;

public class bird_1568 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int i = 1;
        int time = 0;
        while (i <= N)
        {
            time++;
            N -= i;
            i++;
            if (i > N)
                i = 1;
        }
        System.out.println(time);
    }
}
