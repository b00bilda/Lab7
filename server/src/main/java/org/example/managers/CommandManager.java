package org.example.managers;

import org.example.managers.commands.BaseCommand;

import java.util.HashMap;

public class CommandManager {
    public static HashMap<String, BaseCommand> commandList;

    public CommandManager() {
        commandList = new HashMap<>();
        commandList.put("help", new Help());
        commandList.put("exit", new Exit());
        commandList.put("clear", new Clear());
        commandList.put("filter_less_than_weight", new FilterLessThanWeight());
        commandList.put("info", new Info());
        commandList.put("insert", new Insert());
        commandList.put("min_by_coordinates", new MinByCoordinates());
        commandList.put("remove", new Remove());
        commandList.put("remove_greater", new RemoveGreater());
        commandList.put("remove_lower", new RemoveLower());
        commandList.put("replace_if_greater", new ReplaceIfGreater());
        commandList.put("show", new Show());
        commandList.put("sum_of_age", new SumOfAge());
        commandList.put("update_id", new UpdateId());
        commandList.put("save", new Save());
        commandList.put("execute_script", new ExecuteScript());
        commandList.put("read", new Read());
    }

    public HashMap<String, BaseCommand> getCommandList() {
        return commandList;
    }
}