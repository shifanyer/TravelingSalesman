import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Population {

    private final int[][] matrix;
    private final List<Gen> gens;
    private List<Pair<Gen, Integer>> gensWithLen;

    public Population(int[][] matrix, List<Gen> gens) {
        this.matrix = matrix;
        this.gens = gens;
        this.gensWithLen = gens.stream().map(e -> new Pair<Gen, Integer>(e, calculatePathLength(e))).collect(Collectors.toList());
    }

    public void printGens() {
        System.out.println(gens);
    }

    private Integer calculatePathLength(Gen gen) {
        int res = 0;
        for (int i = 0; i < gen.getGenSize(); i++) {
            int a = gen.getGenList().get(i) - 1;
            int b = gen.getGenList().get((i + 1) % gen.getGenSize()) - 1;
            res += matrix[a][b];
        }
        return res;
    }

    public Pair<Gen, Integer> getOptima() {

        for (int i = 0; i < 10; i++) {
            Collections.sort(gensWithLen, new PairComparator());

            Gen parent1 = gensWithLen.get(0).getA();
            Gen parent2 = gensWithLen.get(1).getA();
            Gen parent3 = gensWithLen.get(2).getA();
            Gen parent4 = gensWithLen.get(3).getA();

            List<Gen> childrenList1 = parent1.getChildren(parent3);
            List<Gen> childrenList2 = parent2.getChildren(parent4);

            Gen child1 = childrenList1.get(0);
            Gen child2 = childrenList1.get(1);
            Gen child3 = childrenList2.get(0);
            Gen child4 = childrenList2.get(1);

            if (gensWithLen.size() > 4) {
                gensWithLen.set(4, new Pair<>(child1, calculatePathLength(child1)));
                gensWithLen.set(5, new Pair<>(child2, calculatePathLength(child2)));
                gensWithLen.set(6, new Pair<>(child3, calculatePathLength(child3)));
                gensWithLen.set(7, new Pair<>(child4, calculatePathLength(child4)));
            }
            else {
                gensWithLen.add(new Pair<>(child1, calculatePathLength(child1)));
                gensWithLen.add(new Pair<>(child2, calculatePathLength(child2)));
                gensWithLen.add(new Pair<>(child3, calculatePathLength(child3)));
                gensWithLen.add(new Pair<>(child4, calculatePathLength(child4)));
            }
        }
        Collections.sort(gensWithLen, new PairComparator());
        return gensWithLen.get(0);
    }

}
