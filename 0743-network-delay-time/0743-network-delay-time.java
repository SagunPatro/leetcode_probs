class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i<=n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge : times) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adj.get(u).add(new Pair(v, wt));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.first - y.first);

        int dist[] = new int[n+1];
        for(int i = 0; i<n+1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[k] = 0;
        pq.add(new Pair(0, k));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            int dis = current.first;
            int node = current.second;

            for(Pair it : adj.get(node)) {
                int adjnode = it.first;
                int edgeweight = it.second;

                if(dis + edgeweight < dist[adjnode]) {
                    dist[adjnode] = dis + edgeweight;
                    pq.add(new Pair(dist[adjnode], adjnode));
                }
            }
        }

        int ans = 0;

        for(int i = 1; i<=n; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, dist[i]);
        }

        return ans;

    }
    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
