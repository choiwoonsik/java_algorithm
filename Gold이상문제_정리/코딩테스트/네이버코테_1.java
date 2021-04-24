package Gold이상문제_정리.코딩테스트;

/*
암호화하려는 문장을 나타내는 문자열 sentence,
키워드를 나타내는 문자열 keyword,
건너뛸 글자(또는 공백) 수를 순서대로 나타내는 정수 배열 skips 가 매개변수로 주어집니다.
위 방법을 따라 만든 암호문을 return 하도록 solution 함수를 완성해주세요.
 */
import java.util.*;
import java.io.*;

public class 네이버코테_1 {
	public static String Solution (String sentence, String keyword, int[] skips)
	{
		int CURSOR_IDX = 0;
		int INPUT_IDX = 0;
		int WORD_IDX = 0;
		int LEN;
		int move;
		StringBuilder RET_str = new StringBuilder();

		RET_str.append(sentence);
		LEN = sentence.length();


		// keyword의 단어 시작부터 skips의 수 이내에 중복이 있는지만 검사
		loop: for (int skip_cnt : skips)
		{
			char word = keyword.charAt(WORD_IDX++ % keyword.length());
			int skip_duplicate = -1;

			// check_overlap
			if (skip_cnt > 1) {
				for (int next = 0; next < skip_cnt; next++) {
					if (CURSOR_IDX + next > LEN) break loop;
					if (sentence.charAt(CURSOR_IDX + next) == word) {
						skip_duplicate = next;
						break;
					}
				}
			}
			move = skip_duplicate == -1 ? skip_cnt : skip_duplicate + 1;
			CURSOR_IDX += move;
			if (CURSOR_IDX > LEN) break;

			// 중복 체크 끝냈으면 실제로 넣기
			INPUT_IDX += move;
			RET_str.insert(INPUT_IDX++, word);
		}

		return RET_str.toString();
	}

	public static void main(String[] args) {
		String a = Solution("i love coding", "mask", new int[]{0, 0, 3, 2, 3, 4});
		String b = Solution("encrypt this sentence", "something", new int[]{0, 1, 3, 2, 1, 2, 0, 3, 0,  2, 4, 1, 3});
		String c = Solution("i love coding", "mode", new int[]{0, 10});
		String d = Solution("abcde fghi", "xyz", new int[]{10, 0, 1});
		String e = Solution("i love coding", "ng", new int[]{12, 13});

		System.out.println(a.equals("mai lsovke cmodinag"));
		System.out.println(b.equals("seoncrmypett thihisng ssenteonmcee"));
		System.out.println(c.equals("mi loove coding"));
		System.out.println(d.equals("abcde fghixy"));
		System.out.println(e.equals("i love codinngg"));
	}
}
