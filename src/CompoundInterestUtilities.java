import java.math.BigDecimal;
import java.math.RoundingMode;

class CompoundInterestUtilities {

    // Round numbers to 2 decimal values
    static double round(double value) {

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    static String interestRateTerminology(String rateUnit) {

        switch(rateUnit) {

            case "days":
                return "per day";
            case "months":
                return "per month";
            case "years":
                return "per annum";
            default:
                return "n/a";

        }

    }

}
