package ru.maks.anonym;

/**
 * Created by mstukolov on 21.08.2018.
 */
public class NetworkListener implements IListener {
    @Override
    public Boolean call(Object obj) {
        System.out.println("NetworkListener is triggered");
        return true;
    }
}
