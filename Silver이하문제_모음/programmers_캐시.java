package Silver이하문제_모음;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class programmers_캐시 {
    public static void main(String[] args)
    {

    }
    private static int solution(int cacheSize, String[] cities)
    {
        Deque<String> deque = new LinkedList<>();
        int time = 0;

        for (int i = 0; i < cities.length; i++)
        {
            cities[i] = cities[i].toLowerCase();
            // 가지고있지 않고 캐시 공간이 없다면
            if (!deque.contains(cities[i]) && deque.size() >= cacheSize) {
                if (deque.size() > 0)
                    deque.poll();
                deque.add(cities[i]);
                time += 5;
            }
            // 가지고있ㅈ 않고 캐시 공간이 있다면
            else if (!deque.contains(cities[i]) && deque.size() < cacheSize) {
                deque.add(cities[i]);
                time += 5;
            }
            // 가지고있다면 빼고 다시 넣기
            else if (deque.contains(cities[i]) && cacheSize > 0){
                time += 1;
                deque.remove(cities[i]);
                deque.add(cities[i]);
            }
            else
                time += 5;
        }

        return time;
    }
}
