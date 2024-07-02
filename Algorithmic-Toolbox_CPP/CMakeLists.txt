cmake_minimum_required(VERSION 3.28)
project(Algorithmic-Toolbox_CPP)

set(CMAKE_CXX_STANDARD 17)

add_executable(MohMoh cpp_stress_test.cpp)
add_executable(MohFib Fibonacci.cpp)
add_executable(MohFiblastnum Fibonacci_last_number.cpp)
add_executable(MohGCD GCD.cpp)
add_executable(MohLCM LCM.cpp)
add_executable(MohFibmodulom Fibonacci_modulo_m.cpp)
