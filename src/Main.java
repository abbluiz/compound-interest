import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        Scanner input = new Scanner(System.in); // This will gather all the information from the user
        double principal, future, rate, term, compoundFrequency;
        String mode, rateUnit, termUnit, compoundFrequencyUnit;

        System.out.println("Compound interest calculator\n");

        System.out.println("Do you wish to calculate future or present value?");
        mode = input.next();

        if (mode.equals("future")) { // If user chooses to calculate future value

            System.out.println("\nProvide principal amount: ");
            principal = input.nextDouble();

            System.out.println("\nProvide rate in percent followed by rate frequency (per day, per month or per annum): ");
            rate = input.nextDouble();
            input.next();
            rateUnit = input.next();

            System.out.println("\nProvide the compound frequency and its unit (days, months or years): every...");
            compoundFrequency = input.nextDouble();
            compoundFrequencyUnit = input.next();

            System.out.println("\nProvide length of time the interest is applied for, followed by unit (days, months or years): ");
            term = input.nextDouble();
            termUnit = input.next();

            // Create CompoundInterest object
            CompoundInterest interest = new CompoundInterest (principal,
                    rate, rateUnit,
                    compoundFrequency, compoundFrequencyUnit,
                    term, termUnit);
            // Print the object
            System.out.println("\n" + interest);
            System.out.println("\nFuture value = $" + CompoundInterestUtilities.round(interest.calculateFutureValue()));

        } else if (mode.equals("present")) { // If user chooses to calculate present value

            System.out.println("\nProvide future value: ");
            future = input.nextDouble();

            System.out.println("\nProvide rate in percent followed by rate frequency (per day, per month or per annum): ");
            rate = input.nextDouble();
            input.next();
            rateUnit = input.next();

            System.out.println("\nProvide the compound frequency and its unit (days, months or years): every...");
            compoundFrequency = input.nextDouble();
            compoundFrequencyUnit = input.next();

            System.out.println("\nProvide length of time the interest is applied for, followed by unit (days, months or years): ");
            term = input.nextDouble();
            termUnit = input.next();

            // Create CompoundInterest object
            CompoundInterest interest = new CompoundInterest (0.0,
                    rate, rateUnit,
                    compoundFrequency, compoundFrequencyUnit,
                    term, termUnit, future);
            System.out.println("\n" + interest);
            System.out.println("\nPresent value = $" + CompoundInterestUtilities.round(interest.getPrincipal()));

        }


    }

}
