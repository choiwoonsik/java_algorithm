package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SortNum3_2_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] numArr = new int[10001];

        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int nextN = Integer.parseInt(st.nextToken());
            numArr[nextN] += 1;
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < numArr.length; i++)
        {
            for (int j = 0; j < numArr[i]; j++){
                str.append(i).append("\n");
            }
        }
        System.out.println(str);
    }
}
