package ru.job4j.io.archive;

import org.apache.commons.cli.*;

public class Args {
    private String directory;
    private String output;
    private String exclude;
    private String[] args;

    public Args(String[] args) {
        this.args = args;
        parseOptions();
    }

    public String directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }

    private void parseOptions() {
        Options options = new Options();
        options.addOption(new Option("d", "directory", true, "directory"));
        options.addOption(new Option("e", "exclude", true, "exclude"));
        options.addOption(new Option("o", "output", true, "output"));
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine line = parser.parse(options, args);

            if (line.hasOption("d")) {
                directory = line.getOptionValue("d");
                System.out.println(directory);
            } else {
                throw new UnsupportedOperationException("parameter d (directory) is not set");
            }

            if (line.hasOption("e")) {
                exclude = line.getOptionValue("e");
                System.out.println(exclude);
            } else {
                throw new UnsupportedOperationException("parameter e (exclude) is not set");
            }

            if (line.hasOption("o")) {
                output = line.getOptionValue("o");
                System.out.println(output);
            } else {
                throw new UnsupportedOperationException("parameter o (output) is not set");
            }
        } catch (ParseException exp) {
            System.out.println("Parse exception:" + exp.getMessage());
        }

    }

}
