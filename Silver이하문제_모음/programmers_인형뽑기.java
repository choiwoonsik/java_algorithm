package Silver이하문제_모음;

import java.util.*;

public class programmers_인형뽑기 {
    public static void main(String[] args)
    {
    }
    public int solution(int[][] board, int[] moves)
    {
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for (int i = 0; i < moves.length; i++)
        {
            int robotPos = moves[i] - 1;
            // 로봇 손 출발
            for (int stH = 0; stH < board.length; stH++) {
                if (board[stH][robotPos] != 0) {
                    int top = 0;
                    if (stack.size() > 0)
                        top = stack.peek();
                    if (top == board[stH][robotPos]) {
                        stack.pop();
                        count += 2;
                    }
                    else
                        stack.push(board[stH][robotPos]);
                    board[stH][robotPos] = 0;
                }
            }
        }
        return count;
    }
}
