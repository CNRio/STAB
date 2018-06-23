package stab.build.tasks;

import stab.build.context.BuildContext;

/**
 * A standard definition of a task.
 */
public interface BuildTask {

    public void setup(BuildContext context) throws Exception;

    public void run(BuildContext context) throws Exception;

    public void cleanup(BuildContext context) throws Exception;
}
