package stab.build.tasks;

public abstract class AbstractBuildTask implements BuildTask {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    protected static String[] getArgs(String ... args) {
        return args;
    }
}

