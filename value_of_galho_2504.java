import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class value_of_galho_2504 {
    static String input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        int all = 0;
        int inner = 1;
        boolean isWrong = false;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);

            if (c == '(' || c == '[') {
                stack.push(c);
                if (c == '(')
                    inner *= 2;
                else
                    inner *= 3;
            }
            else {
                if ((stack.size() < 1 ||  c == ')' && stack.peek() == '[')
                || (stack.size() < 1 || c == ']' && stack.peek() == '(')) {
                    isWrong = true;
                    break;
                }
                char before = input.charAt(i - 1);
                if (c == ')') {
                    if (before == '(')
                        all += inner;
                    inner /= 2;
                }
                else if (c == ']') {
                    if (before == '[')
                        all += inner;
                    inner /= 3;
                }
                stack.pop();
            }
        }

        if (isWrong || stack.size() > 0) {
            System.out.println(0);
            return;
        }
        System.out.println(all);
    }
}
