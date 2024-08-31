package beppy;

import java.util.Objects;
import java.util.stream.IntStream;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import static java.io.File.separator;

final class Root
{
    private final static String DIR_NAME = "bptracker";

    static enum DirPath
    {
        NIX(System.getProperty("user.home"), ".config", Root.DIR_NAME),
        MAC("Library", "Application Support", Root.DIR_NAME),
        WIN(System.getenv("APPDATA"), Root.DIR_NAME);

        final private Path _path;

        private DirPath(final String... pathSegments)
        {
            this._path =
                pathSegments.length != 0 && IntStream.range(0, pathSegments.length - 1).noneMatch(index -> pathSegments[index] == null)
                ? Path.of(separator, pathSegments).toAbsolutePath()
                : null;
        }

        Path getPath() { return this._path; }
    }

    final static Path PATH = generatePath();

    static boolean exists() { return Files.exists(Root.PATH); }

    static Path create()
    {
        try
        {
            return Files.createDirectory(Root.PATH);
        }
        catch(IOException err)
        {
            err.printStackTrace();
            System.exit(111);
            return null;
        }
    }

    private static Path generatePath()
    {
        final String upperCaseOSName =
            Objects.requireNonNull(
                System.getProperty("os.name"),
                "Null \"os.name\" sys property.")
            .toUpperCase();

        if(upperCaseOSName.contains(Root.DirPath.NIX.toString()) || upperCaseOSName.contains("LINUX"))
        {
            return Objects.requireNonNull(
                Root.DirPath.NIX.getPath(),
                "Null nix user home path."
            );
        }

        if(upperCaseOSName.contains(Root.DirPath.MAC.toString()))
        {
            return Root.DirPath.MAC.getPath();
        }

        if(upperCaseOSName.contains(Root.DirPath.WIN.toString()))
        {
            return Objects.requireNonNull(
                Root.DirPath.WIN.getPath(),
                "Null Windows app data path."
            );
        }

        throw new Error(String.format("Unrecognized operating system: \"%s\"", upperCaseOSName));
    }
}
