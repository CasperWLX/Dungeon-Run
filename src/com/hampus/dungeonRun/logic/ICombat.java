package com.hampus.dungeonRun.logic;

public interface ICombat
{
    boolean didDodge(int multiplier);
    boolean isItACriticalHit(int criticalRate);
}
