package ru.otus.java.hw4.logger;

import com.sun.management.GarbageCollectionNotificationInfo;

public class GCEventInfo {

    private String name;
    private Generation generation;
    private long duration;

    private GCEventInfo() {

    }

    public static GCEventInfo parse(GarbageCollectionNotificationInfo notificationInfo) {
        return gcInfoParser()
            .setGeneration(Generation.parseGeneration(notificationInfo.getGcAction()))
            .setName(notificationInfo.getGcName())
            .setDuration(notificationInfo.getGcInfo().getDuration());
    }

    public static GCEventInfo gcInfoParser() {
        return new GCEventInfo();
    }

    public GCEventInfo setName(String name) {
        this.name = name;
        return this;
    }

    public GCEventInfo setGeneration(Generation generation) {
        this.generation = generation;
        return this;
    }

    public GCEventInfo setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public String toString() {
        return new StringBuilder("Generation: ")
            .append(this.generation)
            .append(", GC name = ")
            .append(this.name)
            .append(", GC duration = ")
            .append(String.valueOf(this.duration))
            .toString();
    }

    public enum Generation {
        YOUNG("end of minor GC"),
        OLD("end of major GC");

        private String gcMesage;

        Generation(String logString) {
            this.gcMesage = logString;
        }

        public static Generation parseGeneration(String gcMesage) {
            if (YOUNG.gcMesage.equals(gcMesage)) {
                return YOUNG;
            }
            if (OLD.gcMesage.equals(gcMesage)) {
                return OLD;
            }
            else return null;
        }
    }

    public Generation getGeneration() {
        return generation;
    }
}