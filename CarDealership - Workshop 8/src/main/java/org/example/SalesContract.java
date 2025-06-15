package org.example;

public class SalesContract extends Contract{
    private double taxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean wantsFinance;
    private double monthlyPayment;

    public SalesContract(String date, String name, String email , Vehicle vehicle, boolean wantsFinance) {
        super(date, name, email ,vehicle);
        this.taxAmount = .05 * vehicle.getPrice();
        this.recordingFee = 100;
        if(vehicle.getPrice() < 10000) {
            this.processingFee = 295;
        }
        else{
            this.processingFee = 495;
        }
        this.wantsFinance = wantsFinance;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean wantsFinance() {
        return wantsFinance;
    }

    public void setWantsFinance(boolean wantsFinance) {
        this.wantsFinance = wantsFinance;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + getTaxAmount() + getProcessingFee() + getRecordingFee();
    }

    @Override
    public double getMonthlyPayment() {
        if (wantsFinance) {
            double perMonthInterestRate;
            int months;
            if (getVehicle().getPrice() >= 10000) {
                perMonthInterestRate = (4.25 / 12) / 100;
                months = 48;
            } else {
                perMonthInterestRate = (5.25 / 12) / 100;
                months = 24;
            }
            monthlyPayment = getTotalPrice() * (Math.abs(perMonthInterestRate * Math.pow(1 + perMonthInterestRate, months))
                    / Math.abs(Math.pow(1 + perMonthInterestRate, months) - 1));
            return monthlyPayment;
        } else {
            return 0;
        }
    }
}
