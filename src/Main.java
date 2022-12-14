import java.util.*;

public class Main {
    public static void main(String[] args) {

        int[][] matrix = {
                {0, 4, 6, 2, 9},
                {4, 0, 3, 2, 9},
                {6, 3, 0, 5, 9},
                {2, 2, 5, 0, 8},
                {9, 9, 9, 8, 0},
        };

        int populationSize = 4;
        int genLength = 5;

        Population population = new Population(matrix, genPopulation(populationSize, genLength));

        Gen gen1 = new Gen("12345");
        Gen gen2 = new Gen("54231");

//        gen1.getChildren(gen2);

        System.out.println(population.getOptima().getA().getGen());
        System.out.println(population.getOptima().getB());

//        population.printGens();

    }

    private static String to5Symbols(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        while (sBuilder.length() < 5) {
            sBuilder.insert(0, "0");
        }
        s = sBuilder.toString();
        return s;
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