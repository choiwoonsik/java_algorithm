package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 단어암기_18119 {
    static int N, Q;

    // 모든 알파벳을 알고 있다.
    // 100000000000000000000000000 -> 11111111111111111111111111
    static int alphaBit = (1 << 27) - 1;
    static int[] wordBits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        // 각 단어에 대한 알파벳 체크
        wordBits = new int[N];

        for (int i = 0; i < N; i++) {
            for (char c : br.readLine().toCharArray()) {
                // a -> 1
                // b -> 10
                // c -> 100
                // db -> 1010
                wordBits[i] |= (1 << c - 'a');
            }
        }

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());

            int option = Integer.parseInt(st.nextToken());
            char forgetC = st.nextToken().charAt(0);

            // 해당 단어를 잊게한다. -> 해당 단어만 0으로 바꾸고 & 연산
            if (option == 1) {
                alphaBit &= ~(1 << forgetC - 'a');
            }
            // 해당 단어를 기억하게 한다. -> 해당 단어를 1로 만들고 | 연산
            else {
                alphaBit |= (1 << forgetC - 'a');
            }

            int rememberWordCount = 0;
            for (int wordbit : wordBits) {
                // wordbit : dif -> 100101000
                // alphabit : 11111111111111111111111111 (기억하는 것만 1)
                // 둘다 1 (기억하면 : 1) -> wordbit가 같다면 기억하는 단어
                if ((wordbit & alphaBit) == wordbit)
                    rememberWordCount++;
            }
            answer.append(rememberWordCount).append("\n");
        }
        System.out.print(answer);
    }
}
