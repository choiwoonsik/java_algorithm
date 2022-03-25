package 코테.코테_콘웨_2022_03;

import java.util.*;

class Solution4 {

	static int N;
	static Map<Integer, Integer> partner = new HashMap<>();
	static int[] result;
	static int[] memberCount;

	public int[] solution(int[] targets) {

		N = targets.length;
		memberCount = new int[N + 1];
		result = new int[N+1];

		Queue<Integer> memberQ = new LinkedList<>();

		for (int i = 0; i < N; i++)
		{
			memberQ.add(i + 1);
			partner.put(i + 1, targets[i]);

			memberCount[i+1] = 1;
		}

		while(true)
		{
			// 나눠주기
			Queue<Integer> tmpQ = new LinkedList<>();
			while (!memberQ.isEmpty())
			{
				int curMem = memberQ.poll();
				int partnerMem = partner.get(curMem);

				memberCount[curMem] -= 1;
				memberCount[partnerMem] += 1;

				if (memberCount[partnerMem] >= 1) {
					tmpQ.add(partnerMem);
				}
			}

			memberQ.addAll(tmpQ);

			// 먹기
			int eatCount = 0;
			tmpQ = new LinkedList<>();

			while (!memberQ.isEmpty())
			{
				int curMem = memberQ.poll();
				int partnerMem = partner.get(curMem);

				if (memberCount[partnerMem] >= 2) {

					int tmpCount = memberCount[partnerMem] - 1;

					result[partnerMem] += tmpCount;
					tmpQ.add(partnerMem);
					eatCount += tmpCount;

					memberCount[partnerMem] = 1;
				}
			}

			memberQ.addAll(tmpQ);

			if (eatCount == 0) {
				break;
			}
		}

		return Arrays.copyOfRange(result, 1, result.length);
	}
}