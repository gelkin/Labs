import java.util.*;
import java.io.*;

public class MainGirl {

	FastScanner in;
	PrintWriter out;
	
	public void solve() throws IOException {
	
  	    int n = in.nextInt();
		double A = in.nextDouble();
		double lb = 0;
		double rb = 1e10;
		
		double med;
		double l1;
		double l2;
		double min;
		for (int i = 0; i < 1000; i++) {
            med = lb + (rb - lb) / 2;
            l1 = med;
            l2 = A;
            min = Math.min(l1, l2);
            for (int j = 2; j < n; j++) {
                double tmp = 2 * (l1 + 1) - l2;
                l2 = l1;
                l1 = tmp;
                min = Math.min(min, l1);
            }
            
            if (min <= 1e-8) {
            	lb = med;
            } else {
            	rb = med;
            }
        }
        double t2 = A;
        double t1 = lb + (rb - lb) / 2;
        double t;
        for (int i = 2; i < n; i++)
        {
            t = 2 * (t1 + 1) - t2;
            t2 = t1;
        	t1 = t;
        }
 
        if (t1 < 0.01)
			out.print(0.01);
		else
			out.print((double) Math.round(t1 * 100) / 100);
	}

	public void run() {
		try {
			in = new FastScanner(new File("garland.in"));
			out = new PrintWriter(new File("garland.out"));

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
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
	}

	public static void main(String[] args) {
		new MainGirl().run();
	}
}
