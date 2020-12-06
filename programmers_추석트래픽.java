import java.util.*;
import java.util.stream.Stream;

public class programmers_추석트래픽 {
    public static void main(String[] args) {
        String[] lines = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
        int ret;
        ret = solution(lines);
        System.out.println(ret);
    }

    private static int solution(String[] lines) {

        ArrayList<log> list = new ArrayList<>();

        for (int i = 0; i < lines.length; i++)
        {
            String logTime = lines[i].split(" ")[1];
            int hh = ((logTime.charAt(0) - '0') * 10 + logTime.charAt(1) - '0') * 60 * 60; // 시
            int mm = ((logTime.charAt(3) - '0') * 10 + logTime.charAt(4) - '0') * 60; // 분
            int ss = ((logTime.charAt(6) - '0') * 10 + logTime.charAt(7) - '0'); // 초
            int ms = ((logTime.charAt(9) - '0') * 100 + (logTime.charAt(10) - '0') * 10 + logTime.charAt(11) - '0'); // 밀리초

            // 로그의 끝나는 밀리초
            double endTime = (hh + mm + ss) * 1000 + ms;

            // 작업 시간
            String workTimeStr = lines[i].split(" ")[2];
            workTimeStr = workTimeStr.replace("s", "");
            double workTime = Double.parseDouble(workTimeStr) * 1000;

            // 끝나는 시간 - 1000ms + 1ms == 시작 ms , 끝나는 시간 + 999ms (임의 시간 1초동안 겹쳐있는 작업을 확인하기 위해 1초를 더한다)
            double startTime = endTime - workTime + 1;

            // 시작시간, 끝나는시간 + 범위 1초
            list.add(new log(startTime, endTime + 999));
        }
        list.sort(new Comparator<log>() {
            @Override
            public int compare(log o1, log o2) {
                if (o1.end == o2.end) {
                    if (o1.start < o2.start)
                        return -1;
                    else return 1;
                }
                else if (o1.end < o2.end)
                    return -1;
                else
                    return 1;
            }
        });

        System.out.println();
        int cnt;
        int max = 0;
        for (int i = 0; i < list.size(); i++)
        {
            double end = list.get(i).end;
            cnt = 0;
            for (int j = i; j < list.size(); j++)
            {
                if (list.get(j).start <= end)
                    cnt++;
            }
            max = Math.max(max, cnt);
        }

        return max;
    }
    private static class log
    {
        double start;
        double end;

        public log(double start, double end) {
            this.start = start;
            this.end = end;
        }
    }
}
