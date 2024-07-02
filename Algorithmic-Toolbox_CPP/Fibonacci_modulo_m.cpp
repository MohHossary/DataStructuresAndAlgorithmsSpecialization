#include "iostream"

typedef long long ll;

class Main {
public:
    static ll  fibonacci_fast (const ll  n, const ll  m) {
        if (n <= 1)
            return n;
        ll  f [n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < n + 1; ++i) {
            f[i] = (f[i-1] + f[i-2]) % m;
        }
        return f[n] % m;
    }

};

int main() {

//    for (long long i = 0; i < 150; ++i) {
//        std::cout << i << " " << Main::fibonacci_fast(i) << std::endl;
//    }

    ll  n;
    ll  m;
    std::cin >> n >> m;
    std::cout << Main::fibonacci_fast(n, m);
    return 0;
}
