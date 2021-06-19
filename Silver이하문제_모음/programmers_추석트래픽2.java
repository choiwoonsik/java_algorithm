package Silver이하문제_모음;

import java.util.ArrayList;
import java.util.Comparator;

public class programmers_추석트래픽2 {
    public static void mian(String[] args)
    {

    }
    private static int solution(String[] lines)
    {
        ArrayList<log> list = new ArrayList<>();
        for (int line = 0; line < lines.length; line++)
        {
            String[] info = lines[line].split(" ");
            String[] timeTable = info[1].split(":");
            String task = info[2].replace("s", "");
            String[] sec_mm = timeTable[2].split("\\.");
            double peroid = Double.parseDouble(task);
            int hour = Integer.parseInt(timeTable[0]) * 60 * 60;
            int min = Integer.parseInt(timeTable[1]) * 60;
            int sec = Integer.parseInt(sec_mm[0]);
            int mm = Integer.parseInt(sec_mm[1]);
            mm += (hour + min + sec) * 1000;

            int endT = mm + 999;
            int startT = mm - (int)(peroid * 1000) + 1;
            list.add(new log(startT, endT));
        }
        int max = -1;
        int count;
        list.sort((o1, o2) -> {
            if (o1.endT != o2.endT)
                return Integer.compare(o1.endT, o2.endT);
            else
                return Integer.compare(o1.startT, o2.startT);
        });

        for (int i = 0; i < list.size(); i++) {
            count = 0;
            int endT = list.get(i).endT;
            for (int j = i; j < list.size(); j++) {
                if (list.get(j).startT <= endT)
                    count++;
            }
            max = Math.max(max, count);
        }
        System.out.println(max);
        return max;
    }
    private static class log
    {
        int startT;
        int endT;

        public log(int startT, int endT) {
            this.startT = startT;
            this.endT = endT;
        }
    }
}
