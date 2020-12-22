import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FriendNetwork2_4195 {
    static int[] parent;
    static int[] friendsCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0)
        {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int IDX = 0;

            HashMap<String, Integer> friendMap = new HashMap<>();
            parent = new int[N*2];
            friendsCount = new int[N*2];

            for (int i = 0; i < N; i++)
            {
                st = new StringTokenizer(br.readLine());
                String leftName = st.nextToken();
                String rightName = st.nextToken();

                //이름이 없다면 맵에 -> 부모설정, 친구 수 설정, 인간관계 맵 그리기
                if (!friendMap.containsKey(leftName)) {
                    parent[IDX] = IDX;
                    friendsCount[IDX] = 1;
                    friendMap.put(leftName, IDX);
                    IDX++;
                }
                if (!friendMap.containsKey(rightName)) {
                    parent[IDX] = IDX;
                    friendsCount[IDX] = 1;
                    friendMap.put(rightName, IDX);
                    IDX++;
                }
                // 이름들의 고유 번호를 이용해서 합치기 진행 <-- 만약 둘이 다른 그래프이 친구 그래프라면
                int u = friendMap.get(leftName);
                int v = friendMap.get(rightName);
                UnionFriend(u, v);
                System.out.println(friendsCount[find(u)]);
            }
        }
    }

    private static void UnionFriend(int u, int v) {
        //u와 v의 루트를 찾아서 같은 그래프에 속하는지 확인후 다른 그래프면 합치기
        int root_u = find(u);
        int root_v = find(v);

        // v의 친구 그래프가 루트가 되고 u그래프는 v의 그래프에 속해지게 된다
        if (root_u != root_v) {
            parent[root_u] = parent[root_v];
            friendsCount[root_v] += friendsCount[root_u];
        }
    }

    private static int find(int v) {
        if (parent[v] != v) {
            parent[v] = find(parent[v]);
        }
        return parent[v];
    }
}
