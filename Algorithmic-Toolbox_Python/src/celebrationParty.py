from typing import List

kidAges = [2, 4, 6, 10, 10, 10, 10, 10, 10, 12, 14, 15, 24, 36, 48, 100, 120, 240, 250]

groups = []
oldest = -1

for age in kidAges:
    if age > oldest:
        youngest = age
        oldest = youngest + 24
        group = []
        groups.append(group)
    group.append(age)
print(groups)
