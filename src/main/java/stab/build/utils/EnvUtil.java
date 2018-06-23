package stab.build.utils;

import java.io.File;

/**
 * Util class for environment.
 */
public class EnvUtil {

    private static final String LOCAL_TEMP_DIR = "/local/tmp";

    public static File getTempDir() {
        File tmpdir = new File(LOCAL_TEMP_DIR);

        if (tmpdir.isDirectory()) {
            return tmpdir;
        }

        return new File(System.getProperty("java.io.tmpdir"));
    }
}
