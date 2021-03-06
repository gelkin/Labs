import java.util.*;
import java.io.*;

public class MainMap {

	FastScanner in;
	PrintWriter out;
	
	public final int MAX = 3780503;
	public final int p = 31;
	
	public void solve() throws IOException {
		String str;
		// int num;
		String x; // First arg
		String y; // Second arg
		// while "".equals(str = in.next()) == true
		
		Map map = new Map();
		while (!"".equals(str = in.next())) {
			x = in.next();
			
			switch(str) {
			
			case "put":
				y = in.next();
				map.put(x, y);
				break;
			case "delete":
				y = in.next();
				map.delete(x, y);
				break;
			case "get":
				out.println(map.get(x));
				break;
			case "deleteall":
				map.deleteall(x);
				break;
			default:
				System.out.println("Ошибка!");
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("multimap.in"));
			out = new PrintWriter(new File("multimap.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// MultiMap
	class Map {
		private ArrayList<Element>[] map = new ArrayList[MAX];
		
		private int hash(String str) {
			int[] pow = new int[str.length()];
			pow[0] = 1;
			for (int i = 1; i < str.length(); i++) {
    			pow[i] = pow[i - 1] * p;
			}
			int y = str.charAt(0);
			for (int i = 1; i < str.length(); i++) {
				y += (str.charAt(i) - 'a' + 1) * pow[i];
			}
			return (Math.abs(y) % MAX);
		}
		
		String get(String x) {
			int y = hash(x);
			if (map[y] == null) {
				return "0";
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					String outstr = new Integer(map[y].get(i).values.size()).toString();
					for (int j = 0; j < map[y].get(i).values.size(); j++) {
						outstr += " " + map[y].get(i).values.get(j);
					}
					return outstr;
				}
			}
			return "0";
		}
		
		void put(String x, String z) {
			int y = hash(x);
			if (map[y] == null) {
				map[y] = new ArrayList();
				Element el = new Element(x);
				el.values.add(z);
				map[y].add(el);
				return;
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					for (int j = 0; j < map[y].get(i).values.size(); j++) {
						if (z.equals(map[y].get(i).values.get(j))) {
							return;
						}
					}
					map[y].get(i).values.add(z);
					return;
				}
			}
			Element el = new Element(x);
			el.values.add(z);
			map[y].add(el);
		}
		
		void delete(String x, String z) {
			int y = hash(x);
			if (map[y] == null) {
				return;
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					for (int j = 0; j < map[y].get(i).values.size(); j++) {
						if (z.equals(map[y].get(i).values.get(j))) {
							map[y].get(i).values.remove(j);
							return;
						}
					}
					return;
				} 
			}
		}
		
		void deleteall(String x) {
			int y = hash(x);
			if (map[y] == null) {
				return;
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					map[y].remove(i);
					return;
				} 
			}
		}
	
	}
	
	class Element {
		public String key;
		public ArrayList<String> values;
		
		public Element(String key) {
			this.key = key;
			values = new ArrayList<String>();
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
		new MainMap().run();
	}
}
