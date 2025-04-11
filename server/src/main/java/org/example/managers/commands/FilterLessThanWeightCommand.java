package org.example.managers.commands;

import org.example.managers.ServerEnvironment;

public class FilterLessThanWeightCommand extends BaseCommand {
    public FilterLessThanWeightCommand() {
        super("filter_less_than_weight");
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("Error: No argument provided. Please specify a weight value.");
            }
            ServerEnvironment.getInstance().getCollectionManager().getCollection().forEach((key, dragon) -> {
                int comparisonWeight = Integer.parseInt(args[0]);
                if (dragon.getWeight() < comparisonWeight) {
                    System.out.println(dragon.toString());
                }
            });
        } catch (IllegalArgumentException e) {
            System.out.println("Something wrong with arguments");
        }
    }

    @Override
    public String getDescription() {
        return "outputs elements which value of weight is lower than given";
    }
}
