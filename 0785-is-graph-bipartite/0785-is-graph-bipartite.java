class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int colour[] = new int[n];

        for(int i = 0; i<n; i++) {
            colour[i] = -1;
        }

        for(int i = 0; i<n; i++) {
            if(colour[i] == -1) {
                if(!dfs(i, 0, colour, graph)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dfs(int node, int c, int[] colour, int[][] graph) {
        colour[node] = c;

        for(int neighbour : graph[node]) {
            if(colour[neighbour] == -1) {
                if(!dfs(neighbour, 1-colour[node], colour, graph)) {
                    return false;
                }
            }
            else if(colour[neighbour] == c) {
                return false;
            }
        }
        return true;
    }
}