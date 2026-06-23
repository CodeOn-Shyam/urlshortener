package com.codeon.urlshortener.util;

public class SnowflakeGenerator {
    private static final long EPOCH = 1767225600000L; // Custom epoch (2026-01-01)
    private static final long MACHINE_ID_BITS = 10L; // Number of bits for machine ID
    private static final long SEQUENCE_BITS = 12L; // Number of bits for sequence
    private static final long MAX_MACHINE_ID = (1L << MACHINE_ID_BITS) - 1; // Maximum machine ID
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1; // Maximum sequence value
    private final long machineId; // Machine ID
    private long sequence = 0L; // Sequence number
    private long lastTimestamp = -1L; // Last timestamp
    public SnowflakeGenerator(long machineId) {
        if (machineId < 0 || machineId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException(String.format("Machine ID must be between 0 and %d", MAX_MACHINE_ID));
        }
        this.machineId = machineId;
    }
    public synchronized long nextId(){
        long currentTimestamp = timestamp();
        if(currentTimestamp < lastTimestamp){
            throw new RuntimeException(
                "Clock moved back"
            );
        }
        if(currentTimestamp==lastTimestamp){
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if(sequence==0){
                currentTimestamp = waitForNextMillis(lastTimestamp);
            }
        }else{
            sequence=0;
        }
        lastTimestamp = currentTimestamp;
        return ((currentTimestamp - EPOCH)<<22)|(machineId<<12)|sequence;
    }
    public long waitForNextMillis(long lastTimestamp){
        long timestamp = timestamp();
        while(timestamp<=lastTimestamp){
            timestamp = timestamp();
        }
        return timestamp;
    }
    public long timestamp(){
        return System.currentTimeMillis();
    }
}
