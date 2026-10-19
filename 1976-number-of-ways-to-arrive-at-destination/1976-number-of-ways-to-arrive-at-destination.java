
import java.util.*;

class Solution {

    static class Pair {
        long first;
        int second;

        Pair(long first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int countPaths(int n, int[][] roads) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int time = road[2];

            adj.get(u).add(new Pair(time, v));
            adj.get(v).add(new Pair(time, u));
        }

        PriorityQueue<Pair> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a.first));

        long[] dist = new long[n];
        int[] ways = new int[n];

        Arrays.fill(dist, Long.MAX_VALUE);

        int mod = 1_000_000_007;

        dist[0] = 0;
        ways[0] = 1;

        pq.add(new Pair(0L, 0));

        while (!pq.isEmpty()) {

            Pair current = pq.poll();

            long dis = current.first;
            int node = current.second;

            if (dis > dist[node]) {
                continue;
            }

            for (Pair it : adj.get(node)) {

                int adjNode = it.second;
                long edgeWeight = it.first;

                long newDist = dis + edgeWeight;

                if (newDist < dist[adjNode]) {

                    dist[adjNode] = newDist;
                    ways[adjNode] = ways[node];

                    pq.add(new Pair(newDist, adjNode));

                } else if (newDist == dist[adjNode]) {

                    ways[adjNode] = (int) (
                        (ways[adjNode] + (long) ways[node]) % mod
                    );
                }
            }
        }

        return ways[n - 1];
    }
}
