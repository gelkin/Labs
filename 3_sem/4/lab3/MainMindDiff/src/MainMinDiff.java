import java.io.*;
import java.util.*;

public class MainMinDiff {
    FastScanner in;
    PrintWriter out;

    Random random;

    public static final int INF_POS = Integer.MAX_VALUE;
    public static final int INF_NEG = Integer.MIN_VALUE;

    // Sum of weights in MST
    int res = INF_POS;

    int[] colors;

    int dsuGet (int v) {
        if (v == colors[v]) {
            return v;
        }
        return (colors[v] = dsuGet(colors[v]));
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

        // boolean[] used = new boolean[n];

        // ArrayList<IntPair> mst = new ArrayList<IntPair>();

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

        int maxWeight = INF_NEG;
        int minWeight;

        mainLoop:
        for (int j = 0; j < m; j++) {

            for (int i = 0; i < n; i++) {
                colors[i] = i;
                // used[i] = false;
            }

            minWeight = weights[j].first;

            for (int i = j; i < m; i++) {
                int a = vertices[weights[i].second].first;
                int b = vertices[weights[i].second].second;
                int weight = weights[i].first;

                if (dsuGet(a) != dsuGet(b)) {
                    maxWeight = Math.max(weight, maxWeight);
                    // mst.add(new IntPair(a, b));
                    dsuUnite(a, b);
                }

                /*
                for (int k = 0; k < n; k++) {
                    System.out.print((colors[k] + 1) + " ");
                }
                System.out.print("\n");
                */
            }

            /*
            for (int i = 0; i < mst.size(); i++) {
                System.out.print("(" + (mst.get(i).first + 1) + ", " + (mst.get(i).second + 1) + ") ");
            }
            System.out.print("\n");
            mst.clear();
            */

            int c = dsuGet(0);
            for (int i = 1; i < n; i++) {
                if (c != dsuGet(i)) {
                    break mainLoop;
                }
            }

            res = Math.min(maxWeight - minWeight, res);
        }

        if (res != INF_POS) {
            out.print("YES\n");
            out.print(res);
        } else {
            out.print("NO");
        }
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
            in = new FastScanner(new File("mindiff.in"));
            out = new PrintWriter(new File("mindiff.out"));

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
        new MainMinDiff().run();
    }
}