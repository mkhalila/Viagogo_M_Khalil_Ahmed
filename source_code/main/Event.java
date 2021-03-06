package main;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents an Event. Each Event has a unique identifier
 * and a list of Ticket objects.
 */
public class Event {
    //Static counter for the number of Events that have been constructed
    private static int eventCounter;
    //Unique identifier for each Event instance
    private int identifier;
    //List of all the Ticket types for this event
    private ArrayList<Ticket> tickets;
    //Location of the event in the world
    private Location location;

    /**
     * Constructor for an Event: assigns unique id,
     * and creates a a random no. of Tickets
     */
    public Event(Location locationIn) {
        assignID();
        generateTickets();
        assignLocation(locationIn);
    }

    //Assigns ID for this Event
    private void assignID() {
        //Increments eventCounter then assigns ID of this Event
        identifier = ++eventCounter;
    }

    //Generate tickets for this Event
    private void generateTickets() {
        //Initialise Tickets list
        tickets = new ArrayList<>();

        //Generates 0 to 10 Tickets for this Event
        for (int i = 0; i < new Random().nextInt(10); ++i) {
            tickets.add(new Ticket());
        }
    }

    private void assignLocation(Location locationIn) {
        location = locationIn;
    }

    /**
     * Retrieves the location of this event in a pair (x, y)
     * @return Pair (x, y) as location of this event
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Retrieves cheapest Ticket object for this Event.
     * @return cheapest Ticket for this event, if there are 0 tickets then returns null
     */
    private Ticket getCheapestTicket() {
        //If event has no ticket return null
        if (tickets.size() == 0) {
            return null;
        }

        //If event only has one ticket, return that ticket
        if (tickets.size() == 1) {
            return tickets.get(0);
        }

        //Find cheapest ticket
        Ticket cheapest = tickets.get(0);
        for (int i = 1; i < tickets.size(); ++i) {
            if (tickets.get(i).getPrice() < cheapest.getPrice()) {
                cheapest = tickets.get(i);
            }
        }

        //Return cheapest Ticket
        return cheapest;
    }

    /**
     * Returns the identifier for this Event
     * @return identifier of this event
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Resets counter for how many Event instances created to 0
     * Used after Tests are run
     */
    public static void resetEventCounter() {
        Event.eventCounter = 0;
    }

    /**
     * toString() method for Event, returns id, location and cheapest ticket
     * @return String containing ID, location and cheapest ticket
     */
    @Override
    public String toString() {
        String toReturn = "Event " + identifier + " at (" + location.getX()
                + ", " + location.getY() + ")";

        if (getCheapestTicket() == null) return toReturn + " - No Tickets for this Event";

        else return toReturn + " - $" + getCheapestTicket();
    }
}
