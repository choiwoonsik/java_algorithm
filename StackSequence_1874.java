import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StackSequence_1874 {
        /*
            1~n까지의 수를 스택에 넣었다가 뽑아서 하나의 수열 생성
            스택에 push하는 순서는 오름차순

            예제 입력에 주어진 수열로 만들어야되는데 이때 필요한 push/pop의 조합은?
             */
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder str = new StringBuilder();
            Stack<Integer> stack = new Stack<>();

            //1 ~ N의 숫자가 온다
            int N = Integer.parseInt(st.nextToken());
            // 시작 숫자 설정
            int startNum = 1;

            // 원하는 숫자에 도달할때까지 push를 하고 도달한 경우 -> pop // 못한경우 -> 실패
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                while (startNum <= X) {
                    stack.push(startNum++);
                    str.append("+\n");
                }
                if (stack.peek() == X) {
                    stack.pop();
                    str.append("-\n");
                }
                else {
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println(str);
        }
    }
