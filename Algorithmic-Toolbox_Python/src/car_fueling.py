from sys import stdin


def min_refills(distance, tank, stops):
    print("function called")
    for (i, stop) in enumerate(stops):
        print(i, stop)

if __name__ == '__main__':
    d, m, _, *stops = map(int, stdin.read().split())
    print(min_refills(d, m, stops))
