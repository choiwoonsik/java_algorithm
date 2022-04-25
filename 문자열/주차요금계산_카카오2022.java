package 문자열;

import java.util.*;

public class 주차요금계산_카카오2022 {
	public static void main(String[] args) {
		/*
		차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return
		 */
		Solution sol = new Solution();
		int[] solution = sol.solution(
				new int[]{180, 5000, 10, 600},
				new String[]{
						"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
				}
		);
		int[] answer = new int[]{14600, 34400, 5000};

		String ret = "TRUE";
		for (int i = 0; i < solution.length; i++) {
			if (solution[i] != answer[i]) {
				ret = "FALSE";
			}
		}

		System.out.println(ret);
	}

	private static class Solution {
		static HashMap<String, Integer[]> carInOutMap = new HashMap<>();
		static HashMap<String, Integer> carTotalTime = new HashMap<>();
		static HashSet<String> noOutCar = new HashSet<>();
		static int IN = 0;
		static int OUT = 1;

		public int[] solution(int[] fees, String[] records) {

			int baseTime = fees[0];
			int basePrice = fees[1];
			int perMin = fees[2];
			int perMinPrice = fees[3];

			for (String record : records) {
				String[] recoArr = record.split(" ");
				String[] hhmm = recoArr[0].split(":");
				String car = recoArr[1];
				String inout = recoArr[2];

				if (inout.equals("IN")) {
					int start = Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]);
					Integer[] time = new Integer[2];
					time[IN] = start;
					carInOutMap.put(car, time);
					noOutCar.add(car);
					if (!carTotalTime.containsKey(car)) {
						carTotalTime.put(car, 0);
					}
				} else if (inout.equals("OUT")) {
					int start = carInOutMap.get(car)[IN];
					int end = Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]);
					Integer[] time = carInOutMap.get(car);
					time[OUT] = end;
					noOutCar.remove(car);
					Integer beforeTime = carTotalTime.get(car);
					carTotalTime.put(car, beforeTime + (end - start));
				}
			}

			for (String car : noOutCar) {
				int end = 23 * 60 + 59;
				int start = carInOutMap.get(car)[IN];
				Integer before = carTotalTime.get(car);
				carTotalTime.put(car, before + (end - start));
			}

			Set<String> carSet = carInOutMap.keySet();
			ArrayList<String> allCar = new ArrayList<>(carSet);
			Collections.sort(allCar);

			int[] answer = new int[allCar.size()];
			int carIndex = 0;
			for (String car : allCar) {
				Integer time = carTotalTime.get(car);
				if (time <= baseTime) {
					answer[carIndex] = basePrice;
				} else {
					time -= baseTime;
					int count = (int) Math.ceil((double) time / perMin);
					int price = perMinPrice * count;
					answer[carIndex] = basePrice + price;
				}

				carIndex++;
			}

//			for (int i : answer) {
//				System.out.println(i);
//			}
			return answer;
		}
	}
}
