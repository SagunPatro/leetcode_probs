class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // 1. Check if s is already sorted -> 0 operations
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }

        // 2. Length 2 unsorted is impossible -> -1
        if (n == 2) {
            return -1;
        }

        // Find global minimum and maximum characters
        char minChar = 'z';
        char maxChar = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }

        // 3. Needs 1 operation if min is at index 0 OR max is at index n - 1
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // Check if maxChar exists anywhere in s[1...n-1]
        boolean maxInRest = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) {
                maxInRest = true;
                break;
            }
        }

        // Check if minChar exists anywhere in s[0...n-2]
        boolean minInRest = false;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) {
                minInRest = true;
                break;
            }
        }

        // 4. Needs 3 operations ONLY IF:
        //    s[0] is maxChar AND it is the UNIQUE maxChar
        //    AND s[n-1] is minChar AND it is the UNIQUE minChar
        if (s.charAt(0) == maxChar && !maxInRest && s.charAt(n - 1) == minChar && !minInRest) {
            return 3;
        }

        // 5. Otherwise, 2 operations
        return 2;
    }
}