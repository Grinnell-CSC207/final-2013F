### Problem 1c.  Fixing `partition`

    /**
     * Partition the elements between lb and ub, inclusive.
     *
     * @pre lb <= ub.
     */
    static <T> int partition(T[] vals, int lb, int ub, Comparator<T> order) {
        // Put a pivot at the start of the subarray.
        swap(vals, lb, lb + (ub-lb)/2);

        // Set up the bounds
        int small = lb+1;
        int large = ub;

        while (small <= large) {
            // Skip over small elements
            while ((small <= large) 
                     && (order.compare(vals[small], vals[lb]) <= 0)) {
                 ++small;
            } // while
            // Observation: At this point, vals[small] is large

            // Skip over large elements
            while ((small <= large)
                     && (order.compare(vals[large], vals[lb]) >= 0)) {
                 --large;
            } // while
            // Observation: At this point, vals[large] is small

            // We've reached large/small elements, swap 'em
            swap(small++, large--);
        } // while

        // The element at position small is the last small element,
        // so we can swap it with the pivot.
        swap(vals, lb, small);
        return small;
   } // partition(T[], int, int, Comparator<T>)

