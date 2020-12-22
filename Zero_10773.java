import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Zero_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0)
                stack.pop();
            else
                stack.push(n);
        }
        int sum = stack.stream().mapToInt(s->Integer.parseInt(String.valueOf(s))).sum();
        System.out.println(sum);
    }
}
