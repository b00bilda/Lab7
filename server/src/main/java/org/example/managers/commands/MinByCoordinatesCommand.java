package org.example.managers.commands;

import org.example.managers.CollectionManager;
import org.example.recources.Dragon;

import java.util.Collections;
import java.util.Comparator;

public class MinByCoordinatesCommand extends BaseCommand {
    public MinByCoordinatesCommand() {
        super("min_by_coordinates");
    }

    @Override
    public void execute(String[] args) {
        System.out.println(minByCoordinates().toString());
    }

    public Dragon minByCoordinates() {
        CollectionManager manager = Environment.getInstance().getCollectionManager();
        if (manager.getCollection().isEmpty()) {
            return null;
        } else {
            Comparator<Dragon> dragonComparator = Comparator
                    .comparing((Dragon d) -> d.getCoordinates().getX())
                    .thenComparing(d -> d.getCoordinates().getY());
            Dragon min = Collections.min(manager.getCollection().values(), dragonComparator);
            return min;
        }
    }

    @Override
    public String getDescription() {
        return "outputs element from collection which coordinates is min";
    }
}
