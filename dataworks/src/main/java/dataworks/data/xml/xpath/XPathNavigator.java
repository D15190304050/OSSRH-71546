package dataworks.data.xml.xpath;

import dataworks.data.xml.IXmlNamespaceResolver;
import dataworks.data.xml.schema.XmlSchemaType;
import dataworks.datetime.DateTime;

import java.lang.reflect.Type;

public class XPathNavigator extends XPathItem implements Cloneable, IXPathNavigable, IXmlNamespaceResolver
{
    @Override
    public XPathNavigator CreateNavigator()
    {
        return null;
    }

    /**
     * Returns {@code true} if this item is a node, and not an atomic value.
     *
     * @return
     */
    @Override
    public boolean isNode()
    {
        return false;
    }

    @Override
    public XmlSchemaType getXmlType()
    {
        return null;
    }

    @Override
    public String getValue()
    {
        return null;
    }

    @Override
    public Object getTypedValue()
    {
        return null;
    }

    @Override
    public Type getValueType()
    {
        return null;
    }

    @Override
    public boolean getValueAsBoolean()
    {
        return false;
    }

    @Override
    public DateTime getValueAsDateTime()
    {
        return null;
    }

    @Override
    public double getValueAsDouble()
    {
        return 0;
    }

    @Override
    public int getValueAsInt()
    {
        return 0;
    }

    @Override
    public long getValueAsLong()
    {
        return 0;
    }

    @Override
    public Object getValueAs(Type returnType, IXmlNamespaceResolver nsResolver)
    {
        return null;
    }
}
