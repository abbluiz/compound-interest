public class CompoundInterest {

    private double principal;
    private double rate;
    private String rateUnit;
    private double compoundingFrequency;
    private String compoundingFrequencyUnit;
    private double term;
    private String termUnit;

    // This constants specify the conversion between time units
    private static final double DAYS_IN_A_MONTH = 30.0;
    private static final double DAYS_IN_A_YEAR = 365.0;
    private static final double MONTHS_IN_A_YEAR = 12.0;

    // Constructor if user chooses to calculate future value
    CompoundInterest(double principal, double rate, String rateUnit,
                     double compoundingFrequency, String compoundingFrequencyUnit,
                     double term, String termUnit) {

        this.principal = principal;
        this.rate = rate;
        this.rateUnit = rateUnit;
        this.compoundingFrequency = compoundingFrequency;
        this.compoundingFrequencyUnit = compoundingFrequencyUnit;
        this.term = term;
        this.termUnit = termUnit;

        this.setRateUnit(rateUnit); // Use setter to validate some values

    }

    // Constructor if user chooses to calculate present value
    CompoundInterest(double principal, double rate, String rateUnit,
                     double compoundingFrequency, String compoundingFrequencyUnit,
                     double term, String termUnit, double futureValue) {

        this.principal = principal;
        this.rate = rate;
        this.rateUnit = rateUnit;
        this.compoundingFrequency = compoundingFrequency;
        this.compoundingFrequencyUnit = compoundingFrequencyUnit;
        this.term = term;
        this.termUnit = termUnit;

        this.setRateUnit(rateUnit); // Use setter to validate some values
        this.setPrincipal(this.calculatePresentValue(futureValue));

    }

    double getPrincipal() { return this.principal; }

    private void setPrincipal(double principal) { this.principal = principal; }

    private void setRateUnit(String rateType) {

        /* Change "daily" to "days", "monthly" to "months, and "yearly" to "years"
         * This will make it easy to convert units */
        switch (rateType) {
            case "day":
                this.rateUnit = "days";
                break;
            case "month":
                this.rateUnit = "months";
                break;
            case "annum":
                this.rateUnit = "years";
                break;
        }

    }

    // Converts a unit to whatever the rate unit is
    private double convertTimeToRateUnit(String unit, double value) {

        if (this.rateUnit.equals(unit)) // Checks if the units are already compatible; if so returns the result as is
            return value;
        else if (this.rateUnit.equals("days") && unit.equals("months"))
            return (value * DAYS_IN_A_MONTH); // Return conversion from months to days
        else if (this.rateUnit.equals("days") && unit.equals("years"))
            return (value * DAYS_IN_A_YEAR); // Return conversion from years to days
        else if (this.rateUnit.equals("months") && unit.equals("days"))
            return (value / DAYS_IN_A_MONTH); // Return conversion from days to months
        else if (this.rateUnit.equals("months") && unit.equals("years"))
            return (value * MONTHS_IN_A_YEAR); // Return conversion from years to months
        else if (this.rateUnit.equals("years") && unit.equals("days"))
            return (value / DAYS_IN_A_YEAR); // Return conversion from days to years
        else if (this.rateUnit.equals("years") && unit.equals("months"))
            return (value / MONTHS_IN_A_YEAR); // Return conversion from months to years

        return 0.0;
    }

    // Converts a unit to whatever the compounding frequency unit is
    private double convertTimeToCompoundingFrequencyUnit(String unit, double value) {

        if (this.compoundingFrequencyUnit.equals(unit)) // Checks if the units are already compatible; if so returns the result as is
            return value;
        else if (this.compoundingFrequencyUnit.equals("days") && unit.equals("months"))
            return (value * DAYS_IN_A_MONTH); // Return conversion from months to days
        else if (this.compoundingFrequencyUnit.equals("days") && unit.equals("years"))
            return (value * DAYS_IN_A_YEAR); // Return conversion from years to days
        else if (this.compoundingFrequencyUnit.equals("months") && unit.equals("days"))
            return (value / DAYS_IN_A_MONTH); // Return conversion from days to months
        else if (this.compoundingFrequencyUnit.equals("months") && unit.equals("years"))
            return (value * MONTHS_IN_A_YEAR); // Return conversion from years to months
        else if (this.compoundingFrequencyUnit.equals("years") && unit.equals("days"))
            return (value / DAYS_IN_A_YEAR); // Return conversion from days to years
        else if (this.compoundingFrequencyUnit.equals("years") && unit.equals("months"))
            return (value / MONTHS_IN_A_YEAR); // Return conversion from months to years

        return 0.0;

    }

    // i = j / m
    private double calculatePeriodicRate() { return (this.rate / this.calculateCompoundingPeriodsPerRateUnit()); }

    // m = (number of compounding frequency units there is in a rate unit) / compounding frequency
    private double calculateCompoundingPeriodsPerRateUnit() {

        return (this.convertTimeToCompoundingFrequencyUnit(this.rateUnit, 1) / this.compoundingFrequency);

    }

    // n = term * m
    private double calculateCompoundingPeriodsInTerm() {

        return (this.convertTimeToRateUnit(this.termUnit, this.term) * this.calculateCompoundingPeriodsPerRateUnit());

    }

    double calculateFutureValue() {

        double periodicRate = this.calculatePeriodicRate() / 100.0; // i
        double compoundingPeriodsInTerm = this.calculateCompoundingPeriodsInTerm(); // n

        return (this.principal * Math.pow((1 + periodicRate), compoundingPeriodsInTerm)); // F = P (1 + i)^n

    }

    private double calculatePresentValue(double futureValue) {

        double periodicRate = this.calculatePeriodicRate() / 100.0; // i
        double compoundingPeriodsInTerm = this.calculateCompoundingPeriodsInTerm(); // n

        return (futureValue / Math.pow((1 + periodicRate), compoundingPeriodsInTerm)); // P = F / (1 + i)^n

    }

    @Override
    public String toString() {

        return "Principal: $" + CompoundInterestUtilities.round(this.principal) + "\n" +
                "Interest rate: " + this.rate + "% " + CompoundInterestUtilities.interestRateTerminology(this.rateUnit) + "\n" +
                "Compounded every " + CompoundInterestUtilities.round(this.compoundingFrequency) + " " + this.compoundingFrequencyUnit + "\n" +
                "Term: " + CompoundInterestUtilities.round(this.term) + " " + this.termUnit;

    }

}
