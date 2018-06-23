package stab.build.profile;

import org.apache.commons.configuration.Configuration;
import stab.build.tasks.BuildTask;

import java.util.List;

/**
 * A standard definition of a build.
 */
public interface BuildProfile {

    public void setup(Configuration configuration);

    public List<BuildTask> getTasks();
}
