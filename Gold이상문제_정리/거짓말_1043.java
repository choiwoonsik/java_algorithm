package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 거짓말_1043 {
    static int N, M, K;
    static boolean[] visited = new boolean[51];
    static boolean[] partyVisited = new boolean[51];
    static ArrayList<Integer> factKnows = new ArrayList<>();
    static ArrayList<Integer>[] parties;
    static ArrayList<Integer>[] peoplePartyList;
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        parties = new ArrayList[M];
        peoplePartyList = new ArrayList[N + 1];

        // N명의 사람들 리스트 - 각 사람마다 자신이 들어간 방을 담는다
        for (int i = 0; i <= N; i++)
            peoplePartyList[i] = new ArrayList<>();

        // K명의 진실을 아는 사람들
        for (int k = 0; k < K; k++)
            factKnows.add(sc.nextInt());

        // 파티 M개
        for (int i = 0; i < M; i++)
        {
            int B = sc.nextInt(); // 한 파티에 B명
            parties[i] = new ArrayList<>();

            // 파티 참가자에 대해서 하나의 그룹으로 만들어준다
            for (int j = 0; j < B; j++) {
                int tmp = sc.nextInt(); // 파티에 들어가는 사람
                parties[i].add(tmp); // i번째 파티에 사람들 추가
                peoplePartyList[tmp].add(i); // 해당 사람에게 해당 피티 방 번호 추가
            }
        }
        // parties배열에는 각 파티마다 참여자의 사람번호가 들어있고
        // peoplePartyList에는 각 사람마다 참가하는 방번호가 들어있다

        // 진실을 아는 사람들에 대해서 체크 - 한번만
        for (int know : factKnows) {
            if (!visited[know])
                mark(know);
        }

        int ans = 0;
        for (int i = 0; i < M; i++)
            if (!partyVisited[i]) ans++;

        System.out.println(ans);
    }

    // 체크 - 진실을 아는 사람 know에 대해서 확인
    private static void mark(int know) {
        visited[know] = true;
        for (int party : peoplePartyList[know]) { // 진실을 아는 사람이 들어간 파티
            if (!partyVisited[party]) { // 해당 파티를 아직 체크하지 않았다면
                Pmark(party); // 파티에 대해서 파티원 들에 대해서 체크
            }
        }
    }

    // 파티 참가자들에 대해서 진실을 안다고 해주기
    private static void Pmark(int party) {
        partyVisited[party] = true; // 해당 파티는 체크했다고 표시
        for (int n : parties[party]) { // 파티의 참가자들에 대해서
            if (!visited[n]) { // 아직 체크를 안했으면
                mark(n); // 해당 사람이 들어간 파티에 대해서 확인
            }
        }
    }
}
