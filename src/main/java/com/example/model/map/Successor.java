package com.example.model.map;

public interface Successor {
    int[][] SUCCESSORS = {
        {-1, -1}, {0, -1}, {1, -1},
        {-1, 0}, {1, 0},
        {-1, 1}, {0, 1}, {1, 1}
    };
}

