import sys
import pprint

input = sys.stdin.readline

# N개의 작업을 수행한다

# 2차원 배열에 각 작업마다의 선행작업을 달아준다
# 1차원 배열에 각 작업의 선행 작업 개수를 적어준다 -> 하나씩 디스카운트
# 같이 빠진 작업들은 시간을 비교해서 가장 큰 시간만 작업시간에 더해준다

# 큐에 카운트가 줄어서 0이된 노드들을 넣어주는데 
# 시작 시간별로 시간을 따로 계산해야하는 문제가있다
N = int(input())

queue = []
countArr = [0 for i in range(N+1)]
timeArr = [0 for i in range(N+1)]
table = [[] for i in range(N+1)]
result = [0 for i in range(N+1)]
max_time = 0
time = 0

def get_info(order, work_time, count, *befor):
    countArr[order] = count
    timeArr[order] = work_time
    for i in befor:
        for first in i:
            table[first].append(order)

for i in range(N):
    arr = list(map(int, input().split()))
    if len(arr) > 2:
        get_info(i+1, arr[0], arr[1], arr[2:])
    else:
        get_info(i+1, arr[0], arr[1])

for i in range(1, N + 1):
    if countArr[i] == 0:
        queue.append(i)
        time += timeArr[i]
        last = i
        result[i] = timeArr[i]
        
while queue:
    do_work = queue.pop(0)
    for next_work in table[do_work]:
        if result[next_work] < result[do_work] + timeArr[next_work]:
            result[next_work] = result[do_work] + timeArr[next_work]
        countArr[next_work] -= 1
        if countArr[next_work] == 0:
            queue.append(next_work)
 
ans = 0;
for i in range(1, N+1):
    ans = max(ans, result[i]);
print(ans);