package ru.mano.aviasales.model;

public class Ticket {
    private City source;
    private City destination;

    public Ticket(City source, City destination) {
        this.source = source;
        this.destination = destination;
    }

    public City getSource() {
        return source;
    }

    public void setSource(City source) {
        this.source = source;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }
}
