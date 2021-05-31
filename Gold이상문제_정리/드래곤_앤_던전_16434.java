package Gold이상문제_정리;

import java.io.*;
import java.util.*;
/*
HMaxHP : 용사의 최대 생명력입니다. 이 값은 1이상이어야 하며 던전에 들어간 이후로 변하지 않습니다.
HCurHP : 현재 용사의 생명력입니다. 던전에 들어가기 전 이 값은 용사의 최대 생명력 HMaxHP와 같습니다. 이 값은 HMaxHP보다 커질 수 없습니다.
HATK : 용사의 공격력입니다.

던전은 총 N개의 방으로 이루어져 있고 i번째 방을 통해서만 i+1번째 방으로 이동 할 수 있습니다.
방에는 포션이 있거나 몬스터가 있는데 몬스터가 있을 경우 몬스터를 쓰러트려야지 다음방으로 이동 할 수 있습니다.
N번째 방에는 공주와 용이 있고, 용을 무찌르면 공주를 구할 수 있습니다.

몬스터가 있는 방에 올 경우 다음과 같이 전투가 진행됩니다.
	용사의 공격력 HATK만큼 몬스터의 생명력을 깎습니다.
	몬스터의 생명력이 0 이하이면 전투가 종료되고 용사는 다음 방으로 이동합니다.
	몬스터의 공격력만큼 용사의 생명력 HCurHP를 깎습니다.
	용사의 생명력이 0 이하이면 전투가 종료되고 용사는 사망합니다.
	다시 1부터 진행합니다.
포션이 있는 방에 올 경우
	포션을 마셔서 현재 용사의 생명력 HCurHP가 일정량 회복되고 공격력 HATK도 일정량만큼 증가됩니다.
	회복된 생명력이 최대 생명력 HMaxHP보다 큰 경우 용사의 현재 생명력 HCurHP가 최대 생명력 HMaxHP와 같아집니다.

첫 번째 줄에 방의 개수 N (1 ≤ N  ≤ 123,456) 과 용사의 초기 공격력 HATK (1 ≤ HATK  ≤ 1,000,000) 가 주어집니다.

i+1번째 줄엔 i번째 방의 정보를 나타내는 세개의 정수 ti, ai, hi (ti ∈ {1, 2}, 1 ≤ ai, hi  ≤ 1,000,000) 가 주어집니다.

ti가 1인 경우 공격력이 ai이고 생명력이 hi인 몬스터가 있음을 나타내고, ti가 2인 경우 용사의 공격력 HATK를 ai만큼 증가시켜주고 용사의 현재 생명력 HCurHP를 hi만큼 회복시켜주는 포션이 있음을 나타냅니다.

3 3
1 1 20
2 3 10
1 3 100
 */

public class 드래곤_앤_던전_16434 {
	static int N, ATK, T, A, H;
	static long MAX_HP = 1000000000000000000L;
	static Room[] rooms;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ATK = Integer.parseInt(st.nextToken());
		rooms = new Room[N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			rooms[r] = new Room(T, A, H);
		}

		long left = 1;
		long right = MAX_HP;
		while (left <= right)
		{
			long mid = (left + right) / 2;
			if (!start_game(mid))
				left = mid + 1;
			else
				right = mid - 1;
		}
		System.out.println(left);
	}

	private static boolean start_game(long max_hp) {
		long cur_hp = max_hp;
		long cur_atk = ATK;
		for (Room room : rooms)
		{
			// enemy
			if (room.type == 1)
			{
				long atc_count = (int) Math.ceil((double)room.h / cur_atk) - 1;
				cur_hp -= atc_count * room.a;
				if (cur_hp <= 0)
					return false;
			}
			// portion
			else {
				cur_hp = Math.min((cur_hp + room.h), max_hp);
				cur_atk += room.a;
			}
		}
		return true;
	}

	private static class Room
	{
		int type;
		int a;
		int h;
		public Room(int type, int a, int h)
		{
			this.type = type;
			this.a = a;
			this.h = h;
		}
	}
}
