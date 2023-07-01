package com.example.client.model.people;

import com.example.client.model.map.Tile;

import java.util.LinkedList;

public interface MoveInteface {

    void move(Tile tile);

    void move(LinkedList<Tile> tiles);

}
