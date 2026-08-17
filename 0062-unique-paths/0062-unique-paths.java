class Solution {
    public int uniquePaths(int m, int n) {
        ArrayList<ArrayList<Integer>> dp = new ArrayList<>();

        for (int i = 0; i < m; i++) {
        ArrayList<Integer> row = new ArrayList<>();

        for (int j = 0; j < n; j++) {
            row.add(-1);
        }

        dp.add(row);
        }

        for(int i = 0; i<m; i++) {
            for(int j = 0; j<n; j++) {
                if(i == 0 && j == 0) {
                    dp.get(i).set(j, 1);
                }
                else {
                int up = 0;
                int left = 0;
                if(i > 0) up = dp.get(i-1).get(j);
                if(j > 0) left = dp.get(i).get(j-1);
                dp.get(i).set(j, up+left); 
                }
            }
        }
        return dp.get(m-1).get(n-1);
    }
}