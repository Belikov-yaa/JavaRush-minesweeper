package com.javarush.games.minesweeper;

public class GameObject {
    public int x, y, countMineNeighbors;
    public boolean isMine, isOpen = false;
    public GameObject (int argX, int argY, boolean Mine) {
        x = argX;
        y = argY;
        isMine = Mine;
    }
}
