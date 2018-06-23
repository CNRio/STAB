package stab.build.tasks;

import stab.build.context.BuildContext;

public abstract class AbstractBuildTask implements BuildTask {
    @Override
    public void setup(BuildContext context) throws Exception {
    }

    @Override
    public void cleanup(BuildContext context) throws Exception {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    protected static String[] getArgs(String ... args) {
        return args;
    }
}

