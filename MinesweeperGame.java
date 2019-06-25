package com.javarush.games.minesweeper;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {

    private static final int SIDE=9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;

    @Override
    public void initialize () {
        setScreenSize (SIDE, SIDE);
        createGame();
    }

    private void createGame () {
        int countOfMines = 0;
        for (int i = 0; i<SIDE; i++) {
            for (int j =0; j<SIDE; j++) {
                setCellColor(i, j, Color.AQUAMARINE);
                if (getRandomNumber(10) == 9) {
                    gameField [j][i] = new GameObject(i, j, true);
                    countOfMines++;
                } else gameField [j][i] = new GameObject(i, j, false);

            }
        }
        countMinesOnField = countOfMines;
        countMineNeighbors();
    }

    private void countMineNeighbors() {
        for (int i = 0; i<SIDE; i++) {
            for (int j =0; j<SIDE; j++) {
                if (!gameField [j][i].isMine) {
                    ArrayList<GameObject> anList = getNeighbors(gameField [j][i]);
//                    setCellColor(i, j, Color.BLUE);
                    for(GameObject anObject : anList){
                        if (anObject.isMine) gameField [j][i].countMineNeighbors++;
                    }
//                    setCellValue(i, j, Integer.toString(gameField [j][i].countMineNeighbors));
                }
//                if (gameField [j][i].isMine) setCellColor(i, j, Color.BLACK);
            }
        }
    }

    private ArrayList<GameObject> getNeighbors (GameObject anField) {
        int firstX = anField.x-1, firstY = anField.y-1, lastX = anField.x+2, lastY = anField.y+2;
        ArrayList<GameObject> neighborsList = new ArrayList<>();
        if (firstX<0) firstX=0;
        if (lastX>SIDE) lastX=SIDE;
        if (firstY<0) firstY=0;
        if (lastY>SIDE) lastY=SIDE;
        for (int i=firstX; i<lastX; i++) {
            for (int j=firstY;  j<lastY; j++) {
                if ((j != anField.y) || (i != anField.x)) neighborsList.add(gameField[j][i]);
            }
        }
        return neighborsList;
    }

}
