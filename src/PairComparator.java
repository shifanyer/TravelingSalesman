import java.util.Comparator;

class PairComparator implements Comparator<Pair<Gen, Integer>> {
    @Override
    public int compare(Pair<Gen, Integer> o1, Pair<Gen, Integer> o2) {
        return o1.getB() < o2.getB() ? -1 : 1;
    }
}
