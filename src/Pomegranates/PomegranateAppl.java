package Pomegranates;

import java.util.*;
import java.util.stream.Collectors;

public class PomegranateAppl {
    public static void main(String[] args) {
        int nBoxes = new Random().nextInt(101) + 100;
        System.out.println("nBoxes = " + nBoxes);
        List<Box> boxes = new ArrayList<>();
        new Random()
                .ints(10, 21)
                .limit(nBoxes)
                .forEach(nPomegranatesInTheBox ->{
                    Box box = new Box();
                    List<Pomegranate> pomegranates = new ArrayList<>();
                    new Random()
                        .ints(400, 701)
                        .limit(nPomegranatesInTheBox)
                        .forEach(nSeedsInThePomegranate ->{
                            Pomegranate pomegranate = new Pomegranate();
                            List<Seed> seeds = new ArrayList<>();
                            for (int k = 0; k < nSeedsInThePomegranate; k++) {
                                seeds.add(new Seed(2));
                            }
                            pomegranate.setSeeds(seeds);
                            pomegranates.add(pomegranate);
                        });
                    box.setGranates(pomegranates);
                    box.setName("Box " + (boxes.size() +1) + " : " + pomegranates.size() + " pomegranates");
                    boxes.add(box);
                });

        long seedCount = boxes.stream()
                .mapToInt(box -> seedCount(box))
                .sum();
        System.out.println("seedCount = " + seedCount);

        Map<Integer, List<Box>> countedBoxes = boxes.stream()
                .collect(Collectors.groupingBy(box -> seedCount((Box) box)));
        int maxSeedsInBox = Collections.max(countedBoxes.keySet());
        System.out.println("maxSeedsInBox = " + maxSeedsInBox);
        countedBoxes.get(maxSeedsInBox).forEach(box-> System.out.println(box.getName()));
    }

    private static int seedCount(Box box){
        return (int) box.getGranates().stream()
                .map(Pomegranate::getSeeds)
                .flatMap(Collection::stream)
                .count();
    }
}
