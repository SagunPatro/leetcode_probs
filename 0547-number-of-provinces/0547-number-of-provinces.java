class Solution {
    public int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        int n = isConnected.length;

        for(int i = 0; i<n; i++) {
            adj.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
    for(int j = 0; j < n; j++) {

        if(isConnected[i][j] == 1) {
            adj.get(i).add(j);
        }

    }
}

        int count = 0;

        for(int i = 0; i<n; i++) {
            if(!visited[i]) {
                count++;
                dfs(i, adj, visited);
            }
        }
        return count;
    }

    void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        
        for(int neighbours : adj.get(node)) {
            if(!visited[neighbours]) {
                dfs(neighbours, adj, visited);
            }
        }
    }
}