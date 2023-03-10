package models;

import java.util.ArrayList;
import java.util.List;

public class Event extends HistoricalObject {
    private List<Integer> dynastiesID;
    private List<Integer> figuresID;
    private List<Integer> placesID;
    private List<Integer> festivalsID;

    public Event(int ID, String name, String dates, String description) {
        super(ID, name, dates, description);
        this.dynastiesID = new ArrayList<>();
        this.figuresID = new ArrayList<>();
        this.placesID = new ArrayList<>();
        this.festivalsID = new ArrayList<>();
    }

    public List<Integer> getDynastiesID() {
        return dynastiesID;
    }

    public List<Integer> getFiguresID() {
        return figuresID;
    }

    public List<Integer> getPlacesID() {
        return placesID;
    }

    public List<Integer> getFestivalsID() {
        return festivalsID;
    }

    public void addDynasty(Dynasty dynasty) {
        if (!dynastiesID.contains(dynasty.getID())){
            this.dynastiesID.add(dynasty.getID());
        }
    }

    public void addFigure(Figure figure) {
        if (!figuresID.contains(figure.getID())){
            this.figuresID.add(figure.getID());
        }
    }

    public void addPlace(Place place) {
        if (!placesID.contains(place.getID())){
            this.placesID.add(place.getID());
        }
    }

    public void addFestival(Festival festival) {
        if (!festivalsID.contains(festival.getID())){
            this.festivalsID.add(festival.getID());
        }
    }
}
