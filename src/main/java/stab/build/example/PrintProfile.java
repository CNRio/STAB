package stab.build.example;

import org.apache.commons.configuration.Configuration;
import stab.build.profile.AbstractBuildProfile;
import stab.build.tasks.BuildTask;
import stab.build.utils.BuildUtil;

import java.util.List;

public class PrintProfile extends AbstractBuildProfile {

    private Configuration config;

    @Override
    public void setup(Configuration config) {
        this.config = config;
    }

    public List<BuildTask> getTasks() {
        return BuildUtil.getTasks(new PrintTask());
    }
}
