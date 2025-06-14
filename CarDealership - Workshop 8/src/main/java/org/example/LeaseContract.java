package org.example;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double monthlyPayment;
    private double leaseFee;

    public LeaseContract(String date, String name,String email , Vehicle vehicle) {
        super(date, name, email ,vehicle);
        this.expectedEndingValue = vehicle.getPrice() * .50;
        this.leaseFee = vehicle.getPrice() * 0.07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return getMonthlyPayment() * 36 + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double depreciation = getVehicle().getPrice() - expectedEndingValue;
        double moneyFactor = 0.04 / 24;
        monthlyPayment = (depreciation / 36) + ((getVehicle().getPrice() + expectedEndingValue) * moneyFactor);
        return monthlyPayment;
    }
}
