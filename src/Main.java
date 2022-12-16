import java.util.*;

public class Main {

    private static final int POPULATION_SIZE = 4;
    private static final int GEN_LENGTH = 5;
    private static final int[][] MATRIX = {
            {0, 4, 6, 2, 9},
            {4, 0, 3, 2, 9},
            {6, 3, 0, 5, 9},
            {2, 2, 5, 0, 8},
            {9, 9, 9, 8, 0},
    };

    public static void main(String[] args) {

        Population population = new Population(MATRIX, genPopulation(POPULATION_SIZE, GEN_LENGTH));

        Pair<Gen, Integer> optimaPath = population.getOptima();
        System.out.println(optimaPath.getA().getGen());
        System.out.println(optimaPath.getB());
        System.out.println();

    }

    private static String genGen(int genLength) {

        Set<Integer> creatures = new HashSet<>();
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= genLength; i++) {
            nums.add(i);
        }

        Random rand = new Random();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt(genLength);
            int num = nums.get(x);
            while (creatures.contains(num)) {
                x = (x + 1) % genLength;
                num = nums.get(x);
            }
            creatures.add(num);
            res.append(num);
        }
        return res.toString();
    }

    private static List<Gen> genPopulation(int popSize, int genLength) {

        Set<String> population = new HashSet<>();
        while (population.size() < popSize) {
            String newGen = genGen(genLength);
            population.add(newGen);
        }

        return population.stream().map(Gen::new).toList();
    }

}