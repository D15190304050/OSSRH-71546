package dataworks;

import java.util.*;

public class DefaultComparator<T> implements Comparator<T>, IEqualityComparer
{
    @Override
    public int compare(T o1, T o2)
    {
        if ((o1 == null) && (o2 == null))
            return 0;

        if ((o1 == null) && (o2 != null))
            return -1;

        if ((o1 != null) && (o2 == null))
            return 1;

        if ((o1 == o2) || (o1.equals(o2)))
            return 0;

        return o1.hashCode() - o2.hashCode();
    }

    @Override
    public boolean equals(Object x, Object y)
    {
        if ((x == null) && (y == null))
            return true;

        if ((x == null) && (y != null))
            return false;

        if ((x != null) && (y == null))
            return false;

        if ((x == y) || (x.equals(y)))
            return true;

        return x.hashCode() - y.hashCode() == 0;
    }

    @Override
    public int getHashCode(Object o)
    {
        if (o == null)
            throw new NullPointerException("Argument \"o\" cannot be null.");

        return o.hashCode();
    }
}
