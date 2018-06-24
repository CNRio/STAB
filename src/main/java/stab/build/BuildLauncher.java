package stab.build;

import lombok.extern.log4j.Log4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.configuration.Configuration;
import stab.build.context.BuildContext;
import stab.build.context.BuildContextImpl;
import stab.build.profile.BuildProfile;
import stab.build.runner.BuildRunner;
import stab.build.runner.BuildRunnerImpl;
import stab.build.utils.BuildUtil;
import stab.build.utils.OptUtil;

@Log4j
public class BuildLauncher {

    private static final String PROFILE = "profile";
    private static final String PROPERTY = "property";

    public static Options getOptions() {
        return OptUtil.opts(
                OptUtil.required(PROFILE, true, "The set of tasks to run"),
                OptUtil.option(PROPERTY, true, "Pass a property to your profile")
        );
    }

    private static Configuration getConfiguration(CommandLine line, BuildProfile profile) {
        return BuildUtil.getConfiguration(line.getOptionValues(PROPERTY));
    }

    private static boolean runBuild(CommandLine line) {
        BuildProfile profile = BuildUtil.getProfile(line.getOptionValue(PROFILE));
        Configuration config = getConfiguration(line, profile);
        BuildContext context = new BuildContextImpl(config);
        BuildRunner runner = new BuildRunnerImpl();
        boolean success;

        profile.setup(config);

        log.info("Launching build with options: " + OptUtil.getOptions(line));
        success = runner.runProfile(profile, context);

        return success;
    }


    public static void main(String... args) {
        int returnCode;

        try {
            returnCode = runBuild(OptUtil.parse(getOptions(), args)) ? 0 : 1;
        } catch (Throwable t) {
            returnCode = 1;
            log.error("Error encountered during processing ", t);
        }

        System.exit(returnCode);
    }
}
