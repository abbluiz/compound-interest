import java.math.BigDecimal;
import java.math.RoundingMode;

public class CompoundInterestUtilities {

    // Round numbers
    public static double round(double value, int decimalPlaces) {

        if (decimalPlaces < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public static String interestRateTerminology(String rateUnit) {

        switch(rateUnit) {

            case "days":
                return "per day";
            case "months":
                return "per month";
            case "years":
                return "per year";
            default:
                return "n/a";

        }

    }

}
