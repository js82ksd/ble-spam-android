package com.tutozz.blespam;

public class ContinuityDevice {
    public enum type {
        DEVICE, ACTION
    }

    private String value;
    private String name;
    private type deviceType;

    public ContinuityDevice(String value, String name, type deviceType) {
        this.value = value;
        this.name = name;
        this.deviceType = deviceType;
    }

    public String getValue() { return value; }
    public String getName() { return name; }
    public type getDeviceType() { return deviceType; }

    // 🎯 ШУТОЧНЫЕ IOS УСТРОЙСТВА - ДОБАВЛЯЕМ СЮДА
    public static final ContinuityDevice[] JOKE_DEVICES = {
        // Оригинальные устройства (если есть)
        // new ContinuityDevice("оригинальное_значение", "Apple Watch", type.DEVICE),
        
        // ШУТОЧНЫЕ УСТРОЙСТВА:
        new ContinuityDevice("071907022075aa3001000045121212000000000000000000000000", "🚨 POLICE iPhone", type.DEVICE),
        new ContinuityDevice("071907052075aa3001000045121212000000000000000000000000", "📡 GOV iPad", type.DEVICE),
        new ContinuityDevice("071907072075aa3001000045121212000000000000000000000000", "👽 UFO Device", type.DEVICE),
        new ContinuityDevice("071907092075aa3001000045121212000000000000000000000000", "💣 Bomb Squad", type.DEVICE),
        new ContinuityDevice("071907022075aa3001000045121212000000000000000000000000", "🚗 Tesla Security", type.DEVICE),
        new ContinuityDevice("071907052075aa3001000045121212000000000000000000000000", "🛰️ NSA Monitor", type.DEVICE),
        new ContinuityDevice("071907072075aa3001000045121212000000000000000000000000", "⚡ Energy Dept", type.DEVICE),
        new ContinuityDevice("071907092075aa3001000045121212000000000000000000000000", "🎯 Military Grade", type.DEVICE)
    };

    // Метод для получения случайного шуточного устройства
    public static ContinuityDevice getRandomJokeDevice() {
        int randomIndex = (int) (Math.random() * JOKE_DEVICES.length);
        return JOKE_DEVICES[randomIndex];
    }
}