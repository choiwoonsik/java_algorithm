import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class sw_Academy_10804_문자열의_거울상 {
    static char[] bd = {'b','d'};
    static char[] pq = {'p', 'q'};
    public static void main(String[] args) throws IOException {
        // q <-> p
        // b <-> d

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ret_str = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            ret_str.append("#").append(i + 1).append(" ");
            String str = br.readLine();
            ret_str.append(mirror(str));
            if (i < N - 1)
                ret_str.append("\n");
        }
        System.out.println(ret_str);
    }

    private static String mirror(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
        {
            for (int j = 0; j < 2; j++) {
                if (str.charAt(i) == bd[j])
                    sb.append(bd[(j + 1) % 2]);
                else if (str.charAt(i) == pq[j])
                    sb.append(pq[(j + 1) % 2]);
            }
        }
        return sb.toString();
    }
}
