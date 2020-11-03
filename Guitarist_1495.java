import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Guitarist_1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Start = Integer.parseInt(st.nextToken());
        int Max = Integer.parseInt(st.nextToken());

        int[] music = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            music[i] = Integer.parseInt(st.nextToken());

        boolean[][] table = new boolean[N+1][Max+1];
        table[0][Start] = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= Max; j++) {
                if (table[i][j]) {
                    if (j + music[i] <= Max)
                        table[i + 1][j + music[i]] = true;
                    if (j - music[i] >= 0)
                        table[i + 1][j - music[i]] = true;
                }
            }
        }
        for (int i = Max; i >= 0; i--)
            if (table[N][i]) {
                System.out.println(i);
                return;
            }
            else if (i == 0)
                System.out.println(-1);
    }
}
