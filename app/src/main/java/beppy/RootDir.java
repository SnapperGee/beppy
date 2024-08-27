package beppy;

import java.util.Objects;
import java.nio.file.Path;
import java.nio.file.Files;

final class RootDir
{
    final static String WIN_MAC_ROOT_DIR_NAME = "BPTracker";
    final static String NIX_ROOT_DIR_NAME = '.' + WIN_MAC_ROOT_DIR_NAME;
    final static String DATA_OUTPUT_FILE_NAME = "data.csv";
    final static Path PATH = generatePath();

    static boolean exists() { return Files.exists(PATH); }

    private static Path generatePath()
    {
        final String lowerCaseOSName =
            Objects.requireNonNull(
                System.getProperty("os.name"),
                "Null \"os.name\" sys property.")
            .toLowerCase();

        if(lowerCaseOSName.contains("win"))
        {
            final String windowsAppDataDir =
                Objects.requireNonNull(
                    System.getenv("APPDATA"),
                    "Null Windows \"APPDATA\" env variable.");

            return Path.of(windowsAppDataDir, RootDir.WIN_MAC_ROOT_DIR_NAME).toAbsolutePath();
        }

        if(lowerCaseOSName.contains("mac"))
        {
            final String macHomeDir =
                Objects.requireNonNull(
                    System.getProperty("user.home"),
                    "Null Mac \"user.home\" sys property.");

            return Path.of(macHomeDir, "Library", "Application Support", RootDir.WIN_MAC_ROOT_DIR_NAME).toAbsolutePath();
        }

        if(lowerCaseOSName.contains("nix"))
        {
            final String nixHomeDir =
                Objects.requireNonNull(
                    System.getProperty("user.home"),
                    "Null nix \"user.home\" sys property.");

            return Path.of(nixHomeDir, RootDir.NIX_ROOT_DIR_NAME).toAbsolutePath();
        }

        throw new Error(String.format("Unrecognized operating system: \"%s\"", lowerCaseOSName));
    }
}
