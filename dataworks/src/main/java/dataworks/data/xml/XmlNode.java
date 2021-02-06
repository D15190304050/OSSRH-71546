package dataworks.data.xml;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Iterator;

public class XmlNode implements Serializable, Cloneable, Iterable
{

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator iterator()
    {
        return null;
    }
}
