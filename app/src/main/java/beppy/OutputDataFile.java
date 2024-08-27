package beppy;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;

final class OutputDataFile
{
    private final static List<String> HEADER = List.of("Systolic", "Diastolic", "CreatedAt");

    final static String NAME = "data.csv";

    final static Path DIR_PATH = RootDir.PATH;

    final static Path PATH = Path.of(RootDir.PATH.toString(), OutputDataFile.NAME);

    static boolean exists() { return Files.exists(PATH); }

    static void create()
    {
        try
        {
            if( ! RootDir.exists())
            {
                RootDir.create();
            }

            final Path file = Files.createFile(OutputDataFile.PATH);
            final String header = String.join(", ", OutputDataFile.HEADER);
            Files.writeString(file, header + '\n');
        }
        catch(IOException err)
        {
            err.printStackTrace();
            System.exit(222);
        }
    }
}
