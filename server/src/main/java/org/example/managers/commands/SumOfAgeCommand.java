package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class SumOfAgeCommand extends BaseCommand {
    private long sumOfAges;

    public SumOfAgeCommand() {
        super("sumOfAge");
    }

//    @Override
//    public void execute(String[] args) {
//        System.out.println("sum of ages for all items in the collection: " + getSumOfAges());
//    }
//
//    private long getSumOfAges() {
//        Environment.getInstance().getCollectionManager().getCollection().forEach((s, dragon) -> {
//            sumOfAges = sumOfAges + dragon.getAge();
//        });
//        return sumOfAges;
//    }

    @Override
    public String execute(Request request) {
        return Receiver.sumOfAge(request);
    }

    @Override
    public String getDescription() {
        return "outputs the sum of age field values for all items in the collection";
    }
}
