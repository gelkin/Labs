import java.util.*;
import java.io.*;

public class MainRadix {

	FastScanner in;
	PrintWriter out;
	
	// LSD radix sort
    public static void sort(String[] a, int W, int k) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int d = W - 1; d >= k; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;


            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];


            for (int i = 0; i < N; i++)
                aux[count[ a[i].charAt(d) ]++] = a[i];


            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }
	
	public void solve() throws IOException {
		String str;
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		
		String[] a = new String[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.next();
		}
		int l = m - k;
		sort(a, m, l);
		
		for (int i = 0; i < n; i++) {
			out.println(a[i]);
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("radixsort.in"));
			out = new PrintWriter(new File("radixsort.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean Check;
		String line;

		FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		boolean getCheck() {
			return Check;
		}

		void setCheck(boolean b) {
			Check = b;
		}

		String next() {
			try {
				if (st == null || !st.hasMoreTokens()) {
					if ((line = br.readLine()) != null) {
						st = new StringTokenizer(line);
						setCheck(true);
					} else {
						setCheck(false);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (getCheck()) {
				return st.nextToken();
			} else {
				return "";
			} 
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

	public static void main(String[] args) {
		new MainRadix().run();
	}
}
