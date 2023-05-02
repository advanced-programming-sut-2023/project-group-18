package com.example.model;

public class PopularityFactors {
    private final Governance governance;
    private int popularity;
    private int food;
    private int foodRateInput;
    private double foodRate;
    private int tax;
    private int taxRateInput;
    private double taxRate;
    private int religious;
    private int fear;
    private int fearRate;
    private int fearBuilding;
    private int ale;
    private int aleCoverage;

    public PopularityFactors(Governance governance) {
        this.governance = governance;
    }

    public int getPopularity() {
        calculatePopularity();
        return popularity;
    }

    public void setFoodRateInput(int foodRateInput) {
        this.foodRateInput = foodRateInput;
        foodRate = (foodRateInput + 2) / 2;
        food = 4 * foodRateInput;
    }

    public double getFoodRate() {
        return foodRate;
    }

    public int getFoodRateInput() {
        return foodRateInput;
    }

    public void setTaxRateInput(int taxRateInput) {
        this.taxRateInput = taxRateInput;
        int sign = Integer.signum(taxRateInput);
        if (sign == 0) taxRate = 0;
        else taxRate = 0.4 * sign + 0.2 * taxRateInput;
        // if (taxRateInput <= 0) TODO: must complete
    }

    private void calculatePopularity() {
        popularity = food + tax + religious + fear + ale;
    }

    /* TODO: must change

    public String showFoodList() {
        String result = "";
        for (Asset food : assets.get(AssetType.FOOD).keySet()) {
            if (assets.get(AssetType.FOOD).get(food).equals(0)) continue;
            result += "\n" + food.getName() + ": " + assets.get(AssetType.FOOD).get(food);
        }
        if (result.equals(null)) return "You have No food Yet!";
        return result.substring(1);
    }

    public boolean haveEnoughFood() {
        return nonMilitaryCharacters * foodRate <= getAllFoodCount();
    }

    private int getAllFoodCount() {
        int result = 0;
        for (Asset food : assets.get(AssetType.FOOD).keySet())
            result += assets.get(AssetType.FOOD).get(food);
        return result;
    }


    private double calculateTaxRate(int taxRate) {
        int sign = Integer.signum(taxRate);
        return taxRate == 0 ? 0 : 0.4 * sign + 0.2 * taxRate;
    }

    public int getTaxRate() {
        if (taxRate == 0) return 0;
        int sign = taxRate > 0 ? 1 : -1;
        return (int) ((taxRate - 0.4 * sign) * 5);
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = calculateTaxRate(taxRate);
    }

    public boolean canTaxPeople(int taxRate) {
        return gold - calculateTaxRate(taxRate) * nonMilitaryCharacters > 0;
    }

*/
}
