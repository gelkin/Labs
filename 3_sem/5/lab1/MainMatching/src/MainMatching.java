import java.util.*;
import java.io.*;

public class MainMatching {
    LinkedList<Integer>[] graph;

    boolean used[];


    int matching[];

    FastScanner in;
    PrintWriter out;

    boolean dfs (int v) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        Iterator<Integer> iterator = graph[v].listIterator();
        while (iterator.hasNext()) {
            int to = iterator.next();
            if (matching[to] == -1 || dfs(matching[to])) {
                matching[to] = v;
                return true;
            }
        }
        return false;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        graph = new LinkedList[n];
        Arrays.fill(graph, new LinkedList<Integer>());

        matching = new int[k];
        Arrays.fill(matching, -1);

        used = new boolean[n];


        int x;
        int y;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();

            graph[x - 1].add(y - 1);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            Arrays.fill(used, false);
            if (dfs(i)) {
                res++;
            }
        }

        out.print(res);
    }

    public void run() {
        try {
            in = new FastScanner(new File("matching.in"));
            out = new PrintWriter(new File("matching.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        new MainMatching().run();
    }
}



