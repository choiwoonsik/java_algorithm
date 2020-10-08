import java.util.Scanner;

public class N_Queen_9663 {
    static int[] col;
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int count;

    public static void main(String[] args) {
        N = sc.nextInt();

        for (int i = 1; i <= N; i++)
        {
            col = new int[15];
            col[1] = i;
            DFS(1);
        }
        System.out.print(count);
    }
    private static void DFS(int level)
    {
        if (level == N)
            count++;
        else {
            for (int i = 1; i <= N; i++) {
                col[level + 1] = i;
                if (isValid(level + 1))
                    DFS(level + 1);
                else
                    col[level + 1] = 0;
            }
        }
        col[level] = 0;
    }
    private static boolean isValid(int level)
    {
        int i = 1;
        while (i < level)
        {
            if (col[i] == col[level])
                return false;
            if ((col[i] == col[level] - (level - i)) || (col[i] == col[level] + (level - i)))
                return false;
            i++;
        }
        return true;
    }
}