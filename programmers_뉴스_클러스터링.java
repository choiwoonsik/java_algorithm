import java.util.*;

public class programmers_뉴스_클러스터링 {
    public static void main(String[] args)
    {
        //J(A, B)는 두 집합의 교집합 크기를 두 집합의 합집합 크기로 나눈 값
        /*
        A 교 B / A 합 B
        모두 공집합이면 1

        교집합에서는 중복된 원소의 개수의 최소값만큼의 개수를
        합집합에서는 중복된 원소의 개수의 최대값만큼의 개수를 가진다
         */
        solution("aa1+aa2", "AAAA12");
    }
    public static int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        Queue<String> que1 = new LinkedList<>();
        Queue<String> que2 = new LinkedList<>();
        HashMap<String, Integer> hash1 = new HashMap();
        HashMap<String, Integer> hash2 = new HashMap();
        StringBuilder str;

        // str1
        for (int i = 0; i < str1.length() - 1; i++)
        {
            str = new StringBuilder();
            if (str1.charAt(i) <= 'z' && str1.charAt(i) >= 'a') {
                if (str1.charAt(i + 1) <= 'z' && str1.charAt(i + 1) >= 'a') {
                    str.append(Character.toString(str1.charAt(i))).append(Character.toString(str1.charAt(i + 1)));
                    if (!que1.contains(str.toString())) {
                        que1.add(str.toString());
                        hash1.put(str.toString(), 1);
                    }
                    else
                        hash1.put(str.toString(), hash1.get(str.toString()) + 1);
                }
            }
        }
        que1.forEach(s -> System.out.print(s + " "));
        System.out.println();

        // str2
        for (int i = 0; i < str2.length() - 1; i++)
        {
            str = new StringBuilder();
            if (str2.charAt(i) <= 'z' && str2.charAt(i) >= 'a') {
                if (str2.charAt(i + 1) <= 'z' && str2.charAt(i + 1) >= 'a') {
                    str.append(Character.toString(str2.charAt(i))).append(Character.toString(str2.charAt(i + 1)));
                    if (!que2.contains(str.toString())) {
                        que2.add(str.toString());
                        hash2.put(str.toString(), 1);
                    }
                    else
                        hash2.put(str.toString(), hash2.get(str.toString()) + 1);
                }
            }
        }
        que2.forEach(s -> System.out.print(s + " "));
        System.out.println();

        int union = 0;
        int inter = 0;
        for (String next : que1) {
            if (hash2.containsKey(next) && hash1.containsKey(next)) {
                union += Math.max(hash1.get(next), hash2.get(next));
                inter += Math.min(hash1.get(next), hash2.get(next));
            }
            else if (hash1.containsKey(next))
                union += hash1.get(next);
            else
                union += hash2.get(next);
            if (que2.contains(next))
                que2.remove(next);
        }
        for (String next : que2) {
            union += hash2.get(next);
        }

        int ret;
        if (union == 0 && inter == 0)
            ret = 65536;
        else
            ret = inter * 65536 / union;
        System.out.println(union +"   "+inter);
        System.out.println(ret);
        return ret;
    }
}
