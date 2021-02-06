package dataworks;


import org.jetbrains.annotations.Contract;

/**
 * The {@link OutFloat} class is a simulation of the "out float" parameters in C#.
 */
public class OutFloat
{
    private float value;

    @Contract(pure = true)
    public OutFloat(float value)
    {
        this.value = value;
    }

    public float getValue()
    {
        return value;
    }

    public void setValue(float value)
    {
        this.value = value;
    }
}
