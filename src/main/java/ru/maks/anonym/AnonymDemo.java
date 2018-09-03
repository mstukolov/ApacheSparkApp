package ru.maks.anonym;

/**
 * Created by mstukolov on 21.08.2018.
 */
public class AnonymDemo {
    public static void main(String[] args) {

        TVSet homeTV = new TVSet();
        homeTV.addListener(new AvailableChannelListener());
        homeTV.addListener(new NetworkListener());
        homeTV.addListener(new WifiListener());

        homeTV.setChannel(5);

        System.out.println("Main program is finished");

    }
}
