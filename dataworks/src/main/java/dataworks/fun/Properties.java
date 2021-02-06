package dataworks.fun;

import dataworks.OutInt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class Properties
{
    private static final int INITIAL_CAPACITY = 8;

    private final ConcurrentHashMap<String, String> map;

    public Properties()
    {
        this(INITIAL_CAPACITY);
    }

    public Properties(int initialCapacity)
    {
        map = new ConcurrentHashMap<>(initialCapacity);
    }

    public synchronized void setProperty(String key, String value)
    {
        map.put(key, value);
    }

    public String getProperty(String key)
    {
        return map.get(key);
    }

    public Iterable<String> keys()
    {
        return null;
    }

    public boolean containsKey(String key)
    {
        return map.containsKey(key);
    }

    public boolean containsValue(String value)
    {
        return map.containsValue(value);
    }

    public synchronized void removeProperty(String key)
    {
        map.remove(key);
    }

    public synchronized void putAll(Map<String, String> properties)
    {
        map.putAll(properties);
    }

    public synchronized void clear()
    {
        map.clear();
    }

    @Override
    public synchronized String toString()
    {
        return "";
    }

    @Override
    public synchronized int hashCode()
    {
        return map.hashCode();
    }

    @Override
    public synchronized boolean equals(Object o)
    {
        return map.equals(o);
    }

    public int size()
    {
        return map.size();
    }

    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    public synchronized void forEach(BiConsumer<? super Object, ? super Object> action)
    {
        map.forEach(action);
    }

    public Iterable<String> getPropertyNames()
    {
        return null;
    }

    /**
     * Converts encoded &#92;uxxxx to unicode chars and changes special saved chars to their original forms.
     *
     * @param in          The input char array that contains the key or the value to read and parse.
     * @param startOffset Start offset of the part.
     * @param length      Length of the part.
     * @param outBuffer   A buffer that contains the intermediate and final result to return.
     * @return The parsed key or value.
     */
    private static String convertEscape(char[] in, int startOffset, int length, StringBuilder outBuffer)
    {
        char c;
        int end = startOffset + length;
        int left = startOffset;
        while (left < end)
        {
            c = in[left++];
            if (c == '\\')
                break;
            ;
        }

        // No escape (no backslash).
        if (left == end)
            return new String(in, startOffset, length);

        // Backslash found at left - 1, reset the shared buffer, rewind offset.
        outBuffer.setLength(0);
        left--;
        outBuffer.append(in, startOffset, left - startOffset);

        // Process each character one by one.
        // Convert escape characters if meet one.
        while (left < end)
        {
            c = in[left++];

            // If there is a \, then a escape conversion is needed; otherwise, it is a simple character.
            if (c == '\\')
            {
                c = in[left++];

                // \\uxxxx, unicode character.
                if (c == 'u')
                {
                    // Now we have "\\u", a unicode is a "\\uxxxx", next we read the "xxxx."
                    int value = 0;
                    for (int i = 0; i < 4; i++)
                    {
                        c = in[left++];
                        switch (Character.toLowerCase(c))
                        {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + c - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + c - 'a';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                }
                else
                {
                    // Convert escapes.
                    switch (c)
                    {
                        case 't':
                            c = '\t';
                            break;
                        case 'r':
                            c = '\r';
                            break;
                        case 'n':
                            c = '\n';
                            break;
                        case 'f':
                            c = '\f';
                            break;
                    }
                    outBuffer.append(c);
                }
            }
            else
                outBuffer.append(c);
        }

        return outBuffer.toString();
    }

    public synchronized void load(Reader reader) throws IOException
    {
        Objects.requireNonNull(reader, "The argument \"reader\" can not be null.");
        loadProperties(new PropertiesLineReader(reader));
    }

    public synchronized void load(InputStream inputStream) throws IOException
    {
        Objects.requireNonNull(inputStream, "The argument \"inputStream\" can not be null.");
        loadProperties(new PropertiesLineReader(inputStream));
    }

    private void loadProperties(PropertiesLineReader reader) throws IOException
    {
        StringBuilder outputBuffer = new StringBuilder();
        OutInt currentLineLength = new OutInt(0);
        int keyLength;
        int valueStartIndex;
        boolean hasSeparator;
        boolean precedingBackslash;

        while (reader.readLine(currentLineLength) != null)
        {

        }
    }

    public void save(String filePath)
    {

    }

    public void write(OutputStream outputStream)
    {

    }

    public void writeXml(OutputStream outputStream)
    {

    }

    public String toXml()
    {
        return null;
    }
}
