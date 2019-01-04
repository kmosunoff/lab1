import java.util.stream.IntStream;

public class Polynom {
    private FractionsSet data;

    public Polynom() {
        data = new FractionsSet();
    }

    public Polynom(FractionsSet data) {
        this.data = new FractionsSet();
        for (int i = 0; i < data.size(); ++i) {
            this.data.add(data.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder("");
        for (int i = 0; i < size(); ++i) {
            answer.append(getCoefficient(i).toString() + "*X^" + Integer.toString(i) + " + ");
        }
        return answer.toString().substring(0, answer.length() - 3);
    }

    public int size() {
        return data.size();
    }

    public void addCoefficient (RationalFraction another) {
        data.add(another);
    }

    public RationalFraction getCoefficient(int i) {
        return i < data.size()
                ? data.get(i)
                : new RationalFraction();
    }

    public Polynom plus(Polynom another) {
        Polynom sum = new Polynom();
        IntStream.range(0,  Math.max(this.size(), another.size()))
                .forEach(i -> sum.addCoefficient(this.getCoefficient(i).plus(another.getCoefficient(i))));
        return sum;
    }
}
