package connectors;

import models.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class GeneralConnector {
    public List<Festival> festivals;
    public List<Figure> figures;
    public List<Event> events;
    public List<Dynasty> dynasties;
    public List<Place> places;
    Gson gson = new Gson();

    public GeneralConnector() {

    }

    public void getData() throws Exception {
        festivals = gson.fromJson(new FileReader("data/Festival.json"), new TypeToken<List<Festival>>() {
        }.getType());
        figures = gson.fromJson(new FileReader("data/Figure.json"), new TypeToken<List<Figure>>() {
        }.getType());
        events = gson.fromJson(new FileReader("data/Event.json"), new TypeToken<List<Event>>() {
        }.getType());
        dynasties = gson.fromJson(new FileReader("data/Dynasty.json"), new TypeToken<List<Dynasty>>() {
        }.getType());
        places = gson.fromJson(new FileReader("data/Place.json"), new TypeToken<List<Place>>() {
        }.getType());
    }

    public void writeToJsonDynasty() throws Exception {
        String json = gson.toJson(dynasties);
        FileWriter writer = new FileWriter("data/Dynasty.json");
        writer.write(json);
        writer.close();
    }

    public void writeToJsonFigure() throws Exception {
        String json = gson.toJson(figures);
        FileWriter writer = new FileWriter("data/Figure.json");
        writer.write(json);
        writer.close();
    }

    public void writeToJsonEvent() throws Exception {
        String json = gson.toJson(events);
        FileWriter writer = new FileWriter("data/Event.json");
        writer.write(json);
        writer.close();
    }

    public void writeToJsonPlace() throws Exception {
        String json = gson.toJson(places);
        FileWriter writer = new FileWriter("data/Place.json");
        writer.write(json);
        writer.close();
    }

    public void writeToJsonFestival() throws Exception {
        String json = gson.toJson(festivals);
        FileWriter writer = new FileWriter("data/Festival.json");
        writer.write(json);
        writer.close();
    }

}