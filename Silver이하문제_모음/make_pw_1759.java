package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class make_pw_1759 {
    static int N; // pw길이
    static int C; // 주어진 알파벳 개수
    static boolean[] used;
    static char[] pw;
    static char[] using;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        int n[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = n[0];
        C = n[1];
        using = new char[C];
        used = new boolean[C];
        pw = new char[N];
        String tmp = br.readLine();
        for (int i = 0; i < C; i++)
            using[i] = tmp.charAt(i * 2);
        Arrays.sort(using);
        back_tracking(0, 0);
    }
    private static void back_tracking(int start, int depth)
    {
        if (depth == N)
        {
            if (isValid()) {
                for (char c : pw)
                    System.out.print(c);
                System.out.println();
            }
            return;
        }
        for (int pos = start; pos < using.length; pos++) {
            if (!used[pos]) {
                used[pos] = true;
                pw[depth] = using[pos];
                depth++;
                back_tracking(start + 1, depth);
                pw[--depth] = 0;
                used[pos] = false;
            }
        }
    }
    private static boolean isValid()
    {
        int ja = 0;
        int mo = 0;
        for (int i = 0; i < pw.length; i++)
        {
            if (i < pw.length - 1) {
                if (pw[i] > pw[i + 1]) {
                    return false;
                }
            }
            if (pw[i] == 'a' || pw[i] == 'e' || pw[i] == 'i' || pw[i] == 'o' || pw[i] == 'u')
                mo++;
            else
                ja++;
        }
        if (mo >= 1 && ja >= 2)
            return true;
        return false;
    }
}
