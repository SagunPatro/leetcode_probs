class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Create adjacency list
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];

            // prerequisite -> course
            adj.get(prerequisite).add(course);
        }

        int visited[] = new int[numCourses];
        int dfsvis[] = new int[numCourses];

        // Check every component
        for (int i = 0; i < numCourses; i++) {

            if (visited[i] == 0) {

                if (checkCycle(i, adj, visited, dfsvis)) {
                    return false;   // cycle exists
                }
            }
        }

        return true;    // no cycle
    }

    public boolean checkCycle(
        int node,
        ArrayList<ArrayList<Integer>> adj,
        int visited[],
        int dfsvis[]
    ) {

        visited[node] = 1;
        dfsvis[node] = 1;

        for (Integer it : adj.get(node)) {

            // Node not visited
            if (visited[it] == 0) {

                if (checkCycle(it, adj, visited, dfsvis)) {
                    return true;
                }
            }

            // Node is already in current DFS path
            else if (dfsvis[it] == 1) {
                return true;
            }
        }

        // Remove node from current DFS path
        dfsvis[node] = 0;

        return false;
    }
}