package com.example.server.model.people;

import java.util.LinkedList;

import com.example.server.model.map.Tile;

public interface MoveInteface {

    void move(Tile tile);

    void move(LinkedList<Tile> tiles);

}
