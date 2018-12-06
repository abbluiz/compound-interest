public class CompoundInterest {

    private double principal;
    private double rate;
    private String rateType;
    private double compoundingFrequency;
    private String compoundingFrequencyUnit;
    private double term;
    private String termUnit;

    // This constants specify the conversion between time units used in simple interest calculations
    private static final double DAYS_IN_A_MONTH = 30.0;
    private static final double DAYS_IN_A_YEAR = 365.0;
    private static final double MONTHS_IN_A_YEAR = 12.0;

    public CompoundInterest () {}

    public CompoundInterest (double principal, double rate, String rateType,
                             double compoundingFrequency, String compoundingFrequencyUnit,
                             double term, String termUnit) {

        this.principal = principal;
        this.rate = rate;
        this.rateType = rateType;
        this.compoundingFrequency = compoundingFrequency;
        this.compoundingFrequencyUnit = compoundingFrequencyUnit;
        this.term = term;
        this.termUnit = termUnit;

        this.setRateType(rateType); // Use setter to validate some values

    }

    public CompoundInterest (double principal, double rate, String rateType,
                             double compoundingFrequency, String compoundingFrequencyUnit,
                             double term, String termUnit, double futureValue) {

        this.principal = principal;
        this.rate = rate;
        this.rateType = rateType;
        this.compoundingFrequency = compoundingFrequency;
        this.compoundingFrequencyUnit = compoundingFrequencyUnit;
        this.term = term;
        this.termUnit = termUnit;

        this.setRateType(rateType); // Use setter to validate some values
        this.setPrincipal(this.calculatePresentValue(futureValue));

    }

    public double getPrincipal() { return principal; }

    public double getRate() { return rate; }

    public String getRateType() { return rateType; }

    public double getCompoundingFrequency() { return compoundingFrequency; }

    public String getCompoundingFrequencyUnit() { return compoundingFrequencyUnit; }

    public double getTerm() { return term; }

    public String getTermUnit() { return termUnit; }

    public void setPrincipal(double principal) { this.principal = principal; }

    public void setRate(double rate) { this.rate = rate; }

    public void setRateType(String rateType) {

        /* Change "daily" to "days", "monthly" to "months, and "yearly" to "years"
         * This will make it easy to convert units later on */
        switch (rateType) {
            case "daily":
                this.rateType = "days";
                break;
            case "monthly":
                this.rateType = "months";
                break;
            case "yearly":
                this.rateType = "years";
                break;
        }

    }

    public void setCompoundingFrequency(double compoundingFrequency) { this.compoundingFrequency = compoundingFrequency; }

    public void setCompoundingFrequencyUnit(String compoundingFrequencyUnit) { this.compoundingFrequencyUnit = compoundingFrequencyUnit; }

    public void setTerm(double term) { this.term = term; }

    public void setTermUnit(String termUnit) { this.termUnit = termUnit; }

    public double convertTimeToRateUnit(String unit, double value) {

        if (this.rateType.equals(unit)) // Checks if the units are already compatible; if so returns the term as is
            return value;
        else if (this.rateType.equals("days") && unit.equals("months"))
            return (value * DAYS_IN_A_MONTH); // Return conversion from months to days
        else if (this.rateType.equals("days") && unit.equals("years"))
            return (value * DAYS_IN_A_YEAR); // Return conversion from years to days
        else if (this.rateType.equals("months") && unit.equals("days"))
            return (value / DAYS_IN_A_MONTH); // Return conversion from days to months
        else if (this.rateType.equals("months") && unit.equals("years"))
            return (value * MONTHS_IN_A_YEAR); // Return conversion from years to months
        else if (this.rateType.equals("years") && unit.equals("days"))
            return (value / DAYS_IN_A_YEAR); // Return conversion from days to years
        else if (this.rateType.equals("years") && unit.equals("months"))
            return (value / MONTHS_IN_A_YEAR); // Return conversion from months to years

        return 0.0;
    }

    public double convertTimeToCompoundingFrequencyUnit(String unit, double value) {

        if (this.compoundingFrequencyUnit.equals(unit)) // Checks if the units are already compatible; if so returns the term as is
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

    /* This method will convert the term unit to whatever rate type is being used and return the converted result.
     * EXAMPLE: if it is a monthly interest rate and the term unit is days, this method will return the original term
     * value divided by the number of days in a month. */
    private double convertTermToRateTypeUnit() {

        return this.convertTimeToRateUnit(this.termUnit, this.term);

    }

    public double calculatePeriodicRate() { // i

        return (this.rate / this.calculateCompoundingPeriodsPerRateUnit());

    }

    public double calculateCompoundingPeriodsPerRateUnit() { // m

        return (this.convertTimeToCompoundingFrequencyUnit(this.rateType, 1) / this.compoundingFrequency);

    }

    public double calculateCompoundingPeriodsInTerm() { // n

        return (this.convertTermToRateTypeUnit() * this.calculateCompoundingPeriodsPerRateUnit());

    }

    public double calculateFutureValue() {

        double periodicRate = this.calculatePeriodicRate() / 100.0;
        double compoundingPeriodsInTerm = this.calculateCompoundingPeriodsInTerm();

        return (this.principal * Math.pow((1 + periodicRate), compoundingPeriodsInTerm));

    }

    public double calculatePresentValue(double futureValue) {

        double periodicRate = this.calculatePeriodicRate() / 100.0;
        double compoundingPeriodsInTerm = this.calculateCompoundingPeriodsInTerm();

        return (futureValue / Math.pow((1 + periodicRate), compoundingPeriodsInTerm));

    }

    @Override
    public String toString() {

        return "Principal: $" + CompoundInterestUtilities.round(this.principal,2) + "\n" +
                "Interest rate: " + this.rate + "% " + CompoundInterestUtilities.interestRateTerminology(this.rateType) + "\n" +
                "Term: " + this.term + " " + this.termUnit;

    }

}
