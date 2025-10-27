case DEVICE:
    devices = new ContinuityDevice[]{
        // ОРИГИНАЛЬНЫЕ УСТРОЙСТВА
        new ContinuityDevice("0x0E20", "AirPods Pro", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0620", "Beats Solo 3", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0A20", "AirPods Max", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x1020", "Beats Flex", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0055", "Airtag", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0030", "Hermes Airtag", ContinuityDevice.type.DEVICE),
        
        // 🎯 ШУТОЧНЫЕ УСТРОЙСТВА - ДОБАВЛЯЕМ СЮДА
        new ContinuityDevice("0x0055", "🚨 POLICE iPhone", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0030", "📡 GOV iPad", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0E20", "👽 UFO Device", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0620", "💣 Bomb Squad", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0A20", "🚗 Tesla Security", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x1020", "🛰️ NSA Monitor", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0220", "⚡ Energy Dept", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0F20", "🎯 Military Grade", ContinuityDevice.type.DEVICE),
        
        // ОСТАЛЬНЫЕ ОРИГИНАЛЬНЫЕ
        new ContinuityDevice("0x0220", "AirPods", ContinuityDevice.type.DEVICE),
        new ContinuityDevice("0x0F20", "AirPods 2nd Gen", ContinuityDevice.type.DEVICE),
        // ... остальные оригинальные устройства
    };
    break;