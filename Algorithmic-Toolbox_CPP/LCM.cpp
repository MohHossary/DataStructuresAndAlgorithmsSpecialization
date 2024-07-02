#include <iostream>

typedef long long ll;;

ll gcd_naive (ll a, ll b) {
    ll gcd = 0;
    for (ll i = 1; i < a + b; ++i) {
        if (a % i == 0 && b % i == 0) {
            gcd = i;
        }
    }
    return gcd;
}

ll gcd_fast (ll a, ll b) {
    if (b == 0)
        return a;
    ll aprime = a % b;
    return gcd_fast(b, aprime);
}

ll lcm_fast (ll  a, ll b) {
    ll gcd = gcd_fast(a, b);
    ll arelation = a / gcd;
    ll brelation = b / gcd;
    ll lcm = gcd * arelation * brelation;
    return  lcm;
}

int main () {
    ll a;
    ll b;
    std::cin >> a >> b;
    std::cout << lcm_fast(a, b);
}