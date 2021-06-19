package Silver이하문제_모음;

import java.util.*;

public class shopping_jewely_programmers {
    public static void main(String[] args)
    {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        int[] arr = solution(gems);
        System.out.println(arr[0]+", "+arr[1]);
    }
    public static int[] solution(String[] gems) {
        Queue<String> q = new LinkedList<>();
        HashSet<String> hs = new HashSet<>();
        HashMap<String, Integer> hm = new HashMap<>();
        int start = 0;
        int end = Integer.MAX_VALUE;

        for(String s : gems) {        //보석종류
            hs.add(s);
        }
        int startPoint = 0;
        for(int i = 0; i < gems.length; i++) {
            hm.put(gems[i], hm.getOrDefault(gems[i], 0) + 1);

            q.add(gems[i]);

            while(true) {
                String temp = q.peek();                //구간 내 보석이 1이상 있으면 start++
                if(hm.get(temp) > 1) {
                    q.poll();
                    start++;
                    hm.put(temp, hm.get(temp) -1);
                }else {
                    break;
                }
            }
            if(hm.size() == hs.size() && end > q.size()) {
                end = q.size();
                startPoint = start;
            }


        }
        return new int[] {startPoint+1, startPoint+ end };
    }
}
