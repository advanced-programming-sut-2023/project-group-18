package com.example.model.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.model.Game;
import com.example.model.WriteInFile;
import com.google.gson.Gson;

public class GameMap implements WriteInFile {
    private static final int LENGTH = 3;
    private static final String line = "\n--------------------------------------------------------------------------------------------";
    private final Game game;
    private final int id;
    private final int length;
    private final Cell[][] map;
/*
    public GameMap(int id) throws Exception {
        Gson gson = new Gson();
        this.id = id;
        File mapFile = getGameMapFileById(id);
        try (Scanner scanner = new Scanner(mapFile)) {
            map = gson.fromJson(scanner.nextLine(), Cell[][].class);
        } catch (JsonSyntaxException | FileNotFoundException e) {
            throw e;
        }
    }
*/

    public GameMap(String size, Game game) {
        this.game = game;
        // id = getNextId();
        // goToNextId();
        id = 0;
        length = MapSizes.getMapSize(size);
        map = new Cell[length][length];
        for (int yCordinate = 0; yCordinate < map.length; yCordinate++)
            for (int xCordinate = 0; xCordinate < map.length; xCordinate++)
                map[yCordinate][xCordinate] = new Cell(xCordinate, yCordinate, this);
    }


    public Game getGame() {
        return game;
    }

    public Cell getCellByLocation(int xCordinate, int yCordinate) {
        return map[yCordinate][xCordinate];
    }

    public boolean isInBounds(int xCordinate, int yCordinate) {
        return 0 <= xCordinate && 0 <= yCordinate && yCordinate < map.length && xCordinate < map.length;
    }

/*
    private File getGameMapFileById(int id) {
        File main = new File("src", "main");
        File resources = new File(main, "resources");
        File maps = new File(resources, "maps");
        File nextId = new File(maps, "nextId.txt");
        Scanner scanner;
        int lastId = 0;
        try {
            scanner = new Scanner(nextId);
            lastId = scanner.nextInt() - 1;
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File mot found!");
            e.printStackTrace();
        }
        File mapFile = new File(nextId, "map" + id + ".json");
        if (id <= lastId) return mapFile;
        try {
            mapFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Some problem in creating file!");
            e.printStackTrace();
        }
        return mapFile;
    }
*/

    public String showMap(int xCordinate, int yCordinate) {
        String result = line;
        for (int i = yCordinate - LENGTH; i <= yCordinate + LENGTH; i++) {
            for (int k = 0; k < 3; k++) {
                result += "\n|";
                for (int j = xCordinate - 2 * LENGTH; j <= xCordinate + 2 * LENGTH; j++) {
                    result += map[i][j].toString() + "|";
                }
            }
            result += line;
        }
        return result;
    }

    public String showDetails(int xCordinate, int yCordinate) {
        return map[yCordinate][xCordinate].showDetails();
    }

    public boolean areCordinatesValid(int xCordinate, int yCordinate) {
        return (xCordinate - 2 * LENGTH) >= 0
            && (xCordinate + 2 * LENGTH) <= length
            && (yCordinate - LENGTH) >= 0
            && (yCordinate + LENGTH) <= length;
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
/*
    @Override
    public File getNextIdFile() {
        File main = new File("src", "main");
        File resources = new File(main, "resources");
        File maps = new File(resources, "maps");
        return new File(maps, "nextId.txt");
    }

    @Override
    public int getNextId() {
        File nextIdFile = getNextIdFile();
        try (Scanner scanner = new Scanner(nextIdFile)) {
            return scanner.nextInt();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void goToNextId() {
        File nextIdFile = getNextIdFile();
        try (FileWriter fileWriter = new FileWriter(nextIdFile)) {
            fileWriter.write(getNextId() + 1);
        } catch (IOException e) {
            System.err.println("Can't write in file!");
            e.printStackTrace();
        }
    }
*/

}
