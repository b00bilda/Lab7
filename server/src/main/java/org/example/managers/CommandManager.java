package org.example.managers;

import org.example.managers.commands.BaseCommand;
import org.example.managers.commands.*;
import org.example.system.Request;

import java.util.HashMap;

public class CommandManager {
    public static HashMap<String, BaseCommand> commandList;

    public CommandManager() {
        commandList = new HashMap<>();
        commandList.put("help", new HelpCommand());
        commandList.put("exit", new ExitCommand());
        commandList.put("clear", new ClearCommand());
        commandList.put("filter_less_than_weight", new FilterLessThanWeightCommand());
        commandList.put("info", new InfoCommand());
        commandList.put("insert", new InsertCommand());
        commandList.put("min_by_coordinates", new MinByCoordinatesCommand());
        commandList.put("remove", new RemoveCommand());
        commandList.put("remove_greater", new RemoveGreaterCommand());
        commandList.put("remove_lower", new RemoveLowerCommand());
        commandList.put("replace_if_greater", new ReplaceIfGreaterCommand());
        commandList.put("show", new ShowCommand());
        commandList.put("sum_of_age", new SumOfAgeCommand());
        commandList.put("update", new UpdateIdCommand());
        commandList.put("save", new SaveCommand());
        //commandList.put("execute_script", new ExecuteScriptCommand());
        commandList.put("read", new ReadCommand());
    }

    public HashMap<String, BaseCommand> getCommandList() {
        return commandList;
    }

    public String startExecuting(Request request) {
        String commandName = request.getMessage().split(" ")[0];
        if (commandList.containsKey(commandName)) {
            BaseCommand command = commandList.get(commandName);
            String message = command.execute(request);
            return message;
        } else {
            return "Command doesn't exist";
        }
    }
}