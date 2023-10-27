package com.hampus.dungeonRun.logic;

import com.hampus.dungeonRun.characters.CharacterManager;

public interface ICombat
{
    void takeDamage(CharacterManager characterManager);
    void levelUp(Menu menu, CharacterManager characterManager);
    boolean isItACrit(CharacterManager characterManager);
    boolean didDodge(CharacterManager characterManager);


}
