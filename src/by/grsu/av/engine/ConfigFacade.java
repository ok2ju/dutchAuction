package by.grsu.av.engine;

public class ConfigFacade {

    private static String[] goods = new String[]{"Grain", "Potato"};

    public static int getInitialMoney() {
        return 100; //TODO
    }

    public static String[] getProductTypes() {
        return goods;
    }

    public static int getGoodNumber() {
        return goods.length;
    }
}
