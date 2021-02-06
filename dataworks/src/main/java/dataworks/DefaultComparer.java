package dataworks;

import java.io.Serializable;

public final class DefaultComparer implements IComparer, Serializable
{
    /**
     * Compares 2 objects. An implementation of this method must return a value less than 0 if x is less than y, 0 if x
     * is equal to y, or a value greater than 0 if x is greater than y.
     *
     * @param x An object to compare.
     * @param y The other object to compare.
     * @return An int value, which is less than 0 if x is less than y, 0 if x is equal to y, or a value greater than 0 if x is
     * greater than y.
     */
    public int compare(Object x, Object y)
    {
        if (x == y)
            return 0;

        if (x == null)
            return -1;

        if (y == null)
            return 1;

        if (x instanceof Comparable && y instanceof Comparable)
            return ((Comparable) x).compareTo(y);

        return x.hashCode() - y.hashCode();
    }
}
