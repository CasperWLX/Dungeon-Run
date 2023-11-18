package com.hampus.dungeonRun.logic;

/**
 * Interface with player and monster methods
 */
public interface ICombat
{
    boolean didDodge(int multiplier);
    boolean isItACriticalHit(int criticalRate);
}
