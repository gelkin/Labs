import java.util.*;
import java.io.*;

public class Parking {

	FastScanner in;
	PrintWriter out;

	public void solve() throws IOException {
		String str; // str equals to "enter" or "exit"
		int x; // x contains a number of place
		int n; // Size of the parking
		int m; // Number of requests
		
		n = in.nextInt();
		m = in.nextInt();
		ParkQueue park = new ParkQueue(n);
		
		for (int i = 0; i < m; i++) {
			str = in.next();
			x = in.nextInt();
			if ("enter".equals(str)) {
				out.println(park.whereEnter(x));
			} else {
				park.exit(x);
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("parking.in"));
			out = new PrintWriter(new File("parking.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class ParkQueue {
		private int[] places;
		private int size;
		
		public ParkQueue(int n) {
			assert (n >= 0);
			
			this.size = n;
			int[] arr = new int[n];
			this.places = arr;
		}
		
		// pre: 1 <= x <= this.size && free place for this car exists!
		public int whereEnter(int x) {
			assert (1 <= x && x <= this.size);
			
			for (int i = x - 1; i < (this.size + x - 1); i++) {
				int j = i % this.size;
				if (places[j] == 0) {
					places[j] = 1;
					return (j + 1);
				}
			}
			return -1;
		}
		
		public void exit(int x) {
			assert (1 <= x && x <= this.size);
			
			places[x - 1] = 0;
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
		new Parking().run();
	}
}
