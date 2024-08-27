package beppy;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

final class OutputDataFile
{
    final static String NAME = "data.csv";

    final static Path DIR_PATH = RootDir.PATH;

    final static Path PATH = Path.of(RootDir.PATH.toString(), OutputDataFile.NAME);

    static boolean exists() { return Files.exists(PATH); }

    static void create()
    {
        try
        {
            Files.createDirectory(OutputDataFile.PATH);
        }
        catch(IOException err)
        {
            err.printStackTrace();
            System.exit(222);
        }
    }
}
