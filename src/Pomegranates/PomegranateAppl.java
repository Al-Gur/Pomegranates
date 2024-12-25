package Pomegranates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PomegranateAppl {
    public static void main(String[] args) {
        int nBoxes = new Random().nextInt(101) + 100;
        System.out.println("nBoxes = " + nBoxes);
        List<Box> boxes = new ArrayList<>();
        for (int i = 0; i < nBoxes; i++) {
            Box box = new Box();
            List<Pomegranate> pomegranates = new ArrayList<>();
            int nPomegranates = new Random().nextInt(11) + 10;
            for (int j = 0; j < nPomegranates; j++) {
                Pomegranate pomegranate = new Pomegranate();
                List<Seed> seeds = new ArrayList<>();
                int nSeeds = new Random().nextInt(301) + 400;
                for (int k = 0; k < nSeeds; k++) {
                    seeds.add(new Seed(2));
                }
                pomegranate.setSeeds(seeds);
                pomegranates.add(pomegranate);
            }
            box.setGranates(pomegranates);
            box.setName("Box " + i);
            boxes.add(box);
        }

        long seedCount = boxes.stream()
                .map(Box::getGranates)
                .flatMap(Collection::stream)
                .map(Pomegranate::getSeeds)
                .flatMap(Collection::stream)
                .count();
        System.out.println("seedCount = " + seedCount);

        long maxSeedsInBox = boxes.stream()
                .map(box ->
                        box.getGranates().stream()
                                .map(Pomegranate::getSeeds)
                                .flatMap(Collection::stream)
                                .count()
                ).reduce(0L, (acc, s) -> acc > s ? acc : s);
        System.out.println("maxSeedsInBox = " + maxSeedsInBox);

        boxes.stream()
                .filter(box ->
                        box.getGranates().stream()
                                .map(Pomegranate::getSeeds)
                                .flatMap(Collection::stream)
                                .count() == maxSeedsInBox
                )
                .map(Box::getName)
                .forEach(System.out::println);
    }
}
