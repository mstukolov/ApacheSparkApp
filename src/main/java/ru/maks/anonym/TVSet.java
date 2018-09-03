package ru.maks.anonym;

import java.util.ArrayList;

/**
 * Created by mstukolov on 21.08.2018.
 */
public class TVSet {

    Integer currentChannel;
    ArrayList<IListener> listeners;

    public TVSet() {
        System.out.println("init TVSet....");
        listeners = new ArrayList<>();
        currentChannel = 0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("TVSet is started on: %d\n", this.currentChannel);
    }

    public void addListener(IListener listener) {
        listeners.add(listener);
    }

    public void setChannel(Integer channel){
        System.out.printf("Pushed a new channel: %d\n", channel);

        for(IListener listener : listeners){
            // An Async task always executes in new thread
            new Thread(new Runnable() {
                public void run()
                {
                    // check if listener is registered.
                    if (listener != null) {

                        // invoke the callback method of class A
                        listener.call(channel);
                    }
                }
            }).start();
        }
    }
}
