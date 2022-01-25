package 자료구조;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 트리인가_6416 {
    static final int MAX = 14;
    static int[] parent = new int[MAX];
    static ArrayList<Integer>[] tree;
    static HashMap<Integer, Integer> nodeInfos = new HashMap<>();
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int i = 0;


        over: while (true) {
            ++i;
            tree = new ArrayList[MAX];
            for (int j = 0; j < MAX; j++) {
                tree[j] = new ArrayList<>();
                parent[j] = j;
            }
            nodeInfos.clear();

            testcase: while (true) {
                String line = br.readLine();
                if (line.length() == 0) line = br.readLine();
                st = new StringTokenizer(line);

                while (st.hasMoreTokens()) {
                    int from = Integer.parseInt(st.nextToken());
                    int to = Integer.parseInt(st.nextToken());

                    if (from == -1) {
                        break over;
                    }

                    if (from == 0) {
                        break testcase;
                    }

                    if (!nodeInfos.containsKey(from)) nodeInfos.put(from, 0);
                    if (!nodeInfos.containsKey(to)) nodeInfos.put(to, 0);

                    tree[from].add(to);
                    nodeInfos.put(to, nodeInfos.get(to) + 1);
                }
            }

            int root = 0;
            int topCount = 0;
            boolean isTree = true;
            for (int node : nodeInfos.keySet()) {
                int inputCnt = nodeInfos.get(node);
                if (inputCnt == 0) {
                    root = node;
                    if (++topCount > 1) isTree = false;
                } else if (inputCnt > 1) isTree = false;
                if (!isTree) break;
            }

            if (isTree) {
                findAll(root);
                for (int node : nodeInfos.keySet()) {
                    if (find(node) != find(root)) {
                        isTree = false;
                        break;
                    }
                }
            }

            if (isTree) {
                answer.append("Case ");
                answer.append(i);
                answer.append(" is a tree.");
            } else {
                answer.append("Case ");
                answer.append(i);
                answer.append(" is not a tree.");
            }
            answer.append("\n");
        }
        System.out.print(answer);
    }

    private static void findAll(int root) {
        for (int next : tree[root]) {
            if (find(next) != find(root))
                union(next, root);
            findAll(next);
        }
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        parent[u] = parent[parent[v]];
    }

    private static int find(int next) {
        if (parent[next] == next) return next;
        else return parent[next] = find(parent[next]);
    }
}
