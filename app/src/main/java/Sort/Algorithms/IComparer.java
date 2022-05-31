package Sort.Algorithms;

/**
 * Interface that return negative or positive result as compare result.
 */
public interface IComparer<T> {
    /**
     * Compare logic of two values.
     * @param a Value a.
     * @param b Value b.
     * @return Return positive if a rank higher, return negative if b rank higher.
     */
    public int compare(T a, T b);
}
