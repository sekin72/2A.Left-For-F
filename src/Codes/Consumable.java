public abstract class Consumable extends Item {
    int increaseAmount;
    int maximumHealthChangeAmount;
    public abstract boolean isEnergyDrink();
    public abstract boolean isCoffee();
    public abstract boolean isFood();
    String name;



}
