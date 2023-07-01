package com.example.server.model.map;

public enum MapSizes {
    NORMAL ("normal", 200),
    LARGE ("large", 400)
    ;

    private final String name;
    private final int length;

    private MapSizes(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public static int getMapSize(String name) {
        for (MapSizes values : MapSizes.values())
            if (values.name.equals(name)) return values.length;
        return 0;
    }


}
