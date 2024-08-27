package beppy;

import java.util.Objects;
import java.nio.file.Path;
import java.nio.file.Files;

final class SysPaths
{
    final static String WIN_MAC_ROOT_DIR_NAME = "BPTracker";
    final static String NIX_ROOT_DIR_NAME = '.' + WIN_MAC_ROOT_DIR_NAME;
    final static String DATA_OUTPUT_FILE_NAME = "data.csv";
    final static Path ROOT_DIR_PATH = generateRootDirPath();

    static boolean rootDirExists() { return Files.exists(ROOT_DIR_PATH); }

    static Path generateRootDirPath()
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

            return Path.of(windowsAppDataDir, SysPaths.WIN_MAC_ROOT_DIR_NAME).toAbsolutePath();
        }

        if(lowerCaseOSName.contains("mac"))
        {
            final String macHomeDir =
                Objects.requireNonNull(
                    System.getProperty("user.home"),
                    "Null Mac \"user.home\" sys property.");

            return Path.of(macHomeDir, "Library", "Application Support", SysPaths.WIN_MAC_ROOT_DIR_NAME).toAbsolutePath();
        }

        if(lowerCaseOSName.contains("nix"))
        {
            final String nixHomeDir =
                Objects.requireNonNull(
                    System.getProperty("user.home"),
                    "Null nix \"user.home\" sys property.");

            return Path.of(nixHomeDir, SysPaths.NIX_ROOT_DIR_NAME).toAbsolutePath();
        }

        throw new Error(String.format("Unrecognized operating system: \"%s\"", lowerCaseOSName));
    }
}
