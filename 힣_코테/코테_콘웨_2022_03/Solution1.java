package 힣_코테.코테_콘웨_2022_03;

class Solution1 {

	static String[] logArr;
	static int[] timeZone = new int[24];

	public int[] solution(String logs) {
		logArr = logs.split("\n");

		for (String log : logArr)
		{
			String[] dayAndTime = log.split(" ");
			String[] hhmmss = dayAndTime[1].split(":");

			int hour = Integer.parseInt(hhmmss[0]);
			int hourAfter9 = hour + 9;

			timeZone[hourAfter9 % 24] += 1;
		}

		return timeZone;
	}
}
