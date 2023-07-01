package com.example.controller;

import java.util.ArrayList;
import java.util.Random;

import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.User;
import com.example.model.UsersData;
import com.example.model.assets.Asset;
import com.example.model.buildings.Building;
import com.example.model.buildings.BuildingType;
import com.example.model.map.GameMap;
import com.example.view.images.TextureImages;

public class GameController {
    private static GameController controller;
    private Game game;

    
    private GameController() {
        
    }

    public static GameController getInstance() {
        return controller == null ? controller = new GameController() : controller;
    }

    public void setGame(ArrayList<String> usernames, GameMap gameMap) {
        final ArrayList<Governance> governances = new ArrayList<>();
        for (String username : usernames)
            governances.add(new Governance(UsersData.getUsersData().getUserByUsername(username)));
        this.game = new Game(governances, gameMap);
        makeNewGovernances();
    }

    private void makeNewGovernances() {
        dropKeeps(game.getGameMap());
        for (Governance governance : game.getGovernances()) {
            governance.addAssetToStorage(Asset.WOOD, 20);
            governance.addAssetToStorage(Asset.STONE, 20);
            governance.addAssetToStorage(Asset.IRON, 20);
            governance.addAssetToStorage(Asset.WHEAT, 20);
        }
    }

    private void dropKeeps(GameMap gameMap) {
        final double[][] COORDINATES = {{50, 50}, {150, 150}, {50, 150}, {150, 50}, {50, 100}, {100, 50}, {100, 150}, {150, 100}};
        final double mapCoefficient = game.getGameMap().getLength() / 200.0d;
        Random random = new Random();
        int index = -1;
        for (Governance governance : game.getGovernances()) {
            index++;
            double x = COORDINATES[index][0] * mapCoefficient * GameMap.TILE_LENGTH + random.nextInt(-2, 3);
            double y = COORDINATES[index][1] * mapCoefficient * GameMap.TILE_LENGTH / 2 + random.nextInt(-2, 3);
            gameMap.setKeep(x, y, governance);
            gameMap.setSelectedBuildingType(BuildingType.FIRE);
            gameMap.dropBuilding(x - GameMap.TILE_LENGTH * 5, y + GameMap.TILE_LENGTH * 5);
            gameMap.setSelectedBuildingType(BuildingType.STOCKPILE);
            gameMap.dropBuilding(x + GameMap.TILE_LENGTH * 5, y + GameMap.TILE_LENGTH * 5);
        }
        gameMap.setSelectedBuildingType(null);
    }

    public boolean isInGame(User user) {
        for (Governance governance : game.getGovernances())
            if (governance.getOwner().equals(user)) return true;
        return false;
    }

    public void addToPopulation(Governance governance, int population) {
        governance.addPopulation(population);
    }

    public void selectBuilding(Building selectedBuilding) {
        selectedBuilding.getGovernance().setSelectedBuilding(selectedBuilding);
        /* TODO: NEED TO GO TO THE CLIENT SIDE
        if (selectedBuilding.getBuildingType().equals(BuildingType.KEEP))
            gameMenuController.showKeepMenu();
        else if (selectedBuilding.getBuildingType().getCategory().equals(Category.TOWER) || selectedBuilding.getBuildingType().getCategory().equals(Category.WALL)) {
            gameMenuController.repairMenu(selectedBuilding);
        } else if (selectedBuilding.getBuildingType().getCategory().equals(Category.GATE)) {
            gameMenuController.gateMenu(selectedBuilding);
        } else if (selectedBuilding.getBuildingType().getCategory().equals(Category.BARRACKS)) {
            gameMenuController.barracksMenu(selectedBuilding);
        } else if (selectedBuilding.getBuildingType().getCategory().equals(Category.GUNSMITH)) {
            gameMenuController.gunsmithMenu(selectedBuilding);
        } */
    }

    /* TODO: METHODS FOR CLIENT SIDE
    public void initTiles() {
        URL url = getClass().getResource("/maps/" + name + ".bin");
        try {
            File file = new File(url.toURI());
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            for (int yIndex = 0; yIndex < length; yIndex++) {
                for (int xIndex = 0; xIndex < length; xIndex++) {
                    double x = (yIndex % 2) * TILE_LENGTH / 2 + TILE_LENGTH * xIndex;
                    double y = TILE_LENGTH * yIndex / 2;
                    byte byt = dataInputStream.available() == 0 ? 0 : dataInputStream.readByte();
                    Tile tile = new Tile(x, y, byt, this);
                    centers.add(tile);
                    byt = dataInputStream.available() == 0 ? 0 : dataInputStream.readByte();
                    Tree.addTree(this, byt, x, y);
                }
            }
            dataInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInitScales() {
        setMaxWidth(200);
        setMaxHeight(200);
        scaleXProperty().bind(scale);
        scaleYProperty().bind(scale);
        setScale(3.0);
    }

    public void addEventFilters() {
        MapGestures mapGestures = new MapGestures(this);
        addEventFilter(MouseEvent.MOUSE_PRESSED, mapGestures.getOnMousePressedEventHandler());
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mapGestures.getOnMouseDraggedEventHandler());
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mapGestures.getOnMouseMovedEventHandler());
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mapGestures.getOnMouseConditionalMoveEventHandler());
        addEventFilter(ScrollEvent.ANY, mapGestures.getOnScrollEventHandler());
    }
    */

    // TODO: METHODS FOR CLIENT SIDE
    public void addShortcuts(GameMap gameMap) {
        gameMap.getScene().setOnKeyPressed(key -> {
            switch (key.getCode()) {
                case G -> gameMap.setTexture(TextureImages.GROUND);
                case F -> gameMap.setTexture(TextureImages.FARM);
                case I -> gameMap.setTexture(TextureImages.IRON);
                case S -> gameMap.setTexture(TextureImages.SLAB);
                case W -> gameMap.setTexture(TextureImages.WATER);
                case T -> gameMap.addTree();
                case B -> gameMap.backToMap();
                case Z -> gameMap.removeLastBuilding();
                case M -> gameMap.customizeMove();
                case D -> gameMap.removeSelectedBuilding();
                case C -> gameMap.copyToClipboard();
                case V -> gameMap.pastFromClipboard();
                case Q -> gameMap.deselectUnits();
                case A -> gameMap.attackUnit();
                default -> {}
            }
        });
    }
    
}
