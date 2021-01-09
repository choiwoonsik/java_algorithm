package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 전국시대_15809 {
    static int N, M;
    static int[] kingP;
    static Map<Integer, Integer> king_soldier = new HashMap<>();
    public static void main(String[] args) throws IOException {
        // N개의 국가 1~N - 국가마다 병력있음
        // 동맹 -> 하나의 부모 국가를 가지며 병력은 합친다
        // 전쟁 -> 병력이 더많은 나라가 부모 나라가 된다, 같으면 둘다 사라진다, 이긴 나라의 병력 -= 진 나라 병력

        // N개의 국가, M번의 기록
        // O, P, Q // O==1 pq 동맹 or O==2 pq 전쟁

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        kingP = new int[N + 1];

        kingP[0] = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int soldier_N = Integer.parseInt(st.nextToken());
            king_soldier.put(i, soldier_N);
            kingP[i] = i;
        }

        for (int kirok = 0; kirok < M; kirok++)
        {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            // 동맹
            if (o == 1) {
                p = find(p);
                q = find(q);
                union_friend(p, q);
            }
            // 전쟁
            else if (o == 2) {
                p = find(p);
                q = find(q);
                union_war(p, q);
            }
        }

        int count = 0;
        StringBuilder str = new StringBuilder();
        ArrayList<Integer> soldierList = new ArrayList<>();

        for (int king = 1; king <= N; king++) {
            if (find(kingP[king]) == king) {
                count++;
                soldierList.add(king_soldier.get(find(kingP[king])));
            }
        }
        Collections.sort(soldierList);
        soldierList.forEach(s -> str.append(s).append(" "));

        System.out.println(count);
        System.out.print(str);
    }

    private static void union_war(int p, int q) {
        if (king_soldier.get(p) > king_soldier.get(q))
        {
            king_soldier.put(kingP[p], king_soldier.get(kingP[p]) - king_soldier.get(kingP[q]));
            kingP[kingP[q]] = kingP[p];
        }
        else if (king_soldier.get(p) < king_soldier.get(q))
        {
            king_soldier.put(kingP[q], king_soldier.get(kingP[q]) - king_soldier.get(kingP[p]));
            kingP[kingP[p]] = kingP[q];
        }
        else {
            kingP[p] = 0;
            kingP[q] = 0;
        }
    }

    private static void union_friend(int p, int q) {
        king_soldier.put(kingP[q], king_soldier.get(kingP[q]) + king_soldier.get(kingP[p]));
        kingP[kingP[p]] = kingP[q];
    }

    private static int find(int king) {
        if (kingP[king] == king)
            return king;
        return kingP[king] = find(kingP[king]);
    }
}
