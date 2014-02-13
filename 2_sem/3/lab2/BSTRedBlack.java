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
	
	private final NodeRB NIL = new NodeRB(null, null, null, 123456789);
	
	class NodeRB {
		public NodeRB left;
		public NodeRB right;
		public NodeRB p;
		public boolean colour = true; // Black ~ false; Red ~ true
		public int data; // value in the node
		
		public NodeRB(NodeRB left, NodeRB right, NodeRB parent, int data) {
			this.left = left;
			this.right = right;
			this.p = p;
			this.data = data;
		}
	}
	

	
	class RedBlack {
		public NodeRB root;
		
		public RedBlack(NodeRB left, NodeRB right, NodeRB p, int data) {
			this.root.left = left;
			this.root.right = right;
			this.root.p = p;
			this.root.data = data;
		}
		
		public Left_Rotate(NodeRB x) {
			NodeRB y = x.right;
			x.right = y.left;
			
			if (y.left.data != NIL.data) {
				y.left.p = x;
			}
			y.p = x.p;
			if (x.p.data == NIL.data) {
				this.left = y.left;
				this.right = y.right;
				this.data = y.data;
				
			}
		} 
		
		
		
		
		protected NodeRB simpleInsert(NodeRB to, int key)
        {
            if (key == to.data)//если такой элемент уже есть, увеличиваем счетчик
            {
                to.count++;
                return to;
            }     
            if (key < to.data) { //нужно добавлять влево
                if (to.left != null) { //там уже есть элемент
                    return this.simpleInsert(to.left, key);
                }
                return to.left = new NodeRB(key, to);
            } else { //добавление вправо
                if (to.right != null) { //там уже есть элемент
					return this.simpleInsert(to.right, key);
				}
				return to.right = new NodeRB(key, to);
			}
		}
		
		//добавление элемента с соблюдением правил красно-черных деревьев
        public NodeRB insert(int key)
        {
            //добавим в дерево элемент n как в обычное бинарное дерево
            NodeRB n = this.simpleInsert(this.root, key);
            //необходимо нормализовать правила красно-черных деревьев
            this.case1(n);          
            //возвращаем добавленный элемент
            return n;
        }
 
        protected void case1(NodeRB n)
        {//Текущий узел N в корне дерева. 
            if (n.parent == null)
            {
                n.isRed = false;
                return;
            }
            this.case2(n);           
        }
        protected void case2(NodeRB n)
        {            
            if (n.parent.isRed == false) //Предок текущего узла черный, свойство 4 не нарушается.
            {
                n.isRed = true;
                //Console.WriteLine(n.data + n.isRed.ToString());
             /**
             * Свойство 5 не нарушается, потому что текущий узел N имеет двух черных листовых потомков, 
             * но так как N является красным, пути до каждого из этих потомков содержит 
             * такое же число черных узлов, что и путь до черного листа, который был 
             * заменен текущим узлом, который был черный.
             * */
                return;
            }
            else
                this.case3(n);
 
        }
        protected void case3(NodeRB n)
        {            
            NodeRB u = this.uncle(n);//дядя
            NodeRB g = this.grandparent(n); //дедушка
            if ((u != null) && (u.isRed == true)) //Если и родитель и дядя красные
            {   //то они оба могут быть прекрашены в черный                
                n.parent.isRed = false;
                u.isRed = false;
                //и дедушка станет красным для сохранения свойства 5
                g.isRed = true;
                //дедушка G теперь может нарушить свойства 2 (Корень — черный) или 4 (Оба потомка каждого красного узла — черные) 
                this.case1(g);//Чтобы это исправить, вся рекурсивно выполняется case1 для дедушки
            }
            else
                this.case4(n);//Родитель P является красным, но дядя U — черный.
        }
        protected void case4(NodeRB n)
        { //тут два случая (4 и 5) объеденены в 1 метод, но возможно неправильно!            
            NodeRB g = this.grandparent(n); //дедушка
            if (g == null)
                return;
            if ((n == n.parent.right) && (n.parent == g.left))
            {
                this.rotate_left(n);//поворот дерева влево
                //n = n.left;
            }
            else if ((n == n.parent.left) && (n.parent == g.right))
            {
                rotate_right(n);//поворот вправо
                //n = n.right;
            }
 
            // Родитель P является красным, но дядя U — черный, текущий узел N — левый\правый потомок P 
            // и P — левый\правый потомок G.               
 
            //цвета P и G меняются и в результате дерево удовлетворяет Свойству 4 (Оба потомка любого красного узла — черные). 
            n.parent.isRed = false;
            g.isRed = true;
            //поворачиваем дедушку 
            if ((n == n.parent.left) && (n.parent == g.left)) {
                this.rotate_right(g);
            } else { // зеркальная ситуация 
                this.rotate_left(g);
            }
        }
    	
    	 //повоторот вправо
        protected void rotate_right(NodeRB n)
        {
            if (n.parent == null)
                return;
            //правый потомок узла ставим в левый родительского
            if (n.left != null)
            {
                n.parent.left = n.right;
                n.parent.left.parent = n.parent;
            }
            else
                n.parent.left = null;
 
            n.right = n.parent;//родительский ставим на его место
            n.parent = n.parent.parent;//ссылку на нового родителя берем у бывшего родителя
 
            n.right.parent = n;//бывшему родителю ставим ссылку на текущий узел
 
            if (n.parent != null)//если нужно поправить ссылку у дедушки
            {
                if (n.parent.left == n.left)
                    n.parent.left = n;
                if (n.parent.right == n.left)
                    n.parent.right = n;
            }
            else //корень дерева поменялся
                this.root = n;
        }
        
        //поворот влево
        protected void rotate_left(NodeRB n)
        {            
            if (n.parent == null)
                return;
            //левый потомок узла ставим в правый родительского
            if (n.left != null)
            {
                n.parent.right = n.left;
                n.parent.right.parent = n.parent;
            }
            else
                n.parent.right = null;
 
            n.left = n.parent;//родительский ставим на его место
            n.parent = n.parent.parent;//ссылку на нового родителя берем у бывшего родителя
 
            n.left.parent = n;//бывшему родителю ставим ссылку на текущий узел
 
            if (n.parent != null)//если нужно поправить ссылку у дедушки
            {
                if (n.parent.left == n.left)
                    n.parent.left = n;
                if (n.parent.right == n.left)
                    n.parent.right = n;
            }
            else //корень дерева поменялся
                this.root = n;
        }
        
        //найти дедушку
        protected NodeRB grandparent(NodeRB n)
        {
            if ((n != null) && (n.parent != null))
                return n.parent.parent;
            else
                return null;
        }
 
        //найти дядю
        protected NodeRB uncle(NodeRB n)
        {
            NodeRB g = grandparent(n);
            if (g == null)
                return null; 
            if (n.parent == g.left)
                return g.right;
            else
                return g.left;
        }
        
        // ################### DELETE #####################
        
        protected NodeRB sibling(NodeRB n) {
        	 if (n == n->parent->left)
                return n->parent->right;
	        else
                return n->parent->left;
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
