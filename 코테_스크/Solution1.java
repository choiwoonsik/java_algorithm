package 코테_스크;

import java.util.ArrayList;
import java.util.Collections;

public class Solution1 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] solution = sol.solution(new String[]{"pencil", "cilicon", "contrabase", "picturelist"});
		for (String s : solution) {
			System.out.println("> "+s);
		}
	}

	//	["pencil","cilicon","contrabase","picturelist"]
	private static class Solution {

		static String[] goodArr;
		static String[] answer;

		public String[] solution(String[] goods) {
			goodArr = goods;
			answer = new String[goods.length];

			int idx = 0;
			for (String goodName : goodArr) {
				String s = findUniqueName(goodName);
				answer[idx++] = s;
			}

			return answer;
		}

		private String findUniqueName(String goodName) {
			int maxLen = goodName.length();
			ArrayList<String> uniqueWordList = new ArrayList<>();

			boolean isNone = true;
			for (int curLen = 1; curLen <= maxLen; curLen++) {
				String word;

				for (int index = 0; index + curLen <= goodName.length(); index++) {

					boolean innerUnique = true;
					word = goodName.substring(index, index + curLen);

					for (String otherGoodName : goodArr) {
						if (goodName.equals(otherGoodName)) continue;

						if (otherGoodName.contains(word)) {
							innerUnique = false;
						}
					}

					if (innerUnique) {
						isNone = false;
						if (!uniqueWordList.contains(word)) {
							uniqueWordList.add(word);
						}
					}
				}

				if (!isNone) {
					break;
				}
			}

			StringBuilder uniqueWords = new StringBuilder();
			Collections.sort(uniqueWordList);
			for (String s : uniqueWordList) {
				uniqueWords.append(s).append(" ");
			}

			if (isNone) return "None";
			return uniqueWords.toString().trim();
		}
	}
}

