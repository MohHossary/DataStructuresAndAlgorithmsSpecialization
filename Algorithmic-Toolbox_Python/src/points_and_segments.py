from sys import stdin
from typing import List, Tuple, Union, Sequence


class Segment:

    def __init__(self, start, end):
        self.start = start
        self.end = end

    def __eq__(self, __value):
        return True if self.start == __value.start and self.end == __value.end else False

    def __ne__(self, __value):
        return not self.__eq__(__value)

    def __gt__(self, __value):
        return self.end < __value.end if self.start == __value.start else self.start > __value.start

    def __ge__(self, __value):
        return not self.__lt__(__value)

    def __lt__(self, __value):
        return self.end > __value.end if self.start == __value.start else self.start < __value.start

    def __le__(self, __value):
        return not self.__gt__(__value)

    def __repr__(self):
        return f'<Segment {self.start} {self.end}>'


def binary_search(a: List[Segment], q, lo, hi):
    if lo >= hi:
        return hi

    mid = (lo + hi) // 2
    if a[mid].start > q:
        return binary_search(a, q, lo, mid)
    elif a[mid].start < q:
        return binary_search(a, q, mid + 1, hi)
    else:  # a[mid].start == q
        # if mid > lo and a[mid - 1].start != q:
        #     return mid
        # else:
        #     return binary_search(a, q, lo, mid)
        if mid == lo or a[mid - 1].start != q:
            return mid
        else:
            return binary_search(a, q, lo, mid - 1)


def lottery(starts, ends, points: List[int]):
    # segments = [Segment(starts[i], ends[i]) for i in range(len(starts))]
    # segments.sort()
    START = 0
    POINT = 1
    END = 2
    cnt = [0] * len(points)
    # fill events
    events: List[Tuple[int, int, int]] = [(points[i], POINT, i) for i in range(len(points))] + [(starts[i], START, None) for i in range(len(starts))] + [(ends[i], END, None) for i in range(len(ends))]
    # sort events
    events.sort()
    # print(events)
    # scan events
    count = 0
    for event in events:
        if event[1] == START: count += 1
        if event[1] == END: count -= 1
        if event[1] == POINT: cnt[event[2]] = count
    return cnt


def points_cover_naive(starts, ends, points):
    assert len(starts) == len(ends)
    count = [0] * len(points)

    for index, point in enumerate(points):
        for start, end in zip(starts, ends):
            if start <= point <= end:
                count[index] += 1
    return count


def test():
    # arr = [7, 7, 10, 10, 10, 10]
    # segments = [Segment(arr[i], 100) for i in range(0, len(arr))]
    #
    # idx = binary_search(segments, 7, 0, len(arr))
    # print(idx)
    # idx = binary_search(segments, 8, 0, len(arr))
    # print(idx)
    # idx = binary_search(segments, 9, 0, len(arr))
    # print(idx)
    # idx = binary_search(segments, 10, 0, len(arr))
    # print(idx)
    # idx = binary_search(segments, 11, 0, len(arr))
    # print(idx)
    # idx = binary_search(segments, 12, 0, len(arr))
    # print(idx)
    #
    ss = [
        Segment(2, 5),
        Segment(3, 4),
        Segment(3, 5),
        Segment(3, 6),
        Segment(3, 7),
        Segment(3, 8),
        Segment(4, 9),
        Segment(4, 4),
        Segment(4, 5),
        Segment(4, 6),
        Segment(4, 6),
        Segment(4, 6),
        Segment(4, 6),
    ]
    ss.sort()
    print(ss)


if __name__ == '__main__':
    # test()
    data = list(map(int, stdin.read().split()))
    n, m = data[0], data[1]
    input_starts, input_ends = data[2:2 * n + 2:2], data[3:2 * n + 2:2]
    input_points = data[2 * n + 2:]

    output_count = lottery(input_starts, input_ends, input_points)
    print(*output_count)
