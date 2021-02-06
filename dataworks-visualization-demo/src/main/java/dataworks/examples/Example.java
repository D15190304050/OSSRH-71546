package dataworks.examples;

import java.io.File;
import java.io.IOException;

public class Example
{
    public static boolean isPrime(int i)
    {
        if (i % 2 != 0)
        {
            int squareRoot = (int) Math.sqrt(i);
            for (int divisor = 3; divisor < squareRoot; divisor += 2)
            {
                if (i % divisor == 0)
                    return false;
            }
            return true;
        }
        return i == 2;
    }

    public static long sumToN(int n)
    {
        long sum = 0;
        for (int i = 0; i <= n; i++)
            sum += n;

        return sum;
    }

    public static void main(String[] args) throws IOException
    {
        File rootDirectory = new File(".");
        for (File filePath : rootDirectory.listFiles())
            System.out.println(filePath.getAbsolutePath());

        rootDirectory.createNewFile();
    }
}
