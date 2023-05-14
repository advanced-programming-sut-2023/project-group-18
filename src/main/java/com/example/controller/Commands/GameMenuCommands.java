package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SHOW_POPULARITY_FACTORS("^show popularity factors$"),
    SHOW_POPULARITY("^show popularity$"),
    SHOW_FOOD_LIST("^show food list$"),
    FOOD_RATE("^food rate -r (?<rateNumber>-?\\d)$"),
    SHOW_FOOD_RATE("^food rate show$"),
    TAX_RATE("^tax rate -r (?<rateNumber>-?\\d)$"),
    SHOW_TAX_RATE("^tax rate show$"),
    FEAR_RATE("fear rate -r (?<rateNumber>-?\\d)$"),
    DROP_BUILDING("^dropbuilding(?<fields>( -\\S+ ((\\S+)|(\".+\"))){3})$"),
    SELECT_BUILDING("^select building (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$"),
    CREATE_UNIT("^createunit (-t (?<type>(\\S+)|(\".+\")) -c (?<count>\\d+))|(-c (?<count2>\\d+) -t (?<type2>(\\S+)|(\".+\")))$"),
    REPAIR("^repair$"),
    SELECT_UNIT("^select unit (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$"),
    MOVE_UNIT("^move unit to (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$"),
    PATROL_UNIT("^patrol unit(?<fields>( -\\S+ \\d+){4})$"),
    SET_STATE("^set(?<fields>( -\\S \\S+){3})$"),
    ATTACK("^attack -e (?<xCoordinate>\\d+) (?<yCoordinate>\\d+)$"),
    AIR_ATTACK("^attack (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$"),
    POUR_OIL("^pour oil -d \\S+$"),
    DIG_TUNNEL("^dig tunnel (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$"),
    BUILD("^build -q (?<equipmentName>\\S+)$"),
    DISBAND_UNIT("^disband unit$"),
    SET_TEXTURE("^settexture(?<fields>( -\\S .+){3})$"),
    SET_TEXTURE_RECTANGLE("^settexture (?<fields>( -\\S \\S+){5})$"),
    CLEAR("^clear (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$"),
    DROP_ROCK("^droprock (?<fields>( -\\S \\S+){3})$"),
    DROP_TREE("^droptree (?<fields>( -\\S \\S+){3})$"),
    DROP_UNIT("^dropunit (?<fields>( -\\S \\S+){4})$"),
    CURRENT_MENU("^show current menu$"),
    SHOW_MAP("^show map (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$"),
    EXIT("^exit$");
    private String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
