#include <iostream>

long long gcd_naive (long long a, long long b) {
    long long gcd = 0;
    for (long long i = 1; i < a + b; ++i) {
        if (a % i == 0 && b % i == 0) {
            gcd = i;
        }
    }
    return gcd;
}

long long gcd_fast (long long a, long long b) {
    if (b == 0)
        return a;
    long long aprime = a % b;
    return gcd_fast(b, aprime);
}

int main () {
    long long a;
    long long b;
    std::cin >> a >> b;
    std::cout << gcd_fast(a, b);
}