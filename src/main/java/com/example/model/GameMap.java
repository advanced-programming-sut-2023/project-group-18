package com.example.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GameMap implements NeedsId, WriteInFile {
    private final int id;
    private final Tile[][] map;

    public GameMap(int id) throws Exception {
        Gson gson = new Gson();
        this.id = id;
        File mapFile = getGameMapFileById(id);
        try (Scanner scanner = new Scanner(mapFile)) {
            map = gson.fromJson(scanner.nextLine(), Tile[][].class);
        } catch (JsonSyntaxException | FileNotFoundException e) {
            throw e;
        }
    }

    public GameMap(String size) {
        id = getNextId();
        goToNextId();
        int length = MapSizes.getMapSize(size);
        map = new Tile[length][length];
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map.length; j++)
                map[i][j] = new Tile();
    }


    public Tile getTileByLocation(int xCordinate, int yCordinate) {
        return map[yCordinate][xCordinate];
    }

    public boolean isInBounds(int xCordinate, int yCordinate) {
        return 0 <= xCordinate && 0 <= yCordinate && yCordinate < map.length && xCordinate < map.length;
    }


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



}