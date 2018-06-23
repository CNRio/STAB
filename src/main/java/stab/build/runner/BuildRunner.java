package stab.build.runner;

import stab.build.context.BuildContext;
import stab.build.profile.BuildProfile;

public interface BuildRunner {
    public boolean runProfile(BuildProfile profile, BuildContext context);
}
