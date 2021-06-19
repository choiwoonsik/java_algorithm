package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KeyLogger_5397 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0)
        {
            String str = br.readLine();
            Stack<Character> stackLeft = new Stack<>();
            Stack<Character> stackRight = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '<') {
                    if (!stackLeft.isEmpty())
                        stackRight.push(stackLeft.pop());
                }
                else if (str.charAt(i) == '>') {
                    if (!stackRight.isEmpty())
                        stackLeft.push(stackRight.pop());
                }
                else if (str.charAt(i) == '-') {
                    if (!stackLeft.isEmpty())
                        stackLeft.pop();
                }
                else {
                    stackLeft.push(str.charAt(i));
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!stackRight.isEmpty())
                stackLeft.push(stackRight.pop());
            while (!stackLeft.isEmpty())
                sb.append(stackLeft.pop());
            System.out.println(sb.reverse().toString());
        }
    }
}
