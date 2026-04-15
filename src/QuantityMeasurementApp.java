public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double getConversionFactorToFeet() {
            return conversionFactorToFeet;
        }
    }

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double convertToFeet() {
            return this.value * unit.getConversionFactorToFeet();
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.convertToFeet(), other.convertToFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(convertToFeet());
        }
    }

    public static boolean compare(double value1, LengthUnit unit1,
                                  double value2, LengthUnit unit2) {

        QuantityLength q1 = new QuantityLength(value1, unit1);
        QuantityLength q2 = new QuantityLength(value2, unit2);

        return q1.equals(q2);
    }

    public static void main(String[] args) {

        System.out.println("Input: Quantity(1.0, FEET) and Quantity(12.0, INCH)");
        System.out.println("Output: Equal (" +
                compare(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH) + ")");

        System.out.println("Input: Quantity(1.0, INCH) and Quantity(1.0, INCH)");
        System.out.println("Output: Equal (" +
                compare(1.0, LengthUnit.INCH, 1.0, LengthUnit.INCH) + ")");
    }
}