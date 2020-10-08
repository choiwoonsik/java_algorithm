import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class work_2056_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] timeArr = new int[N+1];
        int[] countArr = new int[N+1];
        int[] timeMemoArr = new int[N+1];
        for (int i = 0; i < N+1; i++)
            map.add(new ArrayList<>());

        for (int i = 1; i <= N; i++)
        {
            st = new StringTokenizer(br.readLine());
            timeArr[i] = Integer.parseInt(st.nextToken());
            countArr[i] = Integer.parseInt(st.nextToken());
            int n = countArr[i];
            // 작업당 시간과 선행 작업 개수, 선행 작업 리스트를 저장
            for (int j = 0; j < n; j++) {
                int next_work = Integer.parseInt(st.nextToken());
                map.get(next_work).add(i);
                // 다음 일에 대해 선행 작업을 달아 준다
                //선행 작업들이 떨어져 나가서 count가 0이 될때 일을 할수 있다
            }
        }

        for (int i = 1; i < countArr.length; i++) {
            if (countArr[i] == 0) {
                queue.add(i);
                timeMemoArr[i] = timeArr[i];
            }
        }

        while (!queue.isEmpty())
        {
            int do_work = queue.poll();
            for (int next_work : map.get(do_work)){
                if (timeMemoArr[next_work] < timeArr[next_work] + timeMemoArr[do_work])
                    timeMemoArr[next_work] = timeArr[next_work] + timeMemoArr[do_work];
                countArr[next_work] -= 1;
                if (countArr[next_work] == 0)
                    queue.add(next_work);
            }
        }
        int rtn = 0;
        for (int i = 1; i < timeMemoArr.length; i++) {
            rtn = Math.max(rtn, timeMemoArr[i]);
        }
        System.out.println(rtn);
    }
}
