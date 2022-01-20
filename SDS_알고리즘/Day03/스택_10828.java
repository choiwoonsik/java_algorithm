package SDS_알고리즘.Day03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/*
14
push 1
push 2
top
size
empty
pop
pop
pop
size
empty
pop
push 3
empty
top
 */
public class 스택_10828 {
    static Stack stack = new Stack(new LinkedList<>());

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int val;
            switch (cmd) {
                case "push":
                    val = Integer.parseInt(st.nextToken());
                    push(val);
                    break;
                case "top":
                    top();
                    break;
                case "size":
                    System.out.println(stack.items.size());
                    break;
                case "empty":
                    empty();
                    break;
                case "pop":
                    pop();
                    break;
            }
        }
    }

    private static void empty() {
        if (stack.items.isEmpty()) System.out.println(1);
        else System.out.println(0);
    }

    private static void push(int val) {
        stack.items.addFirst(val);
    }

    private static void pop() {
        if (stack.items.size() == 0) System.out.println(-1);
        else System.out.println(stack.items.remove(0));
    }

    private static void top() {
        if (stack.items.size() == 0) System.out.println(-1);
        else System.out.println(stack.items.get(0));
    }

    private static class Stack {
        LinkedList<Integer> items;

        public Stack(LinkedList<Integer> items) {
            this.items = items;
        }
    }

}
