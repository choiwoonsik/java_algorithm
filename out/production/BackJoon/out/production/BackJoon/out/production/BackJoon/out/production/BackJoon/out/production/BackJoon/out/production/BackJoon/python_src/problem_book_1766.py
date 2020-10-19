import sys
import queue
input = sys.stdin.readline

N, M = map(int, input().split())
problem = []
countArr = [0 for i in range(N + 1)]
board = [[] for i in range(N + 1)]
myPq = queue.PriorityQueue()

for i in range(M):
    L, R = map(int, input().split())
    problem.append([L, R])

for L, R in problem:
    countArr[R]+=1
    board[L].append(R)

for p in range(1, N+1):
    if countArr[p] == 0:
        myPq.put(p)

while myPq.empty() == False:
    p = myPq.get()
    for next_p in board[p]:
        countArr[next_p] -= 1
        if countArr[next_p] == 0:
            myPq.put(next_p)
    print(p, end=" ")