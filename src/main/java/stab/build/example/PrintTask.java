package stab.build.example;

import lombok.NoArgsConstructor;
import org.apache.commons.configuration.Configuration;
import stab.build.context.BuildContext;
import stab.build.tasks.AbstractBuildTask;
import stab.data.Data;

import java.util.List;

@NoArgsConstructor
public class PrintTask extends AbstractBuildTask {

    private static final String CONTENT = "content";

    private String content;
    private Configuration config;

    @Override
    public void setup(BuildContext context) {
        this.config = context.getConfig();
        this.content = this.config.getString(CONTENT);
    }

    @Override
    public List<Data> getInput() {
        return null;
    }

    @Override
    public Data getOutput() {
        return null;
    }

    @Override
    public void run(BuildContext context) {
        System.out.println("Content passed through command line is: " + content);
    }

    @Override
    public void cleanup(BuildContext context) {

    }
}
