package com.hampus.dungeonRun.logic;

public class InputMenu
{
    private final Colorize COLORIZE = new Colorize();
    public void pressEnter()
    {
        String text = COLORIZE.printWhite("Press enter to continue");
        System.out.print(COLORIZE.printBlackBackground(text));
    }
}
