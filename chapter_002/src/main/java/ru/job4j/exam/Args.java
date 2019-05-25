package ru.job4j.exam;

import org.apache.commons.cli.*;

public class Args {
    private String directory;
    private String output;
    private String name;
    private String[] args;
    private boolean fullMatchSearch;
    private boolean searchByMask;
    private boolean searchByRegular;

    public Args(String[] args) {
        fullMatchSearch = false;
        searchByMask = false;
        searchByRegular = false;
        this.args = args;
        parseOptions();
    }

    public String directory() {
        return directory;
    }

    public String name() {
        return name;
    }

    public String output() {
        return output;
    }

    public boolean fullMatchSearch() {
        return fullMatchSearch;
    }

    public boolean searchByMask() {
        return searchByMask;
    }

    public boolean searchByRegular() {
        return searchByRegular;
    }

    private void parseOptions() {
        Options options = new Options();
        options.addOption(new Option("d", "directory", true, "Directory for search"));
        options.addOption(new Option("n", "name", true, "File name or mask"));
        options.addOption(new Option("o", "output", true, "Result file name"));
        options.addOption(new Option("f", "fullMatchSearch", false, "Full match search"));
        options.addOption(new Option("m", "searchByMask", false, "Search by mask"));
        options.addOption(new Option("r", "searchByRegular", false, "Search by regular"));
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine line = parser.parse(options, args);

            if (line.hasOption("d")) {
                directory = line.getOptionValue("d");
                System.out.println(directory);
            } else {
                throw new UnsupportedOperationException("parameter d (directory) is not set");
            }

            if (line.hasOption("n")) {
                name = line.getOptionValue("n");
                System.out.println(name);
            } else {
                throw new UnsupportedOperationException("parameter n (name) is not set");
            }

            if (line.hasOption("o")) {
                output = line.getOptionValue("o");
                System.out.println(output);
            } else {
                throw new UnsupportedOperationException("parameter o (output) is not set");
            }

            if (line.hasOption("m")) {
                searchByMask = true;
            }
            if (line.hasOption("f")) {
                fullMatchSearch = true;
            }
            if (line.hasOption("r")) {
                searchByRegular = true;
            }
        } catch (ParseException exp) {
            System.out.println("Parse exception:" + exp.getMessage());
        }
    }
}
