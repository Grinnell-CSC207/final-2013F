### 1e. An Iterative `Heap.swapDown`

    /**
     * Restore the heap property by propagating down from pos.
     */
    public static <T> void swapDown(T[] heap, int size, int pos, 
            Comparator<T> order) {
        int l = left(pos);   // Index of the left child.
        int r = right(pos);  // Index of the right child;

        if (l >= size) {
            // If there's no left child, we're done
            return;
        } else if (r >= size) {
            // If there's no right child, we still need to check
            // the left child.
            if (order.compare(heap[pos], heap[l]) < 0) {
                swap(heap, pos, l);
                // We're done, since the left child must be a leaf
            } // if (heap[pos] < heap[l])
        } else {
            // We have both children.  Which one is bigger?
            if (order.compare(heap[l], heap[r]) >= 0) {
                // Left is bigger 
                if (order.compare(heap[pos], heap[l]) < 0) {
                    swap(heap, pos, l);
                    swapDown(heap, size, l, order);
                } // if heap[pos] < heap[l]
            } else {
                // Right is bigger
                if (order.compare(heap[pos], heap[r]) < 0) {
                    swap(heap, pos, r);
                    swapDown(heap, size, r, order);
                } // if heap[pos] < heap[r]
            } // right is bigger
        } // Two children
    } // swapDown
