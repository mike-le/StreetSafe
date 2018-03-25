package io.github.okrand.drivr;

import java.util.List;

public class DecisionTree {

    private DecisionTree decisionTree = DecisionTree.getInstance();

    private static DecisionTree instance =
            new DecisionTree();

    private DecisionTree() {}

    /**
     * @return Command Manager object.
     */
    public static DecisionTree getInstance() {
        return instance;
    }

    public String generateClaim(List<Integer> input) {
        int dec1 = input.get(0);
        int dec2 = input.get(1);

        switch (dec1) {
            //1 signifies an emergency and is handled in the command manager
            case 2:
                return badCar(dec2);
            case 3:
                return badDriver(dec2);
            default:
                return "";
        }
    }

    public String badCar(int dec) {
        switch (dec) {
            case 1:
                return "Inappropriate Speed";
            case 2:
                return "Not Paying Attention to Road";
            case 3:
                return "Swerving";
            case 4:
                return "Tailgating";
            case 5:
                return "Did Not Use Turn Signal";
            default:
                return "";
        }
    }

    public String badDriver(int dec) {
        switch (dec) {
            case 1:
                return "Issue with Lights";
            case 2:
                return "Issue with Tire";
            default:
                return "";
        }
    }
}
