
import java.util.*;
import java.io.*;

public class MainComponents {
    FastScanner in;
    PrintWriter out;

    ArrayList< LinkedList<Integer> > graph;
    ArrayList<Boolean> used;
    ArrayList<Integer> markedVertices;
    int componentsCounter = 0;

    void dfs (int v) {
        used.set(v, true);
        markedVertices.set(v, componentsCounter);

        if (graph.get(v).size() > 0) {
            ListIterator<Integer> it = graph.get(v).listIterator();
            while (it.hasNext()) {
                int to = it.next();
                if (!used.get(to)) {
                    dfs(to);
                }
            }
        }
    }

    void findComponents() {
        int n = used.size();
        for (int i = 0; i < n; ++i) {
            if (!used.get(i)) {
                componentsCounter++;
                dfs (i);
            }
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new ArrayList<LinkedList<Integer>>(n);
        used = new ArrayList<Boolean>(n);
        markedVertices = new ArrayList<Integer>(n);

        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<Integer>());
            used.add(false);
            markedVertices.add(0);
        }

        int x;
        int y;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            graph.get(x - 1).add(y - 1);
            graph.get(y - 1).add(x - 1);
        }

        findComponents();
        out.print(componentsCounter + "\n");
        for (int i = 0; i < markedVertices.size(); i++) {
            out.print(markedVertices.get(i) + " ");
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("components.in"));
            out = new PrintWriter(new File("components.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;
        private String line;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        String next() {
            try {
                if (st == null || !st.hasMoreTokens()) {
                    if ((line = br.readLine()) != null) {
                        st = new StringTokenizer(line);
                    } else {
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        new MainComponents().run();
    }
}
