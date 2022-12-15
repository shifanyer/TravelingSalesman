import java.util.*;
import java.util.stream.Collectors;

public class Gen {
    private static final int MUTATE_PROBABILITY = 1;
    private static final int PROBABILITY_100 = 100;
    private final String gen;
    private final List<Integer> genList;
    private final Integer genSize;

    public Gen(String gen) {
        this.gen = gen;
        this.genList = Arrays.stream(gen.split("\\B"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        this.genSize = gen.length();
    }

    public Integer getGenSize() {
        return genSize;
    }

    public String getGen() {
        return gen;
    }

    public List<Integer> getGenList() {
        return genList;
    }

    public List<Gen> getChildren(Gen gen) {
        Random rand = new Random();

        int splitPoint1 = rand.nextInt(this.genSize - 1);
        int splitPoint2 = splitPoint1 + 2 + rand.nextInt(this.genSize - 1 - splitPoint1);

        List<Gen> childrenList = new ArrayList<>();

        childrenList.add(getChild(this, gen, splitPoint1, splitPoint2));
        childrenList.add(getChild(gen, this, splitPoint1, splitPoint2));

        return childrenList;
    }

    private static Gen getChild(Gen mother, Gen father, int splitPoint1, int splitPoint2) {
        int genSize = mother.genSize;
        List<Integer> child = new ArrayList<>();
        for (int i = 0; i < genSize; i++) {
            child.add(-1);
        }

        Set<Integer> usedNums = new HashSet<>();

        for (int i = splitPoint1; i < splitPoint2; i++) {
            child.set(i, father.genList.get(i));
            usedNums.add(father.genList.get(i));
        }

        int fillPoint = (splitPoint1 + 1) % genSize;
        int currentEmptyPoint = child.indexOf(-1);
        while (child.contains(-1)) {
            int num = mother.genList.get(fillPoint);
            if (usedNums.contains(num)) {
                fillPoint = (fillPoint + 1) % genSize;
            } else {
                child.set(currentEmptyPoint, num);
                usedNums.add(num);
                currentEmptyPoint = child.indexOf(-1);
            }
        }

        Random rand = new Random();
        if (rand.nextInt(PROBABILITY_100) < MUTATE_PROBABILITY) {
            int firstLetter = rand.nextInt(genSize);
            int secondLetter = rand.nextInt(genSize);
            int letter = child.get(firstLetter);
            child.set(firstLetter, child.get(secondLetter));
            child.set(secondLetter, letter);
        }

        String stringGen = String.join("", child.stream().map(Object::toString).toList());

        return new Gen(stringGen);

    }

}
