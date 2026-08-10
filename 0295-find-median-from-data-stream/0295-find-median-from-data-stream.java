class MedianFinder {

    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap
        right = new PriorityQueue<>();                           // Min Heap
    }

    public void addNum(int num) {

        // Step 1: Put num into appropriate heap
        if (left.isEmpty() || num <= left.peek()) {
            left.offer(num);
        } else {
            right.offer(num);
        }

        // Step 2: Balance the heaps
        if (left.size() > right.size() + 1) {
            right.offer(left.poll());
        } 
        else if (right.size() > left.size()) {
            left.offer(right.poll());
        }
    }

    public double findMedian() {

        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        }

        return left.peek();
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */