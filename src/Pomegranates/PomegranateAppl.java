package Pomegranates;

import java.util.*;
import java.util.stream.Collectors;

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

        Map<Integer, List<Box>> countedBoxes = boxes.stream()
                .collect(Collectors.groupingBy(box ->
                                (int) ((Box) box).getGranates().stream()
                                        .map(Pomegranate::getSeeds)
                                        .flatMap(Collection::stream)
                                        .count()
                        )
                );
        int maxSeedsInBox = Collections.max(countedBoxes.keySet());
        System.out.println("maxSeedsInBox = " + maxSeedsInBox);
        countedBoxes.get(maxSeedsInBox).forEach(box-> System.out.println(box.getName()));
    }
}
