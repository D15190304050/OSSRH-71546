package dataworks.examples;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class TestConsole
{
    public static void main(String[] args) throws IOException
    {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\DinoStark\\Projects\\IDEA\\DataWorks\\dataworks\\src\\main\\resources\\down.properties"));
        String[] arrayData = properties.getProperty("array").split(" ");
        int[] array = new int[arrayData.length];
        for (int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt(arrayData[i]);

        Arrays.sort(array);
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);
    }
}
