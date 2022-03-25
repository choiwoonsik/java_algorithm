package 코테_콘웨;

import java.util.*;

class Solution3 {

	static int[] answerArr;
	static int idx = 0;

	public int[] solution(int[] arr) {

		answerArr = new int[arr.length];

		return shake(arr);
	}

	private int[] shake(int[] curArr)
	{
		// 길이가 1인 경우
		if (curArr.length == 1) {
			answerArr[idx++] = curArr[0];
			return answerArr;
		}

		else {
			// 순서 뒤집기
			curArr = reverse(curArr);

			if (curArr.length % 2 == 0)
			{
				shake(Arrays.copyOfRange(curArr, 0, curArr.length / 2));
				shake(Arrays.copyOfRange(curArr, curArr.length / 2, curArr.length));
			}

			if (curArr.length % 2 == 1)
			{
				shake(Arrays.copyOfRange(curArr, 0, curArr.length / 2 + 1));
				shake(Arrays.copyOfRange(curArr, curArr.length / 2 + 1, curArr.length));
			}
		}

		return answerArr;
	}

	private int[] reverse(int[] nums)
	{
		int[] tmpArr = new int[nums.length];

		int i = nums.length;
		for (int num : nums)
		{
			tmpArr[--i] = num;
		}

		return tmpArr;
	}
}
