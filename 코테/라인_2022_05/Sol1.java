package 코테.라인_2022_05;

import java.util.*;

public class Sol1 {
	public static void main(String[] args) {
		Solution sol = new Solution();

	}

	private static class Solution {
		private static HashSet<String> nameSet = new HashSet<>();
		private static HashSet<String> problemSet = new HashSet<>();
		private static HashMap<String, HashSet<String>> problemSolverMap = new HashMap<>();
		private static HashMap<String, HashSet<String>> nameSolvingProblemCountMap = new HashMap<>();

		public String[] solution(String[] logs) {

			for (String log : logs) {
				String[] logArr = log.split(" ");
				String name = logArr[0];
				String problem = logArr[1];

				// 문제별 푼 name
				HashSet<String> solverSet;
				if (!problemSolverMap.containsKey(problem)) {
					solverSet = new HashSet<>();
					solverSet.add(name);
					problemSolverMap.put(problem, solverSet);
				} else {
					problemSolverMap.get(problem).add(name);
				}

				// name 별 푼 문제
				HashSet<String> solvingProblemSet;
				if (!nameSolvingProblemCountMap.containsKey(name)) {
					solvingProblemSet = new HashSet<>();
					solvingProblemSet.add(problem);
					nameSolvingProblemCountMap.put(name, solvingProblemSet);
				} else {
					nameSolvingProblemCountMap.get(problem);
				}

				problemSet.add(problem);
				nameSet.add(name);
			}

			int total = 0;
			for (String name : nameSet) {
				int size = nameSolvingProblemCountMap.get(name).size();
				System.out.println(name + " - " + size);
				if (size >= 1) {
					total += size;
				}
			}

			ArrayList<String> answer = new ArrayList<>();

			for (String problem : problemSet) {
				int size = problemSolverMap.get(problem).size();
				if (size > total / 2) {
					answer.add(problem);
				}
			}

			return answer.toArray(new String[0]);
		}
	}
}