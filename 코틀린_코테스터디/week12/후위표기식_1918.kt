package 코틀린_코테스터디.week12

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class Problem1918 {
    fun solution() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val stack = Stack<Char>()
        val str = StringBuilder()
        val line = br.readLine().toCharArray()
        for (operator in line) {
            if (operator in 'A'..'Z')
                str.append(operator)
            else {
                // 곱셈, 나눗셈은 같은 경우 계속 빼서 넣어주고 아니면 맨 위에 놔준다
                when (operator) {
                    '*', '/' -> {
                        while (stack.isNotEmpty() && (stack.peek() == '*' || stack.peek() == '/')) str.append(stack.pop())
                        stack.push(operator)
                    }

                    // 괄호 열기는 최우선순위를 갖는다
                    '(' -> stack.push(operator)

                    // +, -는 최하위 우선순위이므로 괄호 열기가 아니라면 끝까지 빼주고 넣는다
                    '+', '-' -> {
                        while (stack.isNotEmpty() && stack.peek() != '(') str.append(stack.pop())
                        stack.push(operator)
                    }

                    // 괄호닫기가 나온경우 괄호 열기까지 포함해서 다 빼준다
                    ')' -> {
                        while (stack.peek() != '(') str.append(stack.pop())
                        stack.pop()
                    }
                }
            }
        }
        while (!stack.isEmpty()) str.append(stack.pop())
        print(str)
    }
}

fun main() {
    Problem1918().solution()
}