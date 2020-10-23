import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Z_1074 {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numCase = br.readLine().split(" ");
        int size = Integer.parseInt(numCase[0]);
        int r = Integer.parseInt(numCase[1]);
        int c = Integer.parseInt(numCase[2]);

        int N = (int) Math.pow(2, size);
        recusive(N, 0, 0, r, c);
    }
    private static void recusive(int N, int x, int y, int r, int c)
    {
        if (N == 2)
        {
            if (x == r && y == c){
                System.out.println(count);
                return;
            }
            count++;
            if (x == r && y+1 == c){
                System.out.println(count);
                return;
            }
            count++;
            if (x+1 == r && y == c) {
                System.out.println(count);
                return;
            }
            count++;
            if (x+1 == r && y+1 == c) {
                System.out.println(count);
                return;
            }
            count++;
            return;
        }
        recusive(N/2, x, y, r, c);
        recusive(N/2, x, y + N/2, r, c);
        recusive(N/2, x + N/2, y, r, c);
        recusive(N/2, x + N/2, y + N/2, r, c);
    }
}
