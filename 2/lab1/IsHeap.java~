import java.util.*;
import java.io.*;

public class IsHeap {

	FastScanner in;
	PrintWriter out;
	
	public boolean isHeap(int[] arr, int i) {
		if (2*i <= arr.length - 1) {
			if (2*i + 1 <= arr.length - 1) {
				if (arr[i] <= arr[2*i] && (arr[i] <= arr[2*i + 1])) {
					return (isHeap(arr, 2*i) && isHeap(arr, 2*i + 1));
				} else {
					return false;
				}
			} else if (arr[i] <= arr[2*i]) {
				return true;
			}	else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	public void solve() throws IOException {
		int num = in.nextInt();
		int[] arr = new int[num + 1];
		for (int i = 1; i < num + 1; i++) {
			arr[i] = in.nextInt();
		}
		
		if (isHeap(arr, 1)) {
			out.println("YES");
		} else {
			out.println("NO");
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("isheap.in"));
			out = new PrintWriter(new File("isheap.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] args) {
		new IsHeap().run();
	}
}
