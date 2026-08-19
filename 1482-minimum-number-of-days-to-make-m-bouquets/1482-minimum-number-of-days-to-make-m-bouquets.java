class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if((long)m * k > bloomDay.length){
            return -1;
        }
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        int res = -1;

        for(int day : bloomDay) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }
        while(low <= high) {
            int mid = (low+high)/2;
            if(canForm(bloomDay, m,k, mid)) {
                res = mid;
                high = mid-1;
            } 
            else {
                low = mid+1;
            }
        }
        return res;
    }

    public boolean canForm(int[] bloomDay, int m, int k, int day) {
        
        int count = 0;
        int b = 0;

        for(int bloom : bloomDay) {
            if(bloom <= day) {
                count++;
                if(count == k) {
                    b++;
                    count = 0;
                }
            }
            else {
                count = 0;
            }
        }
        return b>= m;
    }
}