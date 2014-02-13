import java.io.*;
import java.util.*;

public class MainSpanTree2 {
    FastScanner in;
    PrintWriter out;

    Random random;

    // Sum of weights in MST
    int res = 0;

    int[] colors;

    int dsuGet (int v) {
        if (v == colors[v]) {
            return v;
        }
        colors[v] = dsuGet(colors[v]);
        return colors[v];
    }

    void dsuUnite (int a, int b) {
        if (a != b) {
            a = dsuGet(a);
            b = dsuGet(b);

            if (random.nextInt() % 2 == 0) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            colors[a] = b;
        }
    }

    // first - weight; second - number of pair of vertices
    IntPair[] weights;
    IntPair[] vertices;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();

        weights = new IntPair[m];
        vertices = new IntPair[m];
        colors = new int[n];
        random = new Random(m);

        int x;
        int y;
        int w;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            w = in.nextInt();
            weights[i] = new IntPair(w, i);
            vertices[i] = new IntPair(x - 1, y - 1);
        }

        Arrays.sort(weights);

        for (int i = 0; i < n; i++) {
            colors[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a = vertices[weights[i].second].first;
            int b = vertices[weights[i].second].second;
            int weight = weights[i].first;

            if (dsuGet(a) != dsuGet(b)) {
                res += weight;
                dsuUnite(a, b);
            }
        }

        out.print(res);
    }

    class IntPair implements Comparator<IntPair>, Comparable<IntPair> {
        public int first;
        public int second;

        IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(IntPair y) {
            if (this.first < y.first) {
                return -1;
            }
            if (this.first > y.first) {
                return 1;
            }
            return 0;
        }

        @Override
        public int compare(IntPair x, IntPair y){
            if (x.first < y.first) {
                return -1;
            }
            if (x.first > y.first) {
                return 1;
            }
            return 0;
        }

        @Override
        public boolean equals(Object x) {
            IntPair y = (IntPair) x;
            if (x == null) {
                return false;
            }
            if (y.first == this.first && y.second == this.second) {
                return true;
            }
            return false;
        }
    }


    public void run() {
        try {
            in = new FastScanner(new File("spantree2.in"));
            out = new PrintWriter(new File("spantree2.out"));

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
        new MainSpanTree2().run();
    }
}