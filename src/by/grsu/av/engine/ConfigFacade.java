package by.grsu.av.engine;

public class ConfigFacade {

    private static String[] goods = new String[]{"Grain", "Potato"};
    private static int jokerNumber = 7;

    public static int getInitialMoney() {
        return 100; //TODO
    }

    public static String[] getProductTypes() {
        return goods;
    }

    public static int getGoodNumber() {
        return goods.length;
    }

    public static int getJokerNumber() {
        return jokerNumber;
    }

    public static void setJokerNumber(int number) {
        jokerNumber = number;
    }

}
