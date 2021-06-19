package Silver이하문제_모음;

import java.util.*;

public class programmers_셔틀버스 {
    public static void main(String[] args)
    {
        String[] time = {"09:00", "09:00", "09:00", "09:00"};
        solution(2, 1, 2, time);
    }
    private static String solution(int n, int t, int m, String[] timetable)
    {
        String str = "s";
        int nineClock = 540;
        int lastClock = 23 * 60 + 59;
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < timetable.length; i++)
        {
            String[] hhmm = timetable[i].split(":");
            int hour = Integer.parseInt(hhmm[0]) * 60;
            int min  = Integer.parseInt(hhmm[1]);
            if (hour + min == lastClock)
                continue;
            que.add(hour + min);
        }

        int MaxLate = -1;
        int busGoTime = 540;
        int client;
        int lastClientTime = 0;
        int conTime = 0;
        for (int bus = 0; bus < n; bus++)
        {
            client = 0;
            // 일단 가능한 승객 수내에서 시간안에 있는 사람 태우기
            while (!que.isEmpty())
            {
                int cru = que.peek();
                if (cru <= busGoTime && client < m) {
                    client++;
                    lastClientTime = cru;
                    que.poll();
                }
                else
                    break;
            }
            // 자리가있네
            if (client < m)
                conTime = busGoTime;
            // 자리가 없네
            else
                conTime = lastClientTime - 1;
            busGoTime += t;
            MaxLate = Math.max(conTime, MaxLate);
        }
        StringBuilder hourS = new StringBuilder();
        StringBuilder minS = new StringBuilder();
        int hour = MaxLate / 60;
        int min = MaxLate % 60;

        if (hour < 10 && hour > 0)
            hourS.append('0').append(hour);
        else if (hour == 0)
            hourS.append("00");
        if (min < 10 && min > 0)
            minS.append('0').append(min);
        else if (min == 0)
            minS.append("00");
        else
            minS.append(min/10).append(min%10);
        str = hourS.append(":").append(minS).toString();
        System.out.println(str);
        return str;
    }
}
