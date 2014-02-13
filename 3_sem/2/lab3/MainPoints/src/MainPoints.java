
import java.util.*;
import java.io.*;

public class MainPoints {
    FastScanner in;
    PrintWriter out;

    ArrayList< LinkedList<Integer> > graph;
    ArrayList<Boolean> used;
    int edgeCounter = 0;
    int timer;
    int tin[];
    int fup[];
    boolean isAdded[];

    LinkedList<Integer> points;

    void dfs (int v, int p) {
        used.set(v, true);
        tin[v] = timer;
        fup[v] = timer;
        timer++;
        int children = 0;
        if (graph.get(v).size() > 0) {
            // System.out.print("vertex = " + v + " ");
            ListIterator<Integer> it = graph.get(v).listIterator();
            while (it.hasNext()) {
                int to = it.next();
                // System.out.print("to = " + to + " ");
                if (to == p)  continue;
                if (used.get(to)) {
                    fup[v] = Math.min(fup[v], tin[to]);
                    // System.out.println("\nfup[" + v + "] = " + fup[v] + " ");
                } else {
                    // System.out.print(" Arrrr ");
                    dfs(to, v);
                    fup[v] = Math.min(fup[v], fup[to]);
                    if (fup[to] >= tin[v] && p != -1 && (!isAdded[v])) {
                        // Add new point v
                        points.add(v);
                        isAdded[v] = true;
                    }
                    children++;
                }
            }
            if (p == -1 && children > 1) {
                points.add(v);
            }
            // System.out.print("\n");
        }
    }


    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();


        // TODO!!!
        // Wrong Answer
        graph = new ArrayList<LinkedList<Integer>>(n);
        used = new ArrayList<Boolean>(n);
        tin = new int[n];
        fup = new int[n];
        isAdded = new boolean[n];
        points = new LinkedList<Integer>();

        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<Integer>());
            used.add(false);
        }

        int x;
        int y;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            graph.get(x - 1).add(y - 1);
            graph.get(y - 1).add(x - 1);
        }

        for (int i = 0; i < used.size(); i++) {
            if (!used.get(i)) {
                dfs(i, -1);
            }
        }
        /*
        System.out.println("\n");
        for (int i = 0; i < tin.length; i++) {
            System.out.print(tin[i] + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < fup.length; i++) {
            System.out.print(fup[i] + " ");
        }
        System.out.println("\n");
        */

        out.print(points.size() + "\n");
        Collections.sort(points);
        ListIterator<Integer> it = points.listIterator();
        while(it.hasNext()) {
            out.print((it.next() + 1) + "\n");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("points.in"));
            out = new PrintWriter(new File("points.out"));

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
        new MainPoints().run();
    }
}
