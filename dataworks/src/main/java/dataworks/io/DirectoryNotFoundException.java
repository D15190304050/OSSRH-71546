package dataworks.io;

import java.io.IOException;

public class DirectoryNotFoundException extends IOException
{
    public DirectoryNotFoundException(String message)
    {
        super(message);
    }
}
