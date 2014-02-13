
import java.util.*;
import java.io.*;

public class MainBiconv {
    FastScanner in;
    PrintWriter out;

    ArrayList<LinkedList<IntPair>> graph;
    ArrayList<Boolean> used;
    int edgeCounter = -1;
    int c = 0;
    int color[];
    int timer;
    int tin[];
    int fup[];

    LinkedList<Integer> components;

    void dfs(int v) {
        dfs(v, -1);
    }

    void dfs(int v, int p) {
        used.set(v, true);
        tin[v] = timer;
        fup[v] = timer;
        timer++;
        if (graph.get(v).size() > 0) {
            // System.out.print("vertex = " + v + "\n");
            ListIterator<IntPair> it = graph.get(v).listIterator();
            while (it.hasNext()) {
                IntPair to = it.next();
                // System.out.print("to = " + to + " ");
                if (to.first == p)
                    continue;
                if (used.get(to.first)) {
                    if (tin[to.first] < tin[v]) {
                        // System.out.print("YA: " + to.second + "\n");
                        components.add(to.second);
                    }
                    if (fup[v] > tin[to.first]) {
                        // System.out.print(to.second + "ARRRRRRRRRRRRR\n");
                        fup[v] = fup[to.first];
                    }
                    // System.out.println("\nfup[" + v + "] = " + fup[v] + " ");
                } else {
                    // System.out.print(" Arrrr ");
                    components.add(to.second);
                    dfs(to.first, v);
                    if (fup[to.first] >= tin[v]) {
                        // System.out.print("to.first = " + to.first + "\n");
                        c++;
                        int x;
                        while ((x = components.removeLast()) != to.second) {
                            // System.out.print("x = " + x + "AAA\n");
                            color[x] = c;
                        }
                        color[to.second] = c;

                        /*
                        System.out.print("\n");
                        for (int i = 0; i < color.length; i++) {
                            System.out.print(color[i] + " ");
                        }
                        System.out.print("\n");
                        */
                    }
                    // fup[v] = Math.min(fup[v], fup[to.first]);
                    if (fup[v] > fup[to.first]) {
                        fup[v] = fup[to.first];

                    }
                }
            }
            // System.out.print("\n");
        }
    }

    void findBiconv() {
        // timer = 0;
        for (int i = 0; i < used.size(); ++i) {
            if (!used.get(i)) {
                timer = 0;
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
        color = new int[m];
        tin = new int[n];
        fup = new int[n];
        components = new LinkedList<Integer>();


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

        findBiconv();

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
        out.print(c + "\n");
        for (int i = 0; i < color.length; i++) {
            out.print(color[i] + " ");
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
            in = new FastScanner(new File("biconv.in"));
            out = new PrintWriter(new File("biconv.out"));

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
        new MainBiconv().run();
    }
}
