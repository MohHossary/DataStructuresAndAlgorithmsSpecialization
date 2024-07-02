#include "iostream"


class Main {
public:
    static int fibonacci_naive (int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return fibonacci_naive(n - 1) + fibonacci_naive(n - 2);
    }
    static int fibonacci_fast (const int n) {
        if (n <= 1)
            return n;
        int f [n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < n + 1; ++i) {
            f[i] = f[i-1] + f[i-2];
        }
        return f[n];
    }

};

int main() {

//    for (int i = 0; i < 10; ++i) {
//        std::cout << i << " " << Main::fibonacci_fast(i) << ' ' << Main::fibonacci_naive(i) << std::endl;
//    }

    int n;
    std::cin >> n;
    std::cout << Main::fibonacci_fast(n);
    return 0;
}
