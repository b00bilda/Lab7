package org.example.managers.commands;

public class RemoveCommand extends BaseCommand{
    public RemoveCommand() {
        super("remove");
    }

    @Override
    public void execute(String[] args) {
        Environment.getInstance().getCollectionManager().getCollection().remove(Long.parseLong(args[0]));
        System.out.println("you have removed an item from the collection.");
    }

    @Override
    public String getDescription() {
        return "this command removes an item from a collection by its key.";
    }
}
