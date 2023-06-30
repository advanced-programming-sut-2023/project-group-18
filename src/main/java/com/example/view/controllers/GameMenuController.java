package com.example.view.controllers;

import com.example.controller.GameController;

import com.example.model.*;
import com.example.model.assets.Asset;
import com.example.model.assets.AssetType;
import com.example.model.buildings.*;
import com.example.model.people.UnitType;
import com.example.view.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.List;

public class GameMenuController {
    private final GameController controller = GameController.getInstance();
    @FXML
    private Pane pane;
    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox bottom;
    @FXML
    private HBox imagesHBox;
    @FXML
    private HBox typesHBox;
    private BuildingType selectedBuilding;
    public static List<List<BuildingImage>> buildingListOfLists = new ArrayList<>(7);
    public static List<List<BuildingImage>> assetListOfLists = new ArrayList<>(3);
    public static BarCategory currentCategory = BarCategory.CASTLE;
    public Alert alert = new Alert(Alert.AlertType.NONE);

    public void initialize() {
        Game.getInstance().setGameMenuController(this);
        controller.getGame().setGameMap(100);
        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(UsersData.getUsersData().getUserByUsername("user1"));
        arrayList.add(UsersData.getUsersData().getUserByUsername("user2"));
        arrayList.add(UsersData.getUsersData().getUserByUsername("user3"));
        controller.getGame().makeNewGovernances(arrayList);
        for (BarCategory barCategory : BarCategory.values()) {
            if (!barCategory.equals(BarCategory.NONE)) {
                ArrayList<BuildingImage> buildingImages = new ArrayList<>();
                for (BuildingType buildingType : BuildingType.values()) {
                    if (buildingType.getBarCategory().equals(barCategory)) {
                        try {
                            buildingImages.add(new BuildingImage(imagesHBox, buildingType.getName(), 80, this, false, "buildings"));
                        } catch (Exception e) {
                            System.out.println(buildingType.getName());
                            System.out.println(e.getMessage());
                        }
                    }
                }
                buildingListOfLists.add(buildingImages);
            }
        }
        for (AssetType assetType : AssetType.values()) {
            ArrayList<BuildingImage> assetImages = new ArrayList<>();
            for (Asset asset : Asset.values()) {
                if (asset.getAssetType().equals(assetType)) {
                    try {
                        assetImages.add(new BuildingImage(imagesHBox, asset.getName(), 50, this, false, "assets"));
                    } catch (Exception e) {
                        System.out.println(asset.getName());
                        System.out.println(e.getMessage());
                    }
                }
            }
            assetListOfLists.add(assetImages);
        }
        for (List<BuildingImage> buildingImages : buildingListOfLists) {
            if (buildingImages.size() > 6) {
                for (BuildingImage buildingImage : buildingImages) {
                    buildingImage.setSize(45);
                }
            }
        }
        for (List<BuildingImage> buildingImages : assetListOfLists) {
            if (buildingImages.size() > 6) {
                for (BuildingImage buildingImage : buildingImages) {
                    buildingImage.setSize(40);
                }
            }
        }
    }


    public void initMap() {
        borderPane.setCenter(controller.getGame().getGameMap());
        controller.getGame().getGameMap().addShortcuts();
        //bottom.setBackground(new Background(new BackgroundFill(Color.GREEN)));
        //bottom.setStyle("-fx-background-color: Green;");
        BackgroundSize backgroundSize = new BackgroundSize(1.00,
                1.00,
                true,
                true,
                false,
                false);
        BackgroundImage image = new BackgroundImage(new Image(Main.class.getResourceAsStream("/images/icons/menu.png")),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);
        ArrayList<BuildingImage> typeList = new ArrayList<>();
        ArrayList<BuildingImage> shopImages = new ArrayList<>();
        for (AssetType assetType : AssetType.values()) {
            shopImages.add(new BuildingImage(typesHBox, assetType.getName(), 30, this, true, "assets"));
        }
        for (BuildingImage assetType : shopImages) {
            assetType.addToHBox();
        }
//        for (BarCategory barCategory : BarCategory.values()){
//            if (!barCategory.equals(BarCategory.NONE))
//                typeList.add(new BuildingImage(typesHBox, barCategory.getName(), 20, this, true,"buildings"));
//        }
        for (BarCategory barCategory : BarCategory.values()) {
            if (!barCategory.equals(BarCategory.NONE))
                typeList.add(new BuildingImage(typesHBox, barCategory.getName(), 20, this, true, "buildings"));
        }
        changeMenu(currentCategory);

//        typeList.add(new BuildingImage(typesHBox, "CastleBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "FarmBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "FoodProcessing", 20));
//        typeList.add(new BuildingImage(typesHBox, "IndustryBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "TowerBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "TowerBuilding", 20));
//        typeList.add(new BuildingImage(typesHBox, "WeaponBuilding", 20));
//        for (BuildingType buildingType : BuildingType.values()){
//            if (buildingType.getBarCategory().equals(currentCategory)){
//                try {
//                    castleImages.add(new BuildingImage(imagesHBox, buildingType.getName(), 80));
//                }
//                catch (Exception e){
//                    System.out.println(buildingType.getName());
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
        //BuildingImage buildingImage = new BuildingImage(imagesHBox, "Barracks",80);
        //BuildingImage buildingImage1 = new BuildingImage(imagesHBox, "mercenary post",80);


        setScribe(pane);
        pane.toFront();
        //bottom.setPrefHeight(300);
        //bottom.setMinHeight(600);
//        bottom.setStyle("-fx-background-color: green");
        bottom.setBackground(new Background(image));
    }

