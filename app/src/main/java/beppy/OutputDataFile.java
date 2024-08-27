package beppy;

import java.nio.file.Path;
import java.nio.file.Files;

final class OutputDataFile
{
    final static String NAME = "data.csv";

    final static Path DIR_PATH = RootDir.ROOT_DIR_PATH;

    final static Path PATH = Path.of(RootDir.ROOT_DIR_PATH.toString(), OutputDataFile.NAME);

    static boolean exists() { return Files.exists(PATH); }
}
