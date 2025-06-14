import java.util.*;
import java.io.*;

public class tree_height {
    static class FastScanner {
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

	public static class TreeHeight {
		int n;
		int[] parents;
		int[] heights;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parents = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = in.nextInt();
			}
		}

		int getHeight(int index) {
			if (heights[index] == -1){
				if (parents[index] == -1) { // if root
					heights[index] = 1;
				} else {
					heights[index] = getHeight(parents[index]) + 1;
				}
			}
			return heights[index];
		}

		int computeHeight() {
			heights = new int[n];
			Arrays.fill(heights, -1);
			int maxHeight = 0;
			for (int i = 0; i < n; i++) {
				if (heights[i] == -1) {
					heights[i] = getHeight(i);
				}
				if (heights[i] > maxHeight) {
					maxHeight = heights[i];
				}
			}
			return maxHeight;
		}
	}

	static public void main(String[] args) {
            new Thread(null, () -> {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
