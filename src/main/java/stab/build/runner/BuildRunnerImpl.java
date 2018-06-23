package stab.build.runner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import stab.build.context.BuildContext;
import stab.build.profile.BuildProfile;
import stab.build.tasks.BuildTask;

import java.util.List;

@Log4j
@NoArgsConstructor
public class BuildRunnerImpl implements BuildRunner {

    @Override
    public boolean runProfile(BuildProfile profile, BuildContext context) {
        List<BuildTask> tasks = profile.getTasks();
        log.info("Running build: " + profile);

        boolean success = true;

        tasks.forEach(task -> {
            Exception exception = runTask(task, context);
        });
        return false;
    }

    private Exception runTask(BuildTask task, BuildContext context) {
        Exception exception = null;

        try {
            log.info(task + " :: setup");
            task.setup(context);
        } catch (Exception e) {
            log.error("Caught exception while attempting to setup task: " + task);
            exception = e;
        }

        if (exception == null) {
            try {
                log.info(task + " :: run");
                task.run(context);
            } catch (Exception e) {
                log.error("Caught exception while attempting to setup task: " + task);
                exception = e;
            }
        }

        try {
            log.info(task + " :: cleanup");
            task.cleanup(context);
        } catch (Exception e) {
            log.error("Caught exception while attempting to cleanup task: " + task);
            exception = e;
        }

        return exception;
    }
}
