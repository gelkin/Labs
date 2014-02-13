
import java.util.*;
import java.io.*;

public class MainBridges {
    FastScanner in;
    PrintWriter out;

    ArrayList< LinkedList<IntPair> > graph;
    ArrayList<Boolean> used;
    int edgeCounter = 0;
    int timer;
    int tin[];
    int fup[];

    LinkedList<Integer> bridges;

    void dfs (int v) {
        dfs(v, -1);
    }

    void dfs (int v, int p) {
        used.set(v, true);
        tin[v] = timer;
        fup[v] = timer;
        timer++;
        if (graph.get(v).size() > 0) {
            // System.out.print("vertex = " + v + " ");
            ListIterator<IntPair> it = graph.get(v).listIterator();
            while (it.hasNext()) {
                IntPair to = it.next();
                // System.out.print("to = " + to + " ");
                if (to.first == p)  continue;
                if (used.get(to.first)) {
                    fup[v] = Math.min(fup[v], tin[to.first]);
                    // System.out.println("\nfup[" + v + "] = " + fup[v] + " ");
                } else {
                    // System.out.print(" Arrrr ");
                    dfs(to.first, v);
                    fup[v] = Math.min(fup[v], fup[to.first]);
                    if (fup[to.first] > tin[v]) {
                        // Add new bridge (v, to)
                        bridges.add(to.second);
                    }
                }
            }
            // System.out.print("\n");
        }
    }

    void findBridges() {
        timer = 0;
        for (int i = 0; i < used.size(); ++i) {
            if (!used.get(i)) {
                dfs(i);
            }
        }
    }


    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();


        // TODO!!!
        // Wrong Answer
        graph = new ArrayList<LinkedList<IntPair>>(n);
        used = new ArrayList<Boolean>(n);
        tin = new int[n];
        fup = new int[n];
        bridges = new LinkedList<Integer>();

        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<IntPair>());
            used.add(false);
        }

        int x;
        int y;
        for (int i = 0; i < m; i++) {
            edgeCounter++;
            x = in.nextInt();
            y = in.nextInt();
            graph.get(x - 1).add(new IntPair(y - 1, edgeCounter));
            graph.get(y - 1).add(new IntPair(x - 1, edgeCounter));
        }

        findBridges();

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
        out.print(bridges.size() + "\n");
        Collections.sort(bridges);
        ListIterator<Integer> it = bridges.listIterator();
        while(it.hasNext()) {
            out.print(it.next() + "\n");
        }

    }

    class IntPair {
        int first;
        int second;

        IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("bridges.in"));
            out = new PrintWriter(new File("bridges.out"));

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
        new MainBridges().run();
    }
}
