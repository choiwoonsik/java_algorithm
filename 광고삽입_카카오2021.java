import java.util.ArrayList;
import java.util.Collections;

public class 광고삽입_카카오2021 {
	public static void main(String[] args) {
		Solution sol = new Solution();
//		String solution = sol.solution("02:03:55", "00:14:15", new String[]{
//				"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
//		});
//		System.out.println(solution);
//		System.out.println(solution.equals("01:30:59"));

		sol = new Solution();
		String solution2 = sol.solution("99:59:59", "25:00:00", new String[]{
				"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"
		});
		System.out.println(solution2);
		System.out.println(solution2.equals("01:00:00"));

	}

	private static class Solution {
		static ArrayList<Integer> startList = new ArrayList<>();
		static long[] dpTime;
		static int[] startTime;
		static int[] endTime;
		static int[] totalTime;
		static int START_TIME;
		static int END;
		static int LEN;

		public String solution(String play_time, String adv_time, String[] logs) {
			String[] playT = play_time.split(":");
			String[] advT = adv_time.split(":");
			int h = Integer.parseInt(playT[0]);
			int m = Integer.parseInt(playT[1]);
			int s = Integer.parseInt(playT[2]);
			END = h * 60 * 60 + m * 60 + s;
			h = Integer.parseInt(advT[0]);
			m = Integer.parseInt(advT[1]);
			s = Integer.parseInt(advT[2]);
			LEN = h * 60 * 60 + m * 60 + s;

			startTime = new int[END + 2];
			endTime = new int[END + 2];
			totalTime = new int[END + 2];
			dpTime = new long[END + 2];

			// x 시간대 별 들어온 시청자 / 나간 시청자 계산
			makeStartEndTable(logs);

			// x 시간대에 듣는 시청자 수 계산
			sumWatchPerTime();

			// 듣는 시청자수 누적합 만들기
			cumulativeTime();

			// 각 시작 시간대 별 광고시간 내 totalTime 합이 최대인 곳 찾음
			sumAdvWatchPerStartTime();

			String answer = "";
			answer += (START_TIME / 3600 < 10) ? String.format("0%d:", START_TIME / 3600) : START_TIME / 3600 + ":";
			START_TIME %= 3600;
			answer += START_TIME / 60 < 10 ? String.format("0%d:", START_TIME / 60) : START_TIME / 60 + ":";
			START_TIME %= 60;
			answer += START_TIME < 10 ? String.format("0%d", START_TIME) : START_TIME;

			return answer;
		}

		private void sumAdvWatchPerStartTime() {
			long maxTotal;

			maxTotal = dpTime[LEN] - dpTime[0];
			START_TIME = 0;

			Collections.sort(startList);

			for (int startT = LEN; startT <= END; startT++) {
				int endT = Math.min(startT + LEN, END);

				long partTotal = dpTime[endT] - dpTime[startT];

				if (partTotal > maxTotal) {
					maxTotal = partTotal;
					START_TIME = startT;
				}
			}
		}

		private void cumulativeTime() {
			dpTime[0] = totalTime[0];
			for (int i = 1; i < END + 1; i++) {
				dpTime[i] = dpTime[i - 1] + totalTime[i];
			}
		}

		private void sumWatchPerTime() {
			totalTime[0] = startTime[0];

			for (int i = 1; i < END + 1; i++) {
				totalTime[i] = totalTime[i - 1];
				totalTime[i] += startTime[i];
				totalTime[i] -= endTime[i];
			}
		}

		private void makeStartEndTable(String[] logs) {
			for (String log : logs) {
				String[] logArr = log.split("-");

				String[] hhmmss = logArr[0].split(":");
				int start = Integer.parseInt(hhmmss[0]) * 3600 + Integer.parseInt(hhmmss[1]) * 60 + Integer.parseInt(hhmmss[2]);

				hhmmss = logArr[1].split(":");
				int end = Integer.parseInt(hhmmss[0]) * 3600 + Integer.parseInt(hhmmss[1]) * 60 + Integer.parseInt(hhmmss[2]);

				startTime[start]++;
				endTime[end - 1]++;
				startList.add(start);
			}
		}
	}
}
