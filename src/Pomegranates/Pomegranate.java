package Pomegranates;

import java.util.Iterator;
import java.util.List;

public class Pomegranate implements Iterable<Seed> {
    private List<Seed> seeds;

    public Pomegranate() {
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

    @Override
    public Iterator<Seed> iterator() {
        return seeds.iterator();
    }
}
