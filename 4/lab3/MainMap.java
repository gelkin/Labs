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
		String outstr; // Str by key
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
				map.delete(x);
				break;
			case "get":
				out.println(map.get(x));
				break;
			case "prev":
				out.println(map.prev(x));
				break;
			case "next":
				out.println(map.next(x));
				break;
			default:
				System.out.println("Ошибка!");
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("linkedmap.in"));
			out = new PrintWriter(new File("linkedmap.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Linked Map
	class Map {
		private ArrayList<Node>[] map = new ArrayList[MAX];
		Node last = null;
		Node first = null;
		
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
				return "none";
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					return map[y].get(i).value;
				}
			}
			return "none";
		}
		
		void put(String x, String z) {
			int y = hash(x);
			if (map[y] == null) {
				map[y] = new ArrayList();
				if (first == null) {
					Node element = new Node(x, z, null, null);
					first = element;
					last = element;
					map[y].add(element);
				} else {
					Node element = new Node(x, z, last, null);
					last.next = element;
					last = element;
					map[y].add(element);
				}
				return;
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					map[y].get(i).value = z;
					return;
				}
			}
			if (first == null) {
				Node element = new Node(x, z, null, null);
				first = element;
				last = element;
				map[y].add(element);
			} else {
				Node element = new Node(x, z, last, null);
				last.next = element;
				last = element;
				map[y].add(element);
			}
			
		}
		
		void delete(String x) {
			int y = hash(x);
			if (map[y] == null) {
				return;
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					if (map[y].get(i).prev == null && map[y].get(i).next == null) {
						first = null;
						last = null;
						map[y].remove(i);
						return;
					} else if (map[y].get(i).prev == null) {
						map[y].get(i).next.prev = null;
						first = map[y].get(i).next;
						map[y].remove(i);
						return;
					} else if (map[y].get(i).next == null) {
						map[y].get(i).prev.next = null;
						last = map[y].get(i).prev;
						map[y].remove(i);
						return;
					}
					
					map[y].get(i).prev.next = map[y].get(i).next;
					map[y].get(i).next.prev = map[y].get(i).prev;
					map[y].remove(i);
					return;
				}
			}
		}
		
		String prev(String x) {
			int y = hash(x);
			if (map[y] == null) {
				return "none";
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					if (map[y].get(i).prev != null) {
						return map[y].get(i).prev.value;
					} else {
						return "none";
					}
				}
			}
			return "none";
		}
		
		String next(String x) {
			int y = hash(x);
			if (map[y] == null) {
				return "none";
			}
			for (int i = 0; i < map[y].size(); i++) {
				if (x.equals(map[y].get(i).key)) {
					if (map[y].get(i).next != null) {
						return map[y].get(i).next.value;
					} else {
						return "none";
					}
				}
			}
			return "none";
		}
	}
	
	class Node {
		public String key;
		public String value;
		public Node prev;
		public Node next;
		
		public Node(String key, String value, Node prev, Node next) {
			this.key = key;
			this.value = value;
			this.prev = prev;
			this.next = next;
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
