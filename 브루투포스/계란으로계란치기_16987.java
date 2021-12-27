package 브루투포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 계란으로계란치기_16987 {
    static int N, eggCount;
    static Egg[] eggs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int hp = Integer.parseInt(st.nextToken());
            int atk = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(hp, atk);
        }

        attackEgg(0);
        System.out.println(eggCount);
    }

    private static void attackEgg(int cur) {
        if (cur == N) {
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                if (eggs[i].hp <= 0) tmp++;
            }
            eggCount = Math.max(eggCount, tmp);
            return;
        }
        if (eggs[cur].hp <= 0) {
            attackEgg(cur + 1);
            return;
        }

        for (int next = 0; next < N; next++) {
            if (cur == next) continue;
            if (eggs[next].hp <= 0) continue;
            int myHp = eggs[cur].hp;
            int eHp = eggs[next].hp;
            eggs[cur].hp -= eggs[next].atk;
            eggs[next].hp -= eggs[cur].atk;
            attackEgg(cur + 1);
            eggs[next].hp = eHp;
            eggs[cur].hp = myHp;
        }
        if (cur == N - 1) attackEgg(cur + 1);
    }

    private static class Egg {
        int hp;
        int atk;

        public Egg(int hp, int atk) {
            this.hp = hp;
            this.atk = atk;
        }
    }
}
