package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessThread extends Thread{

    private boolean isRunning = true;
    private final Context context;
    private final Random random = new Random();
    private final String name;
    private final String group;

    public ProcessThread(Context context, String name, String group) {
        this.context = context;
        this.name = name;
        this.group = group;
    }

    @Override
    public void run() {
        while (isRunning) {
            sleep();
            sendMessage();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            Log.d("ProcessThread", "Thread has stopped!");
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("send_message");
        intent.putExtra("broadcast_receiver_extra",
                new Date(System.currentTimeMillis()) + " " + name + " " + group);
        context.sendBroadcast(intent);
    }

    public void stopThread() {
        isRunning = false;
    }
}
