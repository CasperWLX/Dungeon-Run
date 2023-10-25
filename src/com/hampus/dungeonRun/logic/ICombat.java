package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;

public interface ICombat
{
    void scream();
    void takeDamage(CharacterManager characterManager);

    void levelUp();
    boolean isItACrit(CharacterManager characterManager);
    boolean didDodge(CharacterManager characterManager);


}
