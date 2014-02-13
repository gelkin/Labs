import java.util.*;
import java.io.*;

public class MainAnti {

	FastScanner in;
	PrintWriter out;
	int n;
	int[] a;
	int tmp = 0;
	
	void inPut() {
    	n = in.nextInt();
  	  	a = new int[n];
	    for (int i = 0; i < n; i++)
	        a[i] = i + 1;
	}
	
	void createArr() {
    	for (int i = 2;i < n; i++) {
        	tmp = a[i];
        	a[i] = a[i/2];
        	a[i/2] = tmp;
        }
	}
	
	void outPut() {
    	for (int i = 0; i < n; i++)
    	    out.print(a[i] + " ");
	}
	
	public void solve() throws IOException {
		inPut();
		createArr();
		outPut();
	}

	public void run() {
		try {
			in = new FastScanner(new File("antiqs.in"));
			out = new PrintWriter(new File("antiqs.out"));

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
		new MainAnti().run();
	}
}
