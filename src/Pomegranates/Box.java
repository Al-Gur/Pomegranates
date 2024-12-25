package Pomegranates;

import java.util.Iterator;
import java.util.List;

public class Box implements Iterable<Pomegranate> {
    private String name;
    private List<Pomegranate> granates;

    public Box() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pomegranate> getGranates() {
        return granates;
    }

    public void setGranates(List<Pomegranate> granates) {
        this.granates = granates;
    }

    @Override
    public Iterator<Pomegranate> iterator() {
        return granates.iterator();
    }
}
