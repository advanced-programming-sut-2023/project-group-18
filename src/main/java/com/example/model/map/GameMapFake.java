package com.example.model.map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.model.Game;
import com.example.model.WriteInFile;
import com.google.gson.Gson;

public class GameMapFake implements WriteInFile, Successor {
    private static final int LENGTH = 3;
    private static final String line = "\n--------------------------------------------------------------------------------------------";
    private final Game game;
    private final int id;
    private final int length;
    private final Cell[][] map;

    public GameMapFake(String size, Game game) {
        this.game = game;
        // id = getNextId();
        // goToNextId();
        id = 0;
        length = MapSizes.getMapSize(size);
        map = new Cell[length][length];
        for (int yCoordinate = 0; yCoordinate < map.length; yCoordinate++)
            for (int xCoordinate = 0; xCoordinate < map.length; xCoordinate++)
                map[yCoordinate][xCoordinate] = new Cell(xCoordinate, yCoordinate, this);
    }

    public int getMapSize() {
        return map.length;
    }

    public Game getGame() {
        return game;
    }

    public Cell getCellByLocation(int xCoordinate, int yCoordinate) {
        if (!isInBounds(xCoordinate, yCoordinate)) return null;
        return map[yCoordinate][xCoordinate];
    }

    public boolean isInBounds(int xCoordinate, int yCoordinate) {
        return 0 <= xCoordinate && 0 <= yCoordinate && yCoordinate < map.length && xCoordinate < map.length;
    }

    public String showMap(int xCoordinate, int yCoordinate) {
        String result = line;
        for (int i = yCoordinate - LENGTH; i <= yCoordinate + LENGTH; i++) {
            for (int k = 0; k < 3; k++) {
                result += "\n|";
                for (int j = xCoordinate - 2 * LENGTH; j <= xCoordinate + 2 * LENGTH; j++)
                    result += map[i][j].toString(k) + "|";
            }
            result += line;
        }
        return result;
    }

    public String showDetails(int xCoordinate, int yCoordinate) {
        return map[yCoordinate][xCoordinate].showDetails();
    }

    public boolean areCoordinatesValid(int xCoordinate, int yCoordinate) {
        return (xCoordinate - 2 * LENGTH) >= 0
                && (xCoordinate + 2 * LENGTH) <= length
                && (yCoordinate - LENGTH) >= 0
                && (yCoordinate + LENGTH) <= length;
    }

    @Override
    public void writeInFile() {
        Gson gson = new Gson();
        File main = new File("src", "main");
        File resources = new File(main, "resources");
        File maps = new File(resources, "maps");
        File mapId = new File(maps, "map"+ id + ".json");
        try (FileWriter fileWriter = new FileWriter(mapId)) {
            fileWriter.write(gson.toJson(map));
        } catch (IOException e) {
            System.err.println("Can't write in file!!!");
        }
    }

    public ArrayList<Cell> neighbourCells(Cell cell){
        ArrayList<Cell> neighbourCells = new ArrayList<>();
        int xCoordinate = cell.getxCoordinate();
        int yCoordinate = cell.getyCoordinate();
        for (int[] neighbour : SUCCESSORS){
            int x = xCoordinate+neighbour[0];
            int y = yCoordinate+neighbour[1];
            Cell neighbourCell = game.getGameMap().getCellByLocation(x,y);
            if (neighbourCell != null)
                neighbourCells.add(neighbourCell);
        }
        return neighbourCells;
    }

}