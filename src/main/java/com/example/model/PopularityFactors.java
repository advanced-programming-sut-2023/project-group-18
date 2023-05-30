package com.example.model;

public class PopularityFactors {
    private final Governance governance;
    private int popularity;
    private int foodFactor;
    private int foodRate;
    private double foodCoefficient;
    private int taxFactor;
    private int taxRate;
    private double taxCoefficient;
    private int religiousFactor;
    private int fearFactor;
    private int fearRate;
    private int fearBuildings;
    private double fearCoefficient;
    private int aleFactor;
    private double aleCoverage;

    protected PopularityFactors(Governance governance) {
        this.governance = governance;
        setFoodRate(0);
        setTaxRate(0);
        religiousFactor = 0;
        setFearRate(0);
        setAleCoverage(0);
    }

    public int getPopularity() {
        popularity = foodFactor + taxFactor + religiousFactor + fearFactor + aleFactor;
        return popularity;
    }


    public int getFoodRate() {
        return foodRate;
    }

    public double getFoodCoefficient() {
        return foodCoefficient;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
        foodFactor = foodRate * 4 + governance.getKindsOfFoods();
        foodCoefficient = (foodRate + 2) / 2;
    }

    public boolean canSetFoodRate(int foodRate) {
        final int currentFoodRate = this.foodRate;
        setFoodRate(foodRate);
        boolean result;
        result = governance.getFoodCount() >= governance.getNonMilitaryCharacters() * foodCoefficient;
        setFoodRate(currentFoodRate);
        return result;
    }


    public int getTaxRate() {
        return taxRate;
    }

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
        taxFactor = taxRate <= 0 ? 1 - 2 * taxRate : (taxRate <= 4 ? -2 * taxRate : 8 - 4 * taxRate);
        taxCoefficient = taxRate == 0 ? 0 : taxRate * 0.2 + 0.4 * (taxRate < 0 ? -1 : 1);
    }

    public boolean canSetTaxRate(int taxRate) {
        final int currentTaxRate = this.taxRate;
        setTaxRate(taxRate);
        boolean result;
        result = governance.getGold() - governance.getNonMilitaryCharacters() * taxCoefficient >= 0;
        setTaxRate(currentTaxRate);
        return result;
    }


    public int getReligiousFactor() {
        return religiousFactor;
    }

    public void addReligiousFactor(int religious) {
        this.religiousFactor += religious;
    }


    public int getFearRate() {
        return fearRate;
    }

    public int getFearBuildings() {
        return fearBuildings;
    }

    public double getFearCoefficient() {
        return fearCoefficient;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
        fearCoefficient = 0.05 * fearRate;
        fearFactor = fearRate + fearBuildings;
    }

    public void addFearBuildings(int fearBuildings) {
        this.fearBuildings += fearBuildings;
        fearFactor += fearBuildings;
    }


    public double getAleCoverage() {
        if (aleCoverage < 0)
            return 0;
        if (aleCoverage > 1)
            return 1;
        return aleCoverage;
    }

    public int getAleFactor() {
        return aleFactor;
    }

    public void setAleCoverage(double aleCoverage) {
        this.aleCoverage = aleCoverage;
        aleFactor = (int) (aleCoverage * 8);
    }

    public void addAleCoverage(double aleCoverage){
        setAleCoverage(this.aleCoverage + aleCoverage);
    }

}
