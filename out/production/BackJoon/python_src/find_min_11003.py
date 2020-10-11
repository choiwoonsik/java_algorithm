import sys
import queue
input = sys.stdin.readline

N, L = map(int, input().split())
line = list(map(int, input().split()))

my_queue = queue.deque

for i in range(N):
    if my_queue.count == 0:
        my_queue.append(line[i], i)
    while my_queue[0][0] > line[i] and my_queue.count > 1:
        my_queue.popleft(0)
    my_queue.appendleft(line[i])
    while my_queue[-1][1] <= i - L:
        my_queue.popleft(0)
    print(my_queue.pop(), end="")