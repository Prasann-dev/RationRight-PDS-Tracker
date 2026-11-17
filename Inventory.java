class Inventory {
    double riceKg;
    double wheatKg;
    double sugarKg;

    public Inventory(double riceKg, double wheatKg, double sugarKg) {
        this.riceKg = riceKg;
        this.wheatKg = wheatKg;
        this.sugarKg = sugarKg;
    }

    public void displayStock() {
        System.out.println("\n--- Current Shop Stock ---");
        System.out.println("Rice: " + riceKg + " kg");
        System.out.println("Wheat: " + wheatKg + " kg");
        System.out.println("Sugar: " + sugarKg + " kg");
    }
}
