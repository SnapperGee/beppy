package beppy;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

final class OutputDataFile
{
    final static List<String> HEADER_STRINGS = List.of("Systolic", "Diastolic", "CreatedAt");

    final static String NAME = "data.csv";

    final static Path DIR_PATH = RootDir.PATH;

    final static Path PATH = Path.of(RootDir.PATH.toString(), OutputDataFile.NAME);

    static boolean exists() { return Files.exists(PATH); }

    static Path create()
    {
        try
        {
            if( ! RootDir.exists())
            {
                RootDir.create();
            }

            final Path file = Files.createFile(OutputDataFile.PATH);
            final String header = String.join(", ", OutputDataFile.HEADER_STRINGS);
            return Files.writeString(file, header + '\n');
        }
        catch(IOException err)
        {
            err.printStackTrace();
            System.exit(222);
            return null;
        }
    }

    static Path append(String systolic, String diastolic)
    {
        if( ! systolic.chars().allMatch(Character::isDigit))
        {
            throw new Error(String.format("Systolic contains non digit character: \"%s\"", systolic));
        }

        if( ! diastolic.chars().allMatch(Character::isDigit))
        {
            throw new Error(String.format("Diastolic contains non digit character: \"%s\"", diastolic));
        }

        return OutputDataFile.append(Integer.parseInt(systolic), Integer.parseInt(diastolic));
    }

    static Path append(int systolic, int diastolic)
    {
        try
        {
            return Files.writeString(
                OutputDataFile.PATH,
                String.format("%d, %d, %3$tY/%3$tm/%3$td-%3$tH:%3$tM:%3$tS\n", systolic, diastolic, LocalDateTime.now()),
                StandardOpenOption.APPEND
            );
        }
        catch (IOException err)
        {
            err.printStackTrace();
            System.exit(233);
            return null;
        }
    }

    static List<String> read()
    {
        try
        {
            return Files.readAllLines(OutputDataFile.PATH);
        }
        catch (IOException err)
        {
            err.printStackTrace();
            System.exit(244);
            return null;
        }
    }
}
