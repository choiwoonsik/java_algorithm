import java.util.*;

public class programmers_자동완성 {
    public static void main(String[] args)
    {
        String[] test = {"word", "war", "warrior", "world"};
        solution(test);
    }
    private static int solution(String[] words)
    {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, words);
        Collections.sort(list);

        int result = 0;
        int sameLen;
        sameLen = need_input(list.get(0), list.get(1));
        if (sameLen < list.get(0).length())
            result += sameLen + 1;
        else
            result += sameLen;
        for (int word = 1; word < list.size() - 1; word++) {
            int left = need_input(list.get(word - 1), list.get(word));
            sameLen = Math.max(need_input(list.get(word), list.get(word + 1)), left);
            if (sameLen < list.get(word).length())
                result += sameLen + 1;
            else
                result += sameLen;
        }
        sameLen = need_input(list.get(list.size() - 2), list.get(list.size() - 1));
        if (sameLen < list.get(list.size() - 1).length())
            result += sameLen + 1;
        else
            result += sameLen;
        //System.out.println(result);
        return result;
    }

    private static int need_input(String s1, String s2) {
        int i;
        for (i = 0; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                break;
        }
        return i; // i번째에서 다르다 (== i개가 같다)
    }
}
