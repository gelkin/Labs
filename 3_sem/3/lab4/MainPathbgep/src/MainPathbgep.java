import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainPathbgep {
    FastScanner in;
    PrintWriter out;

    public static final int INF = 2147483647;

    boolean used[];
    int s = 0; // start vertex

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();


        LinkedList<IntPair>[] graph = new LinkedList[n];
        used = new boolean[n];
        PriorityQueue<IntPair> queue = new PriorityQueue<IntPair>();
        queue.add(new IntPair(0, s));


        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<IntPair>();
            d[i] = INF;
            used[i] = false;
        }

        int x;
        int y;
        int z;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            graph[x - 1].add(new IntPair(y - 1, z));
            graph[y - 1].add(new IntPair(x - 1, z));
        }

        d[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.peek().second;
            int cur_d = -queue.peek().first;
            queue.poll();
            if (cur_d > d[v])  continue;

            ListIterator<IntPair> it = graph[v].listIterator();
            while (it.hasNext()) {
                IntPair pair = it.next();
                int to = pair.first;
                int len = pair.second;
                if (d[v] + len < d[to]) {
                    d[to] = d[v] + len;
                    queue.add(new IntPair(-d[to], to));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            out.print(d[i] + " ");
        }
    }

    class IntPair implements Comparable<IntPair> {
        public int first;
        public int second;

        IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(IntPair x) {
            if (first < x.first) {
                return -1;
            } else if (first == x.first) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("pathbgep.in"));
            out = new PrintWriter(new File("pathbgep.out"));

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
        new MainPathbgep().run();
    }
}


