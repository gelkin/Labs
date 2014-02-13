import java.util.*;
import java.io.*;

public class BSTSimple {

	FastScanner in;
	PrintWriter out;
	
	public void solve() throws IOException {
		String str;
		int num;
		int x; // First arg
		int y; // Second arg
		// while "".equals(str = in.next()) == true
		
		BST set = new BST(null, null, null, 123456789);
		while (!"".equals(str = in.next())) {
			num = in.nextInt();
			System.out.println(str + " " + num);
			switch(str) {
			
			case "insert":
				set.insert(num);
				break;
			case "delete":
				set.delete(num);
				break;
			case "exists":
				out.println(set.exists(num));
				break;
			case "next":
				if (set.key == 123456789 || set.pureNext(set, num) == null) {
					out.println("none");
				} else {
					out.println(set.pureNext(set, num).key);
				}
				break;
			case "prev":
				if (set.key == 123456789 || set.purePrev(set, num) == null) {
					out.println("none");
				} else {
					out.println(set.purePrev(set, num).key);
				}
				break;
			default:
				System.out.println("Ошибка!");
			}
		}
	}

	public void run() {
		try {
			in = new FastScanner(new File("bstsimple.in"));
			out = new PrintWriter(new File("bstsimple.out"));

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class BST {
		BST left;
		BST right;
		BST parent;
		int key;

		/*public BST() {
		
		}*/
		
		public BST(BST left, BST right, BST parent, int key) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.key = key;
		}
		
		void insert(int x) {
			if (this.find(x) != null) {
				return;
			}
			if (this.left == null && this.right == null && this.parent == null && this.key == 123456789) {
				this.key = x;
				return;
			}
			if (x > this.key) {
				//System.out.println("zzz");
				if (this.right != null) {
					this.right.insert(x);
				} else {
					BST element = new BST(null, null, this, x);
					this.right = element;
				}
			} else {
				if (this.left != null) {
					this.left.insert(x);
				} else {
					BST element = new BST(null, null, this, x);
					this.left = element;
				}
			}
		}
		
		public BST pureNext(BST set, int x) {
			if (set == null) {
				return null;
			}
			if (set.key <= x) {
				return pureNext(set.right, x);	
			} else {
				BST y = pureNext(set.left, x);
				return (y == null)? set : y;
			}
			
		}
		
	public BST purePrev(BST set, int x) {
			if (set == null) {
				return null;
			}
			if (set.key >= x) {
				return purePrev(set.left, x);	
			} else {
				BST y = purePrev(set.right, x);
				return (y == null)? set : y;
			}
			
		}
		
		public BST find(int x) {
			if (this == null || x == this.key) {
				return this;
			}
			if (x < this.key) {
				if (this.left != null) {
					return this.left.find(x);
				}
				return null;
			} else {
				if (this.right != null) {
					return this.right.find(x);
				}
				return null;
			}
		}
		
		public String exists(int x) {
			if (this.find(x) == null) {
				return "false";
			}
			return "true";
		}
		
		public BST minimum() {
			if (this.left == null) {
				return this;
			}
			return this.left.minimum();
		}
		
		public BST maximum() {
			if (this.right == null) {
				return this;
			}
			return this.right.maximum();
		}
		
		
		public void delete(int x) {
			if (this.left == null && this.right == null && this.parent == null) {
				if (this.key == x) {
					this.key = 123456789;
					return;
				}
				return;
			}
		
			BST cur = this.find(x);
			System.out.println(cur.key + " " + x);
			if (cur == null) {
				return;
			}
			BST next;
			BST child;
			
			if (cur.left != null && cur.right != null) {
				next = next(cur.key);
				child = new BST(null, null, null, 0);
				if (next == next.parent.left) {
					next.parent.left = null;
				} else {
					next.parent.right = null;
				}
				cur.key = next.key;
				cur.left = next.left;
				cur.right = next.right;
				cur.parent = next.parent; 
			} else if (cur.left != null || cur.right != null){
				System.out.println("O eah, baby");
				next = cur;
				if (next.left != null) {
					System.out.println("Double dare you!!!");
					child = next.left;
				} else {
					child = next.right;
				}
			} else {
				next = cur;
				child = null;
			}
			if (child != null) {
				System.out.println("If you know what I mean");
				child.parent = next.parent;
			}
			if (next.parent == null) {
				key = child.key;
				left = child.left;
				right = child.right;
				parent = null;
			} else if (next == next.parent.left){
				next.parent.left = child;
			} else {
				next.parent.right = child;
			}
			
		}
		
		public BST next(int x) { // if (exists): show key: show "none"
			if (this.right != null) {
				return this.right.minimum();
			}
			BST curparent = this.parent;
			BST cur = this; 
			while (curparent != null && cur == curparent.right) {
				cur = curparent;
				curparent = curparent.parent;
			}
			return curparent;
		}
		
		public BST prev(int x) { // if (exists): show key: show "none"
			if (this.left != null) {
				return this.left.maximum();
			}
			BST curparent = this.parent;
			BST cur = this;
			while (curparent != null && cur == curparent.left) {
				cur = curparent;
				curparent = curparent.parent;
			}
			return curparent;
		}
	}
	
	class Node {
		Node left;
		Node right;
		Node parent;
		int key;

		public Node(Node left, Node right, Node parent, int key) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.key = key;
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
		new BSTSimple().run();
	}
}
