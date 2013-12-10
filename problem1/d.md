### 1d. An Invariant for `Heap.swapUp`

    /**
     * Restore the heap property n positions 0 .. pos, inclusive, by 
     * propagating up from pos.
     */
    public static <T> void swapUp(T[] heap, int pos, Comparator<T> order) {
        int p;  // Index of the parent

        // Keep swapping up until we reach the top or find a larger parent
        // INVARIANT:
        while ((pos > 0) && 
               (order.compare(heap[pos], heap[p = parent(pos)]) > 0)) {
            // Swap with the parent
            swap(heap, pos, p);
            // And continue upward
            pos = p;
        } // while
    } // swapUp

