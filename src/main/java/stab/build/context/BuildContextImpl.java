package stab.build.context;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import stab.build.BuildProperties;
import stab.build.utils.EnvUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Log4j
@Getter
public class BuildContextImpl implements BuildContext {

    private Configuration config;
    private File currentDir;

    private static final String BUILD_SUB_DIR = "build";

    public BuildContextImpl(Configuration config) {
        this.config = config;
        this.currentDir = getBuildDirectory(config);
    }

    private File getBuildDirectory(Configuration config) {
        File tempDir = new File(getRootDirectory(config), BUILD_SUB_DIR);
        File result = new File(tempDir, getBuildID(config));

        if (!result.exists()) {
            result.mkdirs();
        }

        try {
            return result.getCanonicalFile();
        } catch (IOException e) {
            log.error("Build directory cannot be created.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate build ID. Currently use random number. Will change to more meaningful name such as containing date.
     * @param config Config.
     * @return Build ID.
     */
    private String getBuildID(Configuration config) {
        return UUID.randomUUID().toString();
    }

    private static File getRootDirectory(Configuration config) {
        // Default root dir
        File rootDir = EnvUtil.getTempDir();

        String rootDirectoryPath = config.getString(BuildProperties.ROOT_DIRECTORY);

        if (StringUtils.isNotEmpty(rootDirectoryPath)) {
            rootDir = new File(rootDirectoryPath);
        }

        return rootDir;
    }

    @Override
    public File getFile(String key) {
        return null;
    }
}
