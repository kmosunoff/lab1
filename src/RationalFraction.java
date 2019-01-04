import java.util.Objects;

public class RationalFraction {
    private int numerator, denominator;

    public RationalFraction() {
        this(0,1);
    }
    public RationalFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.reduce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RationalFraction that = (RationalFraction) o;
        return numerator == that.numerator &&
                denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }

    private int gcd(int numerator, int denominator) {
        while (numerator != 0 && denominator != 0) {
            if (numerator > denominator) {
                numerator %= denominator;
            }
            else {
                denominator %= numerator;
            }
        }
        return numerator + denominator;
    }
    public void reduce() {
        int gcd = this.gcd(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }
    /**public void setNumerator(int numerator) {
        this.numerator = numerator;
        this.reduce();
    }
    public void setDenominator(int denominator) {
        this.denominator = denominator;
        this.reduce();
    }**/
    public RationalFraction plus(RationalFraction another) {
        int commonDenominator = this.denominator * another.denominator;
        int newNumerator = this.numerator * another.denominator + another.numerator * this.denominator;
        RationalFraction sum = new RationalFraction(newNumerator, commonDenominator);
        return sum;
    }
    public RationalFraction multiplyWith(RationalFraction another) {
        int newNumerator = this.numerator * another.numerator;
        int newDenominator = this.denominator * another.denominator;
        RationalFraction product = new RationalFraction(newNumerator, newDenominator);
        return product;
    }
    public RationalFraction minus(RationalFraction another) {
        return this.plus(another.multiplyWith(new RationalFraction(-1, 1)));
    }
    public RationalFraction divideBy(RationalFraction another) {
        if (another.equals(new RationalFraction(0,1))) {
            return null;
        }
        return this.multiplyWith(new RationalFraction(another.denominator, another.numerator));
    }
    public boolean lessThan(RationalFraction another) {
        return this.numerator * another.denominator < this.denominator * another.numerator;
    }
    public boolean greaterThan(RationalFraction another) {
        return this.numerator * another.denominator > this.denominator * another.numerator;
    }
}
