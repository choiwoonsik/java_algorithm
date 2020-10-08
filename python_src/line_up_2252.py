import queue
import sys
input = sys.stdin.readline

arr = []
N, M = map(int, input().split())
countArr = [0 for i in range(N + 1)]
board = [[] for i in range(N + 1)]
my_queue = []

# n명의 학생들에 대해서 M번의 키비교 진행
for i in range(M):
    L, R = map(int, input().split())
    arr.append([L, R])

# 비교한 키를 가지고 차수 및 그래프 만들기
for L, R in arr:
    countArr[R] += 1
    board[L].append(R)

for i in range(1, N + 1):
    if countArr[i] == 0:
        my_queue.append(i)

while my_queue:
    num = my_queue.pop(0)
    # 해당 수와 연결된 수와 연결을 끊고 만약 차수가 0이된 수가 있다면 큐에 넣어준다
    for i in board[num]:
        countArr[i] -= 1
        if countArr[i] == 0:
            my_queue.append(i)
    print(num, end=" ")

