package dataworks.collections;

import org.jetbrains.annotations.Contract;

public class KeyValuePair<TKey, TValue>
{
    private TKey key;
    private TValue value;

    public TKey getKey()
    {
        return key;
    }

    public TValue getValue()
    {
        return value;
    }

    @Contract(pure = true)
    public KeyValuePair(TKey key, TValue value)
    {
        this.key = key;
        this.value = value;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object that)
    {
        if (that == null)
            return false;

        if (this == that)
            return true;

        if (that.getClass() == this.getClass())
            return equals((KeyValuePair<TKey, TValue>)that);

        return false;
    }

    public boolean equals(KeyValuePair<TKey, TValue> that)
    {
        if (that == null)
            return false;

        if (this == that)
            return true;

        return this.key.equals(that.key) && this.value.equals(that.value);
    }

    @Override
    public String toString()
    {
        return "[" + key + ", " + value + "]";
    }
}
