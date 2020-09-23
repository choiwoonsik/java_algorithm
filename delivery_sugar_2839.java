import java.util.Scanner;

public class delivery_sugar_2839 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int five = 5;
        int three = 3;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= N/five; i++)
        {
            for (int j = 0; j <= N/three; j++)
            {
                if (five * i + three * j == N) {
                    if (min > i + j)
                        min = i + j;
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }
}
