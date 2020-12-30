package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 암호만들기_1759 {
    static StringBuilder sb = new StringBuilder();
    static String[] moArr = {"a", "e", "i", "o", "u"};
    static List<String> moList = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        // L, C > 서로다른 L개의 알파벳으로 구성된 암호 , C개의 알파벳 리스트
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        ArrayList<String> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens())
            list.add(st.nextToken());
        Collections.sort(list);

        moList.addAll(Arrays.asList(moArr));
        String[] arr = list.toArray(new String[C]);
        boolean[] visited = new boolean[C];
        comb(arr, visited, 0, L);
        System.out.print(sb);
    }

    private static void comb(String[] word, boolean[] visited, int depth, int r) {
        if (r == 0) {
            int mo = 0;
            int ja = 0;
            for (int i = 0; i < word.length; i++){
                if (visited[i] && moList.contains(word[i]))
                    mo++;
                else if (visited[i])
                    ja++;
            }
            if (mo >= 1 && ja >= 2) {
                for (int i = 0; i < word.length; i++) {
                    if (visited[i])
                        sb.append(word[i]);
                }
                sb.append("\n");
                return;
            }
        }
        for (int i = depth; i < word.length; i++) {
            visited[i] = true;
            comb(word, visited, i + 1, r - 1);
            visited[i] = false;
        }
    }
}
