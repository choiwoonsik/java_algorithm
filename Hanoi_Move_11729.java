import java.util.Scanner;

public class Hanoi_Move_11729 {
    static int count;
    static StringBuilder str = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        Hanoi(N, '1', '2', '3');
        System.out.println(count);
        System.out.println(str);
    }

    private static void Hanoi(int N, char a, char b, char c) {

        count++;
        if (N == 1)
        {
            str.append(a + " "+ c).append("\n");
            return;
        }
        else
        {
            Hanoi(N-1, a, c, b);
            str.append(a + " " + c).append("\n");
            Hanoi(N-1, b, a, c);
        }
    }
}
