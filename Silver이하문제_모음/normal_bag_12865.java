package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class normal_bag_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] table = new int[N+1][K+1];
        Arrays.fill(table[0], 0);

        List<WV_12865> list = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            list.add(new WV_12865(W, V));
        }
        for (int item = 1; item <= N; item++)
        {
            WV_12865 now_product;
            now_product = list.remove(0);
            int W = now_product.W;
            int V = now_product.V;

            for (int weight = 0; weight <= K; weight++)
            {
                if (W <= weight)
                    table[item][weight] = Math.max(table[item-1][weight - W] + V, table[item - 1][weight]);
                else
                    table[item][weight] =  table[item-1][weight];
            }
        }
        int ret = 0;
        for (int i = 0; i <= N; i++)
            ret = Math.max(table[i][K], ret);
        System.out.println(ret);
    }
}
class WV_12865
{
    int W;
    int V;
    WV_12865(int w, int v) {
        W = w;
        V = v;
    }
}