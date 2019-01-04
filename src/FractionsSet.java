import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FractionsSet {

    private class Pair {
        public int greater, less;

        public Pair() {
            greater = 0;
            less = 0;
        }

        public Pair(int _greater, int _less) {
            greater = _greater;
            less = _less;
        }
    }

    private List<RationalFraction> data = new ArrayList<>();
    private RationalFraction max, min;
    private HashMap<RationalFraction, Pair> queries;


    public FractionsSet() {
        max = null;
        min = null;
        queries = new HashMap<>();
    }

    public FractionsSet(ArrayList<RationalFraction> data) {
        this.data = List.copyOf(data);
        queries = new HashMap<>();
    }

    public int size() {
        return data.size();
    }

    public RationalFraction get(int i) {
        return data.get(i);
    }

    public void add(RationalFraction another) {
        data.add(another);
        if (max == null || another.greaterThan(max)) {
            max = another;
        }
        if (min == null || another.lessThan(min)) {
            min = another;
        }
        for (RationalFraction rf: queries.keySet()) {
            if (another.greaterThan(rf)) {
                ++queries.get(rf).greater;
            }
            if (another.lessThan(rf)) {
                ++queries.get(rf).less;
            }
        }
    }

    public RationalFraction getMax() {
        if (!data.isEmpty()) {
            if (max != null) {
                return max;
            }
            max = data.get(0);
            for (int i = 1; i < data.size(); ++i) {
                if (data.get(i).greaterThan(max)) {
                    max = data.get(i);
                }
            }
        }
        else {
            max = null;
        }
        return max;
    }

    public RationalFraction getMin() {
        if (!data.isEmpty()) {
            if (min != null) {
                return min;
            }
            min = data.get(0);
            for (int i = 1; i < data.size(); ++i) {
                if (data.get(i).lessThan(min)) {
                    min = data.get(i);
                }
            }
        }
        else {
            min = null;
        }
        return min;
    }

    public void refreshAnswerFor(RationalFraction another) {
        int greater = 0;
        int less = 0;
        for (RationalFraction rf: data) {
            if (rf.greaterThan(another)) {
                ++greater;
            }
            if (rf.lessThan(another)) {
                ++less;
            }
        }
        queries.put(another, new Pair(greater, less));
    }

    public int countGreaterThan(RationalFraction another) {
        if (!queries.containsKey(another)) {
            refreshAnswerFor(another);
        }
        return queries.get(another).greater;
    }

    public int countLessThan(RationalFraction another) {
        if (!queries.containsKey(another)) {
            refreshAnswerFor(another);
        }
        return queries.get(another).less;
    }

    public void readFractions(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                int numerator, denominator;
                numerator = Integer.parseInt(line.substring(0, line.indexOf("/")));
                denominator = Integer.parseInt(line.substring(line.indexOf("/") + 1));
                this.add(new RationalFraction(numerator, denominator));
            }
            reader.close();
        }
        catch (IOException e) {
            return;
        }
    }

}
