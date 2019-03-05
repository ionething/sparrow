package com.github.ionething.sparrow.framework.util.id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class LocalIdGenerator {

    private static short increaseNumber4Id = 0;

    private static short increaseNumber4Number = 0;

    private static final short MAX_SERIAL_NUMBER =  10000;

    /**
     * 简单Id
     * @return
     */
    public static String genSimpleId() {
        short serialNumber = 0;
        synchronized (LocalIdGenerator.class) {
            if (increaseNumber4Id >= 9999 ) {
                serialNumber = increaseNumber4Id = 1;
            } else {
                serialNumber = ++increaseNumber4Id;
            }
        }

        String serialNumberStr = (MAX_SERIAL_NUMBER + serialNumber) + "";
        return System.currentTimeMillis() + serialNumberStr.substring(1);
    }

    /**
     * 简单流水号
     * @return
     */
    public static String genSerialNumber() {
        short serialNumber = 0;
        synchronized (LocalIdGenerator.class) {
            if (increaseNumber4Number >= 9999 ) {
                serialNumber = increaseNumber4Number = 1;
            } else {
                serialNumber = ++increaseNumber4Number;
            }
        }
        String serialNumberStr = (MAX_SERIAL_NUMBER + serialNumber) + "";
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(new Date()) + serialNumberStr.substring(1);
    }

    /**
     * 生成UUID
     * @return
     */
    public static String getUUID() {
        return getUUID(false);
    }

    /**
     * 生成UUID
     * @param hasBound
     * @return
     */
    public static String getUUID(boolean hasBound) {
        String result = UUID.randomUUID().toString();
        if (!hasBound) {
            result = result.replaceAll("-", "");
        }
        return result;
    }
}
