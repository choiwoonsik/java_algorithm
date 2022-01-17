package SDS_알고리즘.Day01.암호만들기_1759;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] words;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        words = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            words[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(words);

        dfs(0, 0, 0, 0, new char[L]);
        System.out.print(answer);
    }

    private static void dfs(int idx, int len, int ja, int mo, char[] selected) {
        if (len == L) { // 목적지
            if (ja >= 2 && mo >= 1) {
                for (char c : selected) {
                    answer.append(c);
                }
                answer.append("\n");
            }
            return;
        }

        for (int i = idx; i < C; i++) {
            if (isMo(words[i])) {
                selected[len] = words[i]; // 체크인
                dfs(i + 1, len + 1, ja, mo + 1, selected); // 연결
            } else {
                selected[len] = words[i]; // 체크인
                dfs(i + 1, len + 1, ja + 1, mo, selected); // 연결
            }
        }
    }

    private static boolean isMo(char word) {
        return word == 'a' || word == 'e' || word == 'i' || word == 'o' || word == 'u';
    }
}
