package org.example.managers.commands;

import org.example.system.Request;
import org.example.system.Receiver;

public class MinByCoordinatesCommand extends BaseCommand {
    public MinByCoordinatesCommand() {
        super("min_by_coordinates");
    }

//    @Override
//    public void execute(String[] args) {
//        System.out.println(minByCoordinates().toString());
//    }
//
//    public Dragon minByCoordinates() {
//        CollectionManager manager = ServerEnvironment.getInstance().getCollectionManager();
//        if (manager.getCollection().isEmpty()) {
//            return null;
//        } else {
//            Comparator<Dragon> dragonComparator = Comparator
//                    .comparing((Dragon d) -> d.getCoordinates().getX())
//                    .thenComparing(d -> d.getCoordinates().getY());
//            Dragon min = Collections.min(manager.getCollection().values(), dragonComparator);
//            return min;
//        }
//    }

    @Override
    public String execute(Request request) {
        return Receiver.showMinByCoordinates(request);
    }

    @Override
    public String getDescription() {
        return "outputs element from collection which coordinates is min";
    }
}
