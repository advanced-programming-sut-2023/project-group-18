package com.example.server.model.map;

import java.util.ArrayList;

public interface Successor {
    int[][][] SUCCESSORS = {{
            {-1, -1}, {0, -2}, {0, -1},
            {-1, 0}, {1, 0},
            {-1, 1}, {0, 2}, {0, 1}
        }, {
            {0, -1}, {0, -2}, {1, -1},
            {-1, 0}, {1, 0},
            {0, 1}, {0, 2}, {1, 1}
    }};

    default ArrayList<Tile> getSuccessors(GameMap gameMap, int xIndex, int yIndex) {
        ArrayList<Tile> arrayList = new ArrayList<>();
        for (int[] successor : SUCCESSORS[yIndex % 2])
            arrayList.add(gameMap.getTileByIndex(xIndex + successor[0], yIndex + successor[1]));
        return arrayList;
    }

    default ArrayList<Tile> getSuccessors(GameMap gameMap, Tile tile) {
        int yIndex = gameMap.getTileYIndex(tile.getPoint2d().getY());
        int xIndex = gameMap.getTileXIndex(tile.getPoint2d().getX(), yIndex);
        return getSuccessors(gameMap, xIndex, yIndex);
    }

}

