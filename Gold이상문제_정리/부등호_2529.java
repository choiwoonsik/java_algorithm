package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 부등호_2529 {
    static ArrayList<Character> operators = new ArrayList<>();
    static boolean[] visited = new boolean[10];
    static int N;
    static String least = "";
    static String most = "";

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void solve() {
        for (int n = 0; n < 10; n++) {
            visited[n] = true;
            String number = n+"";
            permutation(n, 0, operators.get(0), number);
            visited[n] = false;
        }
        System.out.println(most + "\n" + least);
    }

    private static void permutation(int before, int select, char oper, String number) {
        if (select == N) {
            if (least.equals("")) {
                least = number;
            } else {
                most = number;
            }
            return;
        }

        for (int now = 0; now < 10; now++) {
            if (!visited[now]) {
                visited[now] = true;
                if (oper == '<' && before < now) {
                    permutation(now, select + 1, operators.get(select + 1), number + now);
                } else if (oper == '>' && before > now) {
                    permutation(now, select + 1, operators.get(select + 1), number + now);
                }
                visited[now] = false;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            operators.add(st.nextToken().charAt(0));
        }
        operators.add('_');
    }
}
