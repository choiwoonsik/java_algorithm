import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class sw_Academy_10761_신뢰 {
    static Queue<String> all = new LinkedList<>();
    static Queue<Integer> Orange = new LinkedList<>();
    static Queue<Integer> Blue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++)
        {
            sb.append("#").append(i+1).append(" ");
            int time;
            st = new StringTokenizer(br.readLine());
            int order_cnt = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                String robot = st.nextToken();
                all.add(robot);
                if (robot.equals("O"))
                    Orange.add(Integer.parseInt(st.nextToken()));
                else
                    Blue.add(Integer.parseInt(st.nextToken()));
            }
            time = start_game();
            sb.append(time);
            if (i < N - 1)
                sb.append("\n");
            System.out.println("========");
        }
        System.out.println(sb.toString());
    }

    private static int start_game() {
        int orageTime = 0;
        int blueTime = 0;
        int sumTime = 0;
        int orange_pos = 1;
        int blue_pos = 1;
        int button;
        int Max = Integer.MIN_VALUE;

        while (!all.isEmpty())
        {
            String top = all.peek();
            while (!all.isEmpty() && all.peek().equals(top))
            {
                System.out.println(">>>"+all.poll());
                if (top.equals("O") && !Orange.isEmpty())
                {
                    orageTime = 0;
                    button = Orange.poll();
                    System.out.println("button > "+button);
                    orageTime += Math.abs(button - orange_pos);
                    System.out.println("+ time : " + orageTime);
                    orageTime += 1;
                    orange_pos = button;
                    System.out.println("o T : "+orageTime);
                }
                else if (top.equals("B") && !Blue.isEmpty())
                {
                    blueTime = 0;
                    button = Blue.poll();
                    System.out.println("button > "+button);
                    blueTime += Math.abs(button - blue_pos);
                    System.out.println("+ time : " + blueTime);
                    blueTime += 1;
                    blue_pos = button;
                    System.out.println("b T : "+blueTime+"\n");
                }
            }
            sumTime += Math.max(orageTime, blueTime);
            orageTime = 0;
            blueTime = 0;
        }
        return sumTime;
    }
}
