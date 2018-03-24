package io.github.okrand.drivr;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class CommandManager {

    private CommandManager commandManager = CommandManager.getInstance();

    private static CommandManager instance =
            new CommandManager();

    private CommandManager() {}

    /**
     * @return Command Manager object.
     */
    public static CommandManager getInstance() {
        return instance;
    }

    /**
     * @param input from frontend space in form of ("License Number" "State" "dec1" "dec2")
     */
    public void processInput(String input) {
        String[] tokens = input.split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String state = tokens[0];
        String license = tokens[1];
        String numClaim = tokens[2] + tokens[3];
        Report report = generateReport(state, license, numClaim);
        storeReport(report);
    }

    /**
     * @param state of license
     * @param license number
     * @param claim as numbers
     * @return new Report object
     */
    public Report generateReport(String state, String license, String claim) {
        Scanner scanner = new Scanner(claim);
        List<Integer> numericClaim = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            numericClaim.add(scanner.nextInt());
        }
        DecisionTree dTree = DecisionTree.getInstance();
        String claimDescription = dTree.generateClaim(numericClaim);
        return new Report(state, license, claim);
    }

    /**
     * @param report to store
     * Method that stores a report in the database
     */
    public void storeReport(Report report) {}



}
