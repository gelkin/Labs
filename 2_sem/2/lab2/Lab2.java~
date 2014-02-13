import java.util.*;
import java.io.*;

public class Lab2 {

	FastScanner in;
	PrintWriter out;
	
	
	public void solve() throws IOException {
		int num = in.nextInt();
		DsuTree[] forest = new DsuTree[num + 1]; 
		for (int i = 1; i <= num; i++) {
			
			forest[i] = new DsuTree();
			(forest[i]).set(i);
		}
		
		String str;
		int x; // First set
		int y; // Second set
		while (!"".equals(str = in.next())) {
			if ("union".equals(str)) {
				x = in.nextInt();
				y = in.nextInt();
				if (!isTheSameSet(forest[x], forest[y])) {
					union(forest[x], forest[y]);
				}
			} else {
				x = in.nextInt();
				out.print(getRoot(forest[x]).getMin() + " ");
				out.print(getRoot(forest[x]).getMax() + " ");
				out.print(getRoot(forest[x]).getSize());
				out.print("\n");
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("dsu.in"));
			out = new PrintWriter(new File("dsu.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class DsuTree {
		private DsuTree parent;
		private int rank;
		private int max;
		private int min;
		private int size;
		
		public DsuTree() {
			this.max = 0;
			this.min = 0;
			this.size = 0;
			this.rank = 0;
			this.parent = this;
		}
		
		public void set(int x) {
			this.max = x;
			this.min = x;
			this.size = 1;
			this.rank = 0;
		}
		
		public void setMax(int x) {
			this.max = x;
		}
		
		public void setMin(int x) {
			this.min = x;
		}
		
		public void setSize(int s) {
			this.size = s;
		}
		
		public int getMax() {
			return this.max;
		}
		
		public int getMin() {
			return this.min;
		}
		
		public int getSize() {
			return this.size;
		}
	}
	
	public boolean isTheSameSet(DsuTree obj1, DsuTree obj2) {
		return (getRoot(obj1) == getRoot(obj2));
	}
	
	public DsuTree getRoot(DsuTree obj) {
		if (obj != obj.parent) {
			obj.parent = getRoot(obj.parent);
		}
		return obj.parent;
	}
	
	public void union(DsuTree obj1, DsuTree obj2) {
		
		// Setting max value:
		if (getRoot(obj1).getMax() >= getRoot(obj2).getMax()) {
			getRoot(obj2).setMax(getRoot(obj1).getMax());
		} else {
			getRoot(obj1).setMax(getRoot(obj2).getMax());
		}
		
		// Setting min value
		if (getRoot(obj1).getMin() <= getRoot(obj2).getMin()) {
			getRoot(obj2).setMin(getRoot(obj1).getMin());
		} else {
			getRoot(obj1).setMin(getRoot(obj2).getMin());
		}
		
		// Setting size value
		int s1 = getRoot(obj1).getSize();
		int s2 = getRoot(obj2).getSize();
		getRoot(obj1).setSize(s1 + s2);
		getRoot(obj2).setSize(s1 + s2);
		
		// Relinking of parents, and setting ranks
		if (getRoot(obj1).rank < getRoot(obj2).rank) {
			getRoot(obj1).parent = getRoot(obj2);
		} else {
			getRoot(obj2).parent = getRoot(obj1);
			if (getRoot(obj1).rank == getRoot(obj2).rank) {
				getRoot(obj1).rank = getRoot(obj1).rank + 1;
			}
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
		new Lab2().run();
	}
}
