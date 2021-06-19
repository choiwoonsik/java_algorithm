package Silver이하문제_모음;

public class top_coder_키위주스 {
    public static void main(String[] args)
    {
        int[] answer = {};
        answer = thePouring(new int[]{30, 20, 10}, new int[]{10, 5, 5}, new int[]{0, 1, 2}, new int[]{1, 2, 0});
        for (int i = 0; i < answer.length; i++)
            System.out.print(answer[i] + " ");
    }
    private static int[] thePouring(int[] capacities, int[] bottles, int[] fromId, int[] toId)
    {
        for (int i = 0; i < fromId.length; i++) {
            // 받을 병의 공간
            int remain = capacities[toId[i]] - bottles[toId[i]];
            // 받을 공간 vs 부을 양
            int real_pour = Math.min(remain, bottles[fromId[i]]);
            bottles[fromId[i]] -= real_pour;
            bottles[toId[i]] += real_pour;
        }
        return bottles;
    }
}
