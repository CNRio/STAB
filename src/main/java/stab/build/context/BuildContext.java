package stab.build.context;

import java.io.File;

/**
 * An object that allows build tasks to interact with external data.
 */
public interface BuildContext {

    /**
     * Get a file to be used with in the context of a build task.
     * @param key File location.
     * @return File handle.
     */
    public File getFile(String key);
}
