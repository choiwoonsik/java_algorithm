package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FriendNetwork_4195 {

    static int[] parent;
    static int[] relation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            // 이름과 인덱스를 가지는 해쉬맵을 만든다
            // 부모는 해쉬맵에서 이름의 인덱스를 가지고 접근한다
            // 관계의수
            HashMap<String, Integer> friendsMap = new HashMap<>();
            parent = new int[N*2+1];
            relation = new int[N*2+1];
            int idx = 0;
            Arrays.fill(relation, 1);

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                if (!friendsMap.containsKey(name1)) {
                    friendsMap.put(name1, idx);
                    parent[idx] = idx++;
                }
                if (!friendsMap.containsKey(name2)) {
                    friendsMap.put(name2, idx);
                    parent[idx] = idx++;
                }
                int u = friendsMap.get(name1);
                int v = friendsMap.get(name2);
                union(u, v);
                System.out.println(relation[find(u)]);
            }
        }
    }

    private static void union(int fr_1, int fr_2) {
        int root_1 = find(fr_1);
        int root_2 = find(fr_2);

        if (root_1 != root_2) {
            parent[root_1] = root_2;
            relation[root_2] += relation[root_1];
        }
    }

    private static int find(int fr) {
        if (parent[fr] != fr)
            parent[fr] = find(parent[fr]);
        return parent[fr];
    }
}