    private void setScribe(Pane pane) {
        Governance governance = Game.getInstance().getCurrentGovernance();
        Text popularity = new Text(Integer.toString(governance.getPopularityFactors().getPopularity()));
        popularity.setFont(new Font(15));

        Text treasury = new Text(Integer.toString(governance.getGold()));
        treasury.setFont(new Font(15));

        Text population = new Text(Integer.toString(governance.getPopulation()));
        population.setFont(new Font(15));
        VBox vBox = new VBox(popularity, treasury, population);
        vBox.setSpacing(2);
        vBox.setLayoutX(890);
        vBox.setLayoutY(109);
        pane.getChildren().add(vBox);
    }

    public void changeMenu(BarCategory barCategory) {
        for (List<BuildingImage> buildingImages : buildingListOfLists) {
            if (BuildingType.getBuildingTypeByName(buildingImages.get(0).getName()).getBarCategory().equals(barCategory)) {
                imagesHBox.getChildren().removeAll(imagesHBox.getChildren());
                for (BuildingImage buildingImage : buildingImages) {
                    imagesHBox.getChildren().add(buildingImage.getImageView());
                }
            }
        }
    }

    public void showKeepMenu() {
        PopularityFactors popularityFactors = Game.getInstance().getCurrentGovernance().getPopularityFactors();
        imagesHBox.getChildren().removeAll(imagesHBox.getChildren());
        VBox vBox1 = new VBox();
        vBox1.setSpacing(10);
        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);

        vBox1.getChildren().add(getPopularityNodes(popularityFactors.getFoodFactor(), "food"));
        vBox1.getChildren().add(getPopularityNodes(popularityFactors.getTaxFactor(), "tax"));
        vBox1.getChildren().add(getPopularityNodes(popularityFactors.getFearFactor(), "fear"));

        vBox2.getChildren().add(getPopularityNodes(popularityFactors.getReligiousFactor(), "religion"));
        vBox2.getChildren().add(getPopularityNodes(popularityFactors.getAleFactor(), "ale coverage"));

