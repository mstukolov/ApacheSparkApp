package ru.maks.anonym;

/**
 * Created by mstukolov on 21.08.2018.
 */
public class AvailableChannelListener implements IListener{
    public Boolean call(Object channel) {
        System.out.printf("Listener check available channel... %d\n", channel);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if((Integer)channel > 0 && (Integer)channel <= 10){
            System.out.printf("SUCCESS: A new channel is available: %d\n", channel);
            return true;
        }
        else if((Integer)channel > 10) {
            System.out.printf("Fail: A new channel is not available: %d", channel);
            return false;
        }
        return true;
    }
}
