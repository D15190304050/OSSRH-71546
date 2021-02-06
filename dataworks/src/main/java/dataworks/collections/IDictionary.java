package dataworks.collections;

/**
 * The {@link IDictionary} interface represents a generic collection of key/value pairs.
 * @param <TKey> The type of keys in the dictionary.
 * @param <TValue> The type of values in the dictionary.
 * */
public interface IDictionary<TKey, TValue> extends ICollection<KeyValuePair<TKey, TValue>>
{
    /**
     * Gets the element associated with the specified key.
     * @param key The key of the element to get.
     * @return The value associated with the specified key.
     * @exception NullPointerException The specified key is null.
     * @exception dataworks.KeyNotFountException The specified key is not in the {@link IDictionary}.
     * */
    TValue get(TKey key);

    /**
     * Sets the element associated with the specified key.
     * @param key The key of the element to get.
     * @param value The new value associated with the specified key.
     * @exception NullPointerException The specified key is null.
     * @exception dataworks.KeyNotFountException The specified key is not in the {@link IDictionary}.
     * */
    void set(TKey key, TValue value);

    /**
     * Gets an {@link Iterable} containing all the keys of the {@link IDictionary}.
     * @return an {@link Iterable} containing all the keys of the {@link IDictionary}.
     * */
    Iterable<TKey> keys();

    /**
     * Gets an {@link Iterable} containing all the values of the {@link IDictionary}.
     * @return an {@link Iterable} containing all the values of the {@link IDictionary}.
     * */
    Iterable<TValue> values();

    /**
     * Adds an element with the provided key and value to this {@link IDictionary}.
     * @param key The object to use as the key of the element to add.
     * @param value The object to use as the value of the element to add, which is associated with the key.
     * @exception NullPointerException The specified key is null.
     * @exception IllegalArgumentException An element with the same key already exists in this {@link IDictionary}.
     * */
    void add(TKey key, TValue value);

    /**
     * Adds an element with the provided key and value to this {@link IDictionary}.
     * @param keyValuePair The object to add with the key and the value.
     * @exception NullPointerException The specified key value pair is null.
     * @exception IllegalArgumentException An element with the same key already exists in this {@link IDictionary}.
     * */
    void add(KeyValuePair<TKey, TValue> keyValuePair);

    /**
     * Determines whether this {@link IDictionary} contains an element with the specified key.
     * @param key The key to locate in this {@link IDictionary}.
     * @return <code>true</code> if this {@link IDictionary} contains an element with the key; otherwise,
     * <code>false</code>.
     * @exception NullPointerException The specified key is null.
     * */
    boolean containsKey(TKey key);

    /**
     * Determines whether this {@link IDictionary} contains an element with the specified value.
     * @param value The value to locate in this {@link IDictionary}.
     * @return <code>true</code> if this {@link IDictionary} contains an element with the value; otherwise,
     * <code>false</code>.
     * */
    public boolean containsValue(TValue value);

    /**
     * Determines whether this {@link IDictionary} contains an element with the specified key and value.
     * @param key The key to locate in this {@link IDictionary}.
     * @param value The value associated with the specified key.
     * @return <code>true</code> if this {@link IDictionary} contains an element with the key and value; otherwise,
     * <code>false</code>.
     * @exception NullPointerException The specified key is null.
     * */
    public boolean containsKeyValue(TKey key, TValue value);

    /**
     * Tries to remove a {@link KeyValuePair} with the specified key.
     * Java's generic programming mechanism makes this method have a long name instead just "remove".
     * @param key The key of the element to remove.
     * @return <code>true</code> if the element is successfully removed; otherwise, <code>false</code>. This method
     * also returns <code>false</code> if key was not found in the original {@link IDictionary}.
     * @exception NullPointerException The specified key is null.
     * */
    boolean removeByKey(TKey key);
}
