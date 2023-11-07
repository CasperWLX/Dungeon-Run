package com.hampus.dungeonRun.logic;

public interface ICombat
{
    boolean didDodge(int multiplier);
    boolean isItACriticalHit(int criticalRate);
    void takeDamage(int damage, String characterTakingDamage, String characterDealingDamage, int opponentCritRate);
}
