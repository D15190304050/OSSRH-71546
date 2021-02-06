package dataworks;

public interface IEqualityComparer
{
    boolean equals(Object x, Object y);
    int getHashCode(Object o);
}
