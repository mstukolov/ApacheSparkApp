package ru.maks.anonym;

/**
 * Created by mstukolov on 21.08.2018.
 */
public class WifiListener implements IListener {
    @Override
    public Boolean call(Object obj) {
        System.out.println("WifiListener: is triggered");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("WifiListener: WIFI success");
        return null;
    }
}
