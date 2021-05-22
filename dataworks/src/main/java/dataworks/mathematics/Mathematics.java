package dataworks.mathematics;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The {@link Mathematics} class provides common mathematical functions.
 */
public class Mathematics
{
    private Mathematics(){}

    private static double epsilon;

    static
    {
        epsilon = 1e-5;
    }


    public static int max(@NotNull int... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (max < array[i])
                max = array[i];
        }

        return max;
    }


    public static double max(@NotNull double... array)
    {
        if (array.length == 0)
        throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        double max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (max < array[i])
                max = array[i];
        }

        return max;
    }


    public static long max(@NotNull long... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        long max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (max < array[i])
                max = array[i];
        }

        return max;
    }


    public static short max(@NotNull short... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        short max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (max < array[i])
                max = array[i];
        }

        return max;
    }


    public static float max(@NotNull float... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        float max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (max < array[i])
                max = array[i];
        }

        return max;
    }


    public static int min(@NotNull int... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (min > array[i])
                min = array[i];
        }

        return min;
    }


    public static double min(@NotNull double... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        double min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (min > array[i])
                min = array[i];
        }

        return min;
    }


    public static long min(@NotNull long... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        long min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (min > array[i])
                min = array[i];
        }

        return min;
    }


    public static short min(@NotNull short... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        short min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (min > array[i])
                min = array[i];
        }

        return min;
    }


    public static float min(@NotNull float... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        float min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (min > array[i])
                min = array[i];
        }

        return min;
    }


    public static int indexOfMax(@NotNull int... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMax = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMax] < array[i])
                indexOfMax = i;
        }

        return indexOfMax;
    }


    public static int indexOfMax(@NotNull double... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMax = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMax] < array[i])
                indexOfMax = i;
        }

        return indexOfMax;
    }


    public static int indexOfMax(@NotNull long... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMax = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMax] < array[i])
                indexOfMax = i;
        }

        return indexOfMax;
    }


    public static int indexOfMax(@NotNull short... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMax = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMax] < array[i])
                indexOfMax = i;
        }

        return indexOfMax;
    }


    public static int indexOfMax(@NotNull float... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMax = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMax] < array[i])
                indexOfMax = i;
        }

        return indexOfMax;
    }


    public static int indexOfMin(@NotNull int... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMin = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMin] > array[i])
                indexOfMin = i;
        }

        return indexOfMin;
    }


    public static int indexOfMin(@NotNull double... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMin = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMin] > array[i])
                indexOfMin = i;
        }

        return indexOfMin;
    }


    public static int indexOfMin(@NotNull long... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMin = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMin] > array[i])
                indexOfMin = i;
        }

        return indexOfMin;
    }


    public static int indexOfMin(@NotNull short... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMin = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMin] > array[i])
                indexOfMin = i;
        }

        return indexOfMin;
    }


    public static int indexOfMin(@NotNull float... array)
    {
        if (array.length == 0)
            throw new IllegalArgumentException("The number of arguments for this method should be at least 1.");

        int indexOfMin = 0;
        for (int i = 1; i < array.length; i++)
        {
            if (array[indexOfMin] > array[i])
                indexOfMin = i;
        }

        return indexOfMin;
    }

    /**
     * Gets the maximum value of `epsilon` that satisfies following statement: 2 double values are assumed equal iff the absolute value of their difference is less than `epsilon`.
     * <p>
     * Assuming x and y are 2 double values. If |x - y| < `epsilon`, then x and y are equal in the context.
     * @return the maximum value of `epsilon` that satisfies following statement: 2 double values are assumed equal iff the absolute value of their difference is less than `epsilon`.
     */
    public static double getEpsilon()
    {
        return epsilon;
    }

    /**
     * Sets the maximum value of `epsilon` that satisfies following statement: 2 double values are assumed equal iff the absolute value of their difference is less than `epsilon`.
     * <p>
     * Assuming x and y are 2 double values. If |x - y| < `epsilon`, then x and y are equal in the context.
     * @param epsilon The maximum value of `epsilon` that satisfies following statement: 2 double values are assumed equal iff the absolute value of their difference is less than `epsilon`.
     */
    public static void setEpsilon(double epsilon)
    {
        validateEpsilon(epsilon);
        Mathematics.epsilon = epsilon;
    }

    public static void validateEpsilon(double epsilon)
    {
        if (epsilon <= 0)
            throw new IllegalArgumentException("Argument \"epsilon\" must be a positive value.");
    }
}
