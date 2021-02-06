package dataworks.data.xml.xpath;

import dataworks.data.xml.IXmlNamespaceResolver;
import dataworks.data.xml.schema.XmlSchemaType;
import dataworks.datetime.DateTime;

import java.lang.reflect.Type;

/**
 * Base class for {@link XPathNavigator} and {@link dataworks.data.xml.schema.XmlAtomicValue}.
 */
public abstract class XPathItem
{
    /**
     * Returns {@code true} if this item is a node, and not an atomic value.
     *
     * @return
     */
    public abstract boolean isNode();

    public abstract XmlSchemaType getXmlType();

    public abstract String getValue();

    public abstract Object getTypedValue();

    public abstract Type getValueType();

    public abstract boolean getValueAsBoolean();

    public abstract DateTime getValueAsDateTime();

    public abstract double getValueAsDouble();

    public abstract int getValueAsInt();

    public abstract long getValueAsLong();

    public abstract Object getValueAs(Type returnType, IXmlNamespaceResolver nsResolver);

    public Object getValueAs(Type returnType)
    {
        return getValueAs(returnType, null);
    }
}
