package stab.build.profile;

public abstract class AbstractBuildProfile implements BuildProfile {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
