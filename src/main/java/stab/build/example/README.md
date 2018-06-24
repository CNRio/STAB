# Example for using build platform
Add following line in IntelliJ `Run/Debug Configurations -> Program arguments`:
```
--profile=PrintProfile --property=content=HelloWorld
```

Then run `stab.build.BuildLauncher` as Main class.

If succeeded, you should be able to see log printed out similar to:

```
2018-06-24 02:49:02 INFO  BuildLauncher:41 - Launching build with options: --profile=stab.build.example.PrintProfile --property=content=Hello 
2018-06-24 02:49:02 INFO  BuildRunnerImpl:18 - Running build: PrintProfile
2018-06-24 02:49:02 INFO  BuildRunnerImpl:33 - PrintTask :: setup
2018-06-24 02:49:02 INFO  BuildRunnerImpl:42 - PrintTask :: run
Content passed through command line is: Hello
2018-06-24 02:49:02 INFO  BuildRunnerImpl:51 - PrintTask :: cleanup
```