        imagesHBox.getChildren().addAll(vBox1, vBox2);

    }

    private HBox getPopularityNodes(int factor, String factorName) {
        ImageView imageView = new ImageView();
        Label label = new Label(Integer.toString(factor));
        label.setFont(new Font(15));
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        Label name = new Label(factorName);
        if (factor < 0) {
            label.setTextFill(Color.RED);
            imageView.setImage(new Image(GameMenuController.class.getResource("/popularityFactors/1.jpg").toExternalForm()));
        } else if (factor == 0) {
            label.setTextFill(Color.YELLOW);
            imageView.setImage(new Image(GameMenuController.class.getResource("/popularityFactors/2.jpg").toExternalForm()));
        } else {
            label.setTextFill(Color.GREEN);
            imageView.setImage(new Image(GameMenuController.class.getResource("/popularityFactors/3.jpg").toExternalForm()));
        }
        HBox hBox = new HBox(label, imageView, name);
        hBox.setSpacing(20);
        return hBox;
    }

    public void changeShopAssetType(AssetType assetType) {
        System.out.println(assetType.getName());
        for (List<BuildingImage> assetImages : assetListOfLists) {
            if (Asset.getAssetByName(assetImages.get(0).getName()).getAssetType().equals(assetType)) {
                imagesHBox.getChildren().removeAll(imagesHBox.getChildren());
                for (BuildingImage assetImage : assetImages) {
                    assetImage.addToHBox();
                }
            }
        }
    }

    public void clickOnAsset(Asset asset) {
        Governance governance = controller.getGame().getCurrentGovernance();
        int stock = governance.getAssetCount(asset);
        int buyPrice = asset.getBuyPrice() * 5;
        int sellPrice = asset.getSellPrice() * 5;
        Label stockLabel = new Label("Stock: " + stock);
        Button buyButton = new Button("Buy: " + buyPrice);
        buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (governance.getGold() >= buyPrice) {
                    if (governance.canAddAssetToStorage(asset, 5)) {
                        governance.addAssetToStorage(asset, 5);
//                        stockLabel.setText("Stock: " + governance.getAssetCount(asset));
                        alert.setAlertType(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("You bought successfully!");
                        clickOnAsset(asset);
                    } else {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("You haven't enough space");
                    }
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("You haven't enough gold.");
                }
                alert.show();
            }
        });
        Button sellButton = new Button("Sell: " + asset.getSellPrice() * 5);
        sellButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (governance.canRemoveAssetFromStorage(asset, 5)) {
                    governance.removeAssetFromStorage(asset, 5);
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("sold successfully");
                    alert.show();
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("You haven't enough asset");
                    alert.show();
                }
            }
        });
        cleanTypesHBox();
        typesHBox.getChildren().addAll(stockLabel, buyButton, sellButton);

    }

    public BuildingType getSelectedBuilding() {
        return selectedBuilding;
    }

    public void setSelectedBuilding(BuildingType selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
        controller.getGame().getGameMap().setSelectedBuilding(selectedBuilding);
    }

    public void cleanTypesHBox() {
        for (int i = typesHBox.getChildren().size() - 1; i > 2; i--) {
            typesHBox.getChildren().remove(i);
        }
    }

    public void repairMenu(Building selectedBuilding) {
        imagesHBox.getChildren().removeAll(imagesHBox.getChildren());
        Tower tower = (Tower) selectedBuilding;
        int hitpoint = selectedBuilding.getHitpoint();
        int maxHitpoint = selectedBuilding.getBuildingType().getHitpoint();
        Label label = new Label("hitpoint/max hitpoint");
        label.setFont(new Font(20));
        Label hp = new Label(hitpoint + "/" + maxHitpoint);
        hp.setFont(new Font(20));
        Button button = new Button("repair");
        button.setMinWidth(70);
        Label repair = new Label();
        repair.setFont(new Font(20));
        repair.setVisible(false);
        button.setOnMouseClicked(mouseEvent -> {
            if (tower.canRepair()) {
                tower.repair();
                repair.setTextFill(Color.GREEN);
                repair.setText("repaired successfully");
                repair.setVisible(true);
            }
            else {
                repair.setTextFill(Color.RED);
                repair.setText("can't repair");
                repair.setVisible(true);
            }
        });
        imagesHBox.getChildren().addAll(label, hp, button, repair);
    }

    public void gateMenu(Building selectedBuilding) {
        imagesHBox.getChildren().removeAll(imagesHBox.getChildren());
        Gate gate = (Gate) selectedBuilding;
        Button open = new Button("open");
        open.setMinWidth(100);
        open.setMinHeight(30);
        open.setOnMouseClicked(mouseEvent -> gate.open());
        Button close = new Button("close");
        close.setMinWidth(100);
        close.setMinHeight(30);
        close.setOnMouseClicked(mouseEvent -> gate.close());
        imagesHBox.getChildren().addAll(open, close);
    }

    public void barracksMenu(Building selectedBuilding) {
        imagesHBox.getChildren().removeAll(imagesHBox.getChildren());
        Barracks barracks = (Barracks) selectedBuilding;
        ArrayList<UnitType> unitTypes = UnitType.getUnitTypesByBuildingType(selectedBuilding.getBuildingType());
        Label error = new Label();
        error.setFont(new Font(20));
        error.setTextFill(Color.RED);
        error.setVisible(false);
        for (UnitType unitType : unitTypes) {
            ImageView imageView = new ImageView(unitType.getType().getIcon());
            imageView.setOnMouseClicked(mouseEvent -> {
                if (barracks.canMakeSoldier(unitType)) {
                    barracks.makeUnit(unitType);
                    error.setVisible(false);
                } else {
                    error.setText("can't make " + unitType.name());
                    error.setVisible(true);
                }
            });
            imagesHBox.getChildren().add(imageView);
        }
    }

    public void gunsmithMenu(Building selectedBuilding) {
        imagesHBox.getChildren().removeAll(imagesHBox.getChildren());

    }
}
