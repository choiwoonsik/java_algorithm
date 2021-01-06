package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 친구네트워크_4195 {
    // 친구의 부모관계를 저장하는 부모맵 , 친구의 개수를 저장하는 친구맵
    static Map<String, Integer> map = new HashMap<>(); // 자신 - 번호
    static int[] parentsArr = new int[200001]; // 번호 - 자기 자신
    static int[] friendsArr = new int[200001]; // 번호 - 친구 수
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder str = new StringBuilder();
        int K;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++)
        {
            int F = Integer.parseInt(br.readLine());
            Arrays.fill(friendsArr, 1);
            for (int i = 0; i < parentsArr.length; i++)
                parentsArr[i] = i;
            K = 0;
            for (int f = 0; f < F; f++)
            {
                st = new StringTokenizer(br.readLine());

                String first = st.nextToken();
                String second = st.nextToken();

                // 일단 등록이 안되있으면 등록해주기
                if (!map.containsKey(first))
                {
                    map.put(first, K++);
                    friendsArr[map.get(first)] = 1;
                }
                if (!map.containsKey(second))
                {
                    map.put(second, K++);
                    friendsArr[map.get(second)] = 1;
                }

                // 둘의 부모가 다르다면 -> 유니온하고 친구개수 더하기
                if (find(map.get(first)) != (find(map.get(second))))
                    union(first, second);

                // 둘의 부모가 같다면 더이상 할거없이 수 출력
                int top = find(map.get(first));
                int friendN = friendsArr[top];
                str.append(friendN).append("\n");
            }
        }
        System.out.println(str);
    }

    private static void union(String first, String second) {
        // A의 친구 수에 B의 친구수를 더해주고, B를 친구 A의 네트워크에 포함 시킨다
        friendsArr[parentsArr[map.get(first)]] += friendsArr[parentsArr[map.get(second)]];
        parentsArr[parentsArr[map.get(second)]] = parentsArr[map.get(first)];
    }

    private static int find(int my)
    {
        if (parentsArr[my] == my)
            return my;
        return parentsArr[my] = find(parentsArr[my]);
    }
}