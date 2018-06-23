package stab.build.utils;

import org.apache.commons.cli.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util class for CLI options.
 */
public class OptUtil {

    private static final Pattern OPT_KEY_PATTERN = Pattern.compile("^(--[^=]+)=");
    private static final String ARG_SEP = "=";
    private static final String APPLICATION_ARGS_PREAMBLE = "--";
    private static final String DASH_DASH = "--";
    private static final String SPACE = " ";
    private static final String EQUALS = "=";
    private static final String EMPTY_STRING = "";

    public static Options opts(final Option... opts) {
        Options options = new Options();

        for (Option opt : opts) {
            options.addOption(opt);
        }

        return options;
    }

    public static String required(final String key, final CommandLine line) {
        String result = line.getOptionValue(key);
        if (result == null) {
            throw new IllegalStateException("Expected a value for option: " + key);
        }

        return result;
    }

    public static Option required(final String option, final boolean argument, final String description) {
        Option requiredOption = option(option, argument, description + " (required)");
        requiredOption.setRequired(true);

        return requiredOption;
    }

    public static Option option(final String option, final boolean argument, final String description) {
        return new Option(option, argument, description);
    }

    public static String getOptions(final CommandLine line) {
        StringBuilder builder = new StringBuilder();
        for (Option option : line.getOptions()) {
            if (option.hasArg()) {
                builder.append(DASH_DASH).append(option.getOpt()).append(EQUALS).append(option.getValue()).append(SPACE);
            } else {
                builder.append(DASH_DASH).append(option.getOpt()).append(SPACE);
            }
        }

        return builder.toString();
    }

    public static String[] canonicalizeArguments(final String[] args) {
        List<String> canonicalArgs = new ArrayList<>();

        for (String arg: args) {
            if (APPLICATION_ARGS_PREAMBLE.equals(arg)) {
                continue;
            }

            Matcher matcher = OPT_KEY_PATTERN.matcher(arg);
            if (matcher.find()) {
                final String optionMatch = matcher.group(0);
                Validate.isTrue(StringUtils.endsWith(optionMatch, ARG_SEP), "Unexpected argument: " + optionMatch);
                final String option = optionMatch.replace(ARG_SEP, EMPTY_STRING);
                final String value = matcher.replaceAll(EMPTY_STRING);
                canonicalArgs.add(option);
                canonicalArgs.add(value);
            } else {
                canonicalArgs.add(arg);
            }
        }

        return canonicalArgs.toArray(new String[]{});
    }

    public static CommandLine parse(final Options options, final String[] args) {
        CommandLine line = null;

        try {
            CommandLineParser parser = new PosixParser();
            line = parser.parse(options, canonicalizeArguments(args));
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            System.out.println("\n" + e.getMessage() + "\n");
            formatter.printHelp("java", options, true);
        }

        return line;
    }
}

