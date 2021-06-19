package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class find_alphabet_10809 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        StringBuilder str = new StringBuilder();
        for (char i = 'a'; i < 'z'+1; i++)
            str.append(s.indexOf(i)).append(" ");
        System.out.println(str);
    }
}
