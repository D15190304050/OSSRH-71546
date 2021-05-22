package dataworks;

import org.jetbrains.annotations.Contract;

/**
 * The {@link OutChar} class is a simulation of the "out char" parameters in C#.
 */
public class OutChar
{
    private char value;

    public OutChar(char value)
    {
        this.value = value;
    }

    public char getValue()
    {
        return value;
    }

    public void setValue(char value)
    {
        this.value = value;
    }
}
