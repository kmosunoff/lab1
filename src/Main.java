public class Main {
    public static void main(String[] args) {
        FractionsSet S = new FractionsSet();
        S.readFractions("input.txt");
        FractionsSet S2 = new FractionsSet();
        S2.readFractions("input2.txt");
        System.out.println(S.getMin());
        System.out.println(S.getMax());
        System.out.println(S.countGreaterThan(new RationalFraction(3,4)));
        System.out.println(S.countLessThan(new RationalFraction(2, 3)));
        Polynom P = new Polynom(S);
        Polynom P2 = new Polynom(S2);
        System.out.println(P.getCoefficient(0).toString());
        System.out.println(P);
        System.out.println(P2.plus(P));
    }
}
