from sys import stdin
import matplotlib.pyplot as plt

n = int(stdin.readline())
x = []
y = []
for i in range(n):
    data = list(map(int, stdin.readline().split()))
    x.append(data[0])
    y.append(data[1])

plt.figure(1, figsize=(10, 10), dpi=300)
plt.plot(x, y, ".")  # , ms=1)
plt.show()