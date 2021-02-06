package dataworks.data.xml.schema;

import dataworks.data.xml.serialization.XmlSerializationNamespaces;

public abstract class XmlSchemaAnnotated
{
    int lineNumber;
    int linePosition;
    String sourceUri;
    XmlSerializationNamespaces namespaces;
    XmlSchemaObject parent;

    /**
     * Indicates whether this object is currently being processed.
     */
    boolean isProcessing;

    public int getLineNumber()
    {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public int getLinePosition()
    {
        return linePosition;
    }

    public void setLinePosition(int linePosition)
    {
        this.linePosition = linePosition;
    }

    public String getSourceUri()
    {
        return sourceUri;
    }

    public void setSourceUri(String sourceUri)
    {
        this.sourceUri = sourceUri;
    }

    public XmlSerializationNamespaces getNamespaces()
    {
        if (namespaces == null)
            namespaces = new XmlSerializationNamespaces();
        return namespaces;
    }

    public void setNamespaces(XmlSerializationNamespaces namespaces)
    {
        this.namespaces = namespaces;
    }

    public XmlSchemaObject getParent()
    {
        return parent;
    }

    public void setParent(XmlSchemaObject parent)
    {
        this.parent = parent;
    }

    abstract void onAdd();
}
