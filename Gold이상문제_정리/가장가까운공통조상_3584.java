package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class 가장가까운공통조상_3584 {
    static int T, N;
    static StringBuilder answer = new StringBuilder();
    static ArrayList<Integer>[] adj;
    static HashMap<Integer, Integer> chPaMap = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            chPaMap.clear();
            boolean[] isNotRoot = new boolean[N+1];

            adj = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++)
                adj[i] = new ArrayList<>();

            String[] input;
            for (int i = 0; i < N - 1; i++) {
                input = br.readLine().split(" ");
                int parent = Integer.parseInt(input[0]);
                int child = Integer.parseInt(input[1]);

                isNotRoot[child] = true;
                adj[parent].add(child);
                chPaMap.put(child, parent);
            }

            input = br.readLine().split(" ");
            int nodeA = Integer.parseInt(input[0]);
            int nodeB = Integer.parseInt(input[1]);
            int root = 1;
            while (isNotRoot[root]) root++;
            findCommonParent(root, nodeA, nodeB);
        }
        System.out.print(answer);
    }

    private static void findCommonParent(int root, int nodeA, int nodeB) {
        int heightA = findNode(root, nodeA, 0);
        int heightB = findNode(root, nodeB, 0);

        if (heightA < heightB)
            matching(nodeA, nodeB, heightA, heightB);
        else
            matching(nodeB, nodeA, heightB, heightA);
    }

    private static void matching(int topNode, int botNode, int topH, int botH) {
        while (botH > topH) {
            botH--;
            botNode = chPaMap.get(botNode);
        }

        while (topNode != botNode) {
            topNode = chPaMap.get(topNode);
            botNode = chPaMap.get(botNode);
        }
        answer.append(topNode).append("\n");
    }

    private static int findNode(int root, int node, int height) {
        if (root == node) return height;

        int h = 0;

        for (int next : adj[root]) {
            h += findNode(next, node, height + 1);
        }

        return h;
    }
}
