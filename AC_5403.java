import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class AC_5403 {
    static String AC;
    static int len;
    static StringBuilder sb;
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0)
            AC_play();

        System.out.println(sb);
    }

    private static void AC_play() throws IOException {
        AC = br.readLine();
        len = Integer.parseInt(br.readLine());
        String str = br.readLine();
        str = str.substring(1, str.length()- 1);
        boolean reversed = false;
        Deque<String> deque = new ArrayDeque<>(Arrays.asList(str.split(",")));

        for (int cmd = 0; cmd < AC.length(); cmd++) {
            if (AC.charAt(cmd) == 'R') {
                if (reversed)
                    reversed = false;
                else
                    reversed = true;
            }
            else if (AC.charAt(cmd) == 'D')
            {
                if (len < 1) {
                    sb.append("error\n");
                    return;
                }
                else {
                    len--;
                    if (reversed)
                        deque.pollLast();
                    else
                        deque.pollFirst();
                }
            }
        }
        print_list(deque, reversed);
    }

    private static void print_list(Deque<String> list, boolean reversed) {
        sb.append("[");
        if (list.size() == 1)
            sb.append(list.pollFirst());
        else if (list.size() == 0)
            sb.append("");
        else if (reversed) {
            sb.append(list.pollLast());
            while (list.size() > 0)
                sb.append(",").append(list.pollLast());
        }
        else {
            sb.append(list.pollFirst());
            while (list.size() > 0)
                sb.append(",").append(list.pollFirst());
        }
        sb.append("]\n");
    }
}
