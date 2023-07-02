package com.example.model.people;

import java.util.LinkedList;

import com.example.model.map.Tile;

public interface MoveInteface {

    void move(Tile tile);

    void move(LinkedList<Tile> tiles);

}
