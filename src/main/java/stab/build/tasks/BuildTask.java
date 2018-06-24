package stab.build.tasks;

import org.apache.commons.configuration.Configuration;
import stab.build.context.BuildContext;
import stab.data.Data;

import java.util.List;

/**
 * A standard definition of a task.
 */
public interface BuildTask {

    public void setup(BuildContext context) throws Exception;

    public List<Data> getInput();

    public Data getOutput();

    public void run(BuildContext context) throws Exception;

    public void cleanup(BuildContext context) throws Exception;
}
