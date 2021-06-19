package Silver이하문제_모음;

import java.util.*;

public class programmers_압축 {
    public static void main(String[] args) {
        solution("KAKAO");
    }
    private static int[] solution(String msg)
    {
        HashMap<String, Integer> dict = new HashMap<>();
        ArrayList<Integer> idxList = new ArrayList<>();
        init_dict(dict);

        int dictIdx = 27;
        boolean isLast = false;
        for (int idx = 0; idx < msg.length(); idx++)
        {
            // msg에 들어있는 각 단어
            String word = msg.charAt(idx) + "";
            while (dict.containsKey(word))
            {
                // 사전에 단어가 있으면?
                idx++;
                if (idx == msg.length()) {
                    isLast = true;
                    break;
                }
                word += msg.charAt(idx);
            }
            if (isLast) {
                idxList.add(dict.get(word));
                break;
            }

            //끝아 아니면 , 마지막에 추가된 단어 빼서 인덱스 담음
            idxList.add(dict.get(word.substring(0, word.length() -1 )));
            dict.put(word, dictIdx++);
            idx--;
        }
        int[] answer = new int[idxList.size()];
        for (int i = 0; i < answer.length; i++)
            answer[i] = idxList.get(i);
        return answer;
    }

    private static void init_dict(HashMap<String, Integer> dict) {
        char A = 'A';
        for (int i = 1; i <= 26; i++)
        {
            dict.put(A + "", i);
            A += 1;
        }
    }
}
