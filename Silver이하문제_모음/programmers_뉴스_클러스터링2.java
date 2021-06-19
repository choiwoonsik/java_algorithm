package Silver이하문제_모음;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class programmers_뉴스_클러스터링2 {
    public static void main(String[] args)
    {
        solution("aa1+aa2", "AAAA12");
    }
    private static int solution(String str1, String str2) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        StringBuilder sb;
        for (int i = 0; i < str1.length() - 1; i++) {
            sb = new StringBuilder();
            String s;
            if (Character.isLetter(str1.charAt(i)) && Character.isLetter(str1.charAt(i + 1))){
                sb.append(str1.charAt(i)).append(str1.charAt(i + 1));
                s = sb.toString();
                if (map1.containsKey(s))
                    map1.put(s, map1.get(s) + 1);
                else
                    map1.put(s, 1);
            }
        }
        for (int i = 0; i < str2.length() - 1; i++)
        {
            sb = new StringBuilder();
            if (Character.isLetter(str2.charAt(i)) && Character.isLetter(str2.charAt(i + 1))) {
                sb.append(str2.charAt(i)).append(str2.charAt(i + 1));
                String s = sb.toString();
                if (map2.containsKey(s))
                    map2.put(s, map2.get(s) + 1);
                else
                    map2.put(s, 1);
            }
        }
        int union = 0;
        int inter = 0;
        for (String s : map1.keySet()) {
            if (map2.containsKey(s)) {
                union += Math.max(map1.get(s), map2.get(s));
                inter += Math.min(map1.get(s), map2.get(s));
                map2.remove(s);
            }
            else
                union += map1.get(s);
        }
        for (String s : map2.keySet())
            union += map2.get(s);

        if (union == 0 && inter == 0)
            return 65536;
        return (inter * 65536) / union;
    }
}
