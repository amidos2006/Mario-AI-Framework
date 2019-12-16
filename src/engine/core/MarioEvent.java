package engine.core;

import engine.helper.EventType;

public class MarioEvent {
    private EventType eventType;
    private int eventParam;
    private float marioX;
    private float marioY;
    private int marioState;
    private int time;

    public MarioEvent(EventType eventType) {
        this.eventType = eventType;
        this.eventParam = 0;
        this.marioX = 0;
        this.marioY = 0;
        this.marioState = 0;
        this.time = 0;
    }

    public MarioEvent(EventType eventType, int eventParam) {
        this.eventType = eventType;
        this.eventParam = eventParam;
        this.marioX = 0;
        this.marioY = 0;
        this.marioState = 0;
        this.time = 0;
    }

    public MarioEvent(EventType eventType, float x, float y, int state, int time) {
        this.eventType = eventType;
        this.eventParam = 0;
        this.marioX = x;
        this.marioY = y;
        this.marioState = state;
        this.time = time;
    }

    public MarioEvent(EventType eventType, int eventParam, float x, float y, int state, int time) {
        this.eventType = eventType;
        this.eventParam = eventParam;
        this.marioX = x;
        this.marioY = y;
        this.marioState = state;
        this.time = time;
    }

    public int getEventType() {
        return this.eventType.getValue();
    }

    public int getEventParam() {
        return this.eventParam;
    }

    public float getMarioX() {
        return this.marioX;
    }

    public float getMarioY() {
        return this.marioY;
    }

    public int getMarioState() {
        return this.marioState;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public boolean equals(Object obj) {
        MarioEvent otherEvent = (MarioEvent) obj;
        return this.eventType == otherEvent.eventType &&
                (this.eventParam == 0 || this.eventParam == otherEvent.eventParam);
    }
}
