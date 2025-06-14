import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaxInSlidingWindow {

    static class StackWithMax extends Stack<Integer> {
        Stack<Integer> auxStack = new Stack<>();

        @Override
        public Integer push(Integer item) {
            auxStack.push(auxStack.isEmpty() || item > auxStack.peek() ? item : auxStack.peek());
            return super.push(item);
        }

        @Override
        public synchronized Integer pop() {
            auxStack.pop();
            return super.pop();
        }

        @Override
        public Integer removeFirst() {
            auxStack.removeFirst();
            return super.removeFirst();
        }

        @Override
        public synchronized void removeElementAt(int index) {
            auxStack.removeElementAt(index);
            super.removeElementAt(index);
        }

        public synchronized Integer max() {
            return auxStack.peek();
        }
    }



    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        StackWithMax window = new StackWithMax();
        Stack<Integer> inputs = new Stack<>();

        for (int i = 0; i < n; ++i) {
            int number = scanner.nextInt();
            inputs.push(number);
        }

        int m = scanner.nextInt();

        for (int i = 0; i < m; ++i) {
            window.push(inputs.elementAt(i));
        }

        for (int i = m - 1; i < n; ++i) {
            System.out.println(window.max());
            window.push(inputs.elementAt(i));
            window.removeFirst();

        }
    }

    static public void main(String[] args) throws IOException {
        new MaxInSlidingWindow().solve();
    }
}
