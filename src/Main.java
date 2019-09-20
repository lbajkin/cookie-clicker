import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Button moveShop; // pour changer de scene, clicker -> shop
    private Button moveClicker; // shop -> clicker

    private Stage stage;

    final Integer[] cookies = {0}; // nombre de cookies
    final Integer[] passiveCookies = {0}; // cookies par seconde
    private int clickPower = 1; // cookies par click

    // couts pour les upgrades
    private int[] coutPower = {25, 110, 500, 1750, 8000};
    private int[] coutPassive = {50, 125, 375, 850, 1500};
    private int[] coutGiga = {25000, 1000000, 99999999};
    private int[] coutOmega = {10000, 400000, 40000000};

    private Label labelCookies = new Label("Nombre de cookies : " + Integer.toString(cookies[0]));
    private Label labelPassive = new Label("Cookies par seconde: " + Integer.toString(passiveCookies[0]));

    private Button[] clickUpgrade = new Button[5];
    private Button[] passiveUpgrade = new Button[5];
    private Button[] gigaUpgrade = new Button[3];
    private Button[] omegaUpgrade = new Button[3];
    private boolean[] bossDefeat = {false, false, false};

    @Override
    public void start(Stage primaryStage) {
        KeyFrame keyframe = new KeyFrame(Duration.millis(1000), event -> {
            cookies[0] = cookies[0] + passiveCookies[0];
            labelCookies.setText("Nombre de cookies : " + Integer.toString(cookies[0]));
            labelPassive.setText("Cookies par seconde: " + Integer.toString(passiveCookies[0]));
        });
        Timeline timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        stage = primaryStage;
        primaryStage.setTitle("Cookie Clicker");
        primaryStage.setResizable(false);
        primaryStage.setX(800);
        primaryStage.setY(800);
        primaryStage.setScene(clicker());
        primaryStage.show();
    }

    private Scene clicker() {
        stage.setWidth(325);
        stage.setHeight(225);
        Label clickPowerLabel = new Label("Clicker power : " + Integer.toString(clickPower));
        Button clicker = new Button("CLICK");
        moveShop = new Button("Shop");

        clickPowerLabel.setTranslateX(15);
        labelPassive.setTranslateX(15);
        labelPassive.setTranslateY(20);
        labelCookies.setTranslateX(15);
        labelCookies.setTranslateY(70);
        clicker.setTranslateX(55);
        clicker.setTranslateY(125);
        moveShop.setTranslateX(250);

        clicker.setOnAction(event -> {
            cookies[0] = clickPower + cookies[0];
            labelCookies.setText("Nombre de cookies : " + Integer.toString(cookies[0]));
        });
        moveShop.setOnAction(event -> stage.setScene(shop()));

        Group root = new Group(labelCookies, clicker, moveShop, clickPowerLabel, labelPassive);
        Scene scene = new Scene(root);
        return scene;
    }

    private Scene shop() {
        stage.setHeight(350);
        stage.setWidth(850);
        Label shop = new Label("Bienvenue dans le shop");
        moveClicker = new Button("Exit shop");

        labelCookies.setTranslateX(0);
        labelCookies.setTranslateY(20);
        moveClicker.setTranslateX(250);
        clickPowerUpgrades().setTranslateY(50);
        passiveUpgrade().setTranslateX(30);
        passiveUpgrade().setTranslateY(50);

        moveClicker.setOnAction(event -> stage.setScene(clicker()));

        Group root = new Group(shop, clickPowerUpgrades(), moveClicker, passiveUpgrade(), multiplicatifUpgrade(), omegaUpgrade(), labelCookies);
        Scene scene = new Scene(root);
        return scene;
    }

    private Group clickPowerUpgrades() {
        clickUpgrade[0] = new Button("+1 Click power (" + coutPower[0] + " cookies)");
        clickUpgrade[1] = new Button("+5 Click power (" + coutPower[1] + " cookies)");
        clickUpgrade[2] = new Button("+25 Click power (" + coutPower[2] + " cookies)");
        clickUpgrade[3] = new Button("+100 Click power (" + coutPower[3] + " cookies)");
        clickUpgrade[4] = new Button("+500 Click power (" + coutPower[4] + " cookies)");

        clickUpgrade[0].setTranslateY(50);clickUpgrade[0].setTranslateX(25);
        clickUpgrade[1].setTranslateY(100);clickUpgrade[1].setTranslateX(25);
        clickUpgrade[2].setTranslateY(150);clickUpgrade[2].setTranslateX(25);
        clickUpgrade[3].setTranslateY(200);clickUpgrade[3].setTranslateX(25);
        clickUpgrade[4].setTranslateY(250);clickUpgrade[4].setTranslateX(25);

        clickUpgrade[0].setOnAction(event -> upgrade1(1, 0));
        clickUpgrade[1].setOnAction(event -> upgrade1(5, 1));
        clickUpgrade[2].setOnAction(event -> upgrade1(25, 2));
        clickUpgrade[3].setOnAction(event -> upgrade1(100, 3));
        clickUpgrade[4].setOnAction(event -> upgrade1(500, 4));

        return new Group(clickUpgrade[0], clickUpgrade[1], clickUpgrade[2], clickUpgrade[3], clickUpgrade[4]);
    }

    private Group passiveUpgrade() {
        passiveUpgrade[0] = new Button("+1 Passive cookies (" + coutPassive[0] + " cookies)");
        passiveUpgrade[1] = new Button("+3 Passive cookies (" + coutPassive[1] + " cookies)");
        passiveUpgrade[2] = new Button("+10 Passive cookies (" + coutPassive[2] + " cookies)");
        passiveUpgrade[3] = new Button("+25 Passive cookies (" + coutPassive[3] + " cookies)");
        passiveUpgrade[4] = new Button("+50 Passive cookies (" + coutPassive[4] + " cookies)");

        passiveUpgrade[0].setTranslateY(50);passiveUpgrade[0].setTranslateX(225);
        passiveUpgrade[1].setTranslateY(100);passiveUpgrade[1].setTranslateX(225);
        passiveUpgrade[2].setTranslateY(150);passiveUpgrade[2].setTranslateX(225);
        passiveUpgrade[3].setTranslateY(200);passiveUpgrade[3].setTranslateX(225);
        passiveUpgrade[4].setTranslateY(250);passiveUpgrade[4].setTranslateX(225);

        passiveUpgrade[0].setOnAction(event -> upgrade2(1, 0));
        passiveUpgrade[1].setOnAction(event -> upgrade2(3, 1));
        passiveUpgrade[2].setOnAction(event -> upgrade2(10, 2));
        passiveUpgrade[3].setOnAction(event -> upgrade2(25, 3));
        passiveUpgrade[4].setOnAction(event -> upgrade2(50, 4));

        return new Group(passiveUpgrade[1], passiveUpgrade[2], passiveUpgrade[3], passiveUpgrade[4], passiveUpgrade[0]);
    }

    private Group multiplicatifUpgrade() {
        gigaUpgrade[0] = new Button("x1.1 Click power (" + coutGiga[0] + " cookies)");
        gigaUpgrade[1] = new Button("x1.2 Click power (" + coutGiga[1] + " cookies)");
        gigaUpgrade[2] = new Button("x1.3 Click power (" + coutGiga[2] + " cookies");

        gigaUpgrade[0].setTranslateY(50);gigaUpgrade[0].setTranslateX(425);
        gigaUpgrade[1].setTranslateY(100);gigaUpgrade[1].setTranslateX(425);
        gigaUpgrade[2].setTranslateY(150);gigaUpgrade[2].setTranslateX(425);

        gigaUpgrade[0].setOnAction(event -> upgrade3(1.1, 0));
        gigaUpgrade[1].setOnAction(event -> upgrade3(1.2, 1));
        gigaUpgrade[2].setOnAction(event -> upgrade3(1.3, 2));

        return new Group(gigaUpgrade[1], gigaUpgrade[2], gigaUpgrade[0]);
    }

    private Group omegaUpgrade() {
        omegaUpgrade[0] = new Button("Boss 1 (" + coutOmega[0] + " cookies)");
        omegaUpgrade[1] = new Button("Boss 2 (" + coutOmega[1] + " cookies)");
        omegaUpgrade[2] = new Button("Boss 3 (" + coutOmega[2] + " cookies)");

        omegaUpgrade[0].setTranslateX(625);omegaUpgrade[0].setTranslateY(50);
        omegaUpgrade[1].setTranslateX(625);omegaUpgrade[1].setTranslateY(100);
        omegaUpgrade[2].setTranslateX(625);omegaUpgrade[2].setTranslateY(150);

        for (int i=0;i<3;i++) {
            if (bossDefeat[i]) {
                omegaUpgrade[i].setTranslateX(2500);
            }
        }

        omegaUpgrade[0].setOnAction(event -> bossFight(0));
        omegaUpgrade[1].setOnAction(event -> bossFight(1));
        omegaUpgrade[2].setOnAction(event -> bossFight(2));

        return  new Group(omegaUpgrade[0], omegaUpgrade[1], omegaUpgrade[2]);
    }

    private void upgrade1(int x, int upgradeNumber) {
        if (cookies[0] >= coutPower[upgradeNumber]) {
            clickPower = x + clickPower;
            cookies[0] = cookies[0] - coutPower[upgradeNumber];
            coutPower[upgradeNumber] = (int)(coutPower[upgradeNumber] * 1.1);
            clickUpgrade[upgradeNumber].setText("+" + x + " Click power (" + coutPower[upgradeNumber] + " cookies)");
            labelCookies.setText("Nombre de cookies : " + Integer.toString(cookies[0]));
        }
    }

    private void upgrade2(int x, int upgradeNumber) {
        if (cookies[0] >= coutPower[upgradeNumber]) {
            passiveCookies[0] = x + passiveCookies[0];
            cookies[0] = cookies[0] - coutPassive[upgradeNumber];
            coutPassive[upgradeNumber] = (int)(coutPassive[upgradeNumber] * 1.1);
            passiveUpgrade[upgradeNumber].setText("+" + x + " Passive cookies (" + coutPassive[upgradeNumber] + " cookies)");
            labelCookies.setText("Nombre de cookies : " + Integer.toString(cookies[0]));
        }
    }

    private void upgrade3(double x, int upgradeNumber) {
        if (cookies[0] >= coutGiga[upgradeNumber]) {
            clickPower = (int)(x * clickPower);
            cookies[0] = cookies[0] - coutGiga[upgradeNumber];
            coutGiga[upgradeNumber] = (int)(coutGiga[upgradeNumber] * 1.1);
            gigaUpgrade[upgradeNumber].setText("+" + x + " Click power (" + coutGiga[upgradeNumber] + " cookies)");
            labelCookies.setText("Nombre de cookies : " + Integer.toString(cookies[0]));
        }
    }

    private void bossFight(int upgradeNumber) {
        if (cookies[0] >= coutOmega[upgradeNumber]) {
            cookies[0] = cookies[0] - coutOmega[upgradeNumber];
            labelCookies.setText("Nombre de cookies : " + Integer.toString(cookies[0]));
            omegaUpgrade[upgradeNumber].setTranslateX(2500);
            stage.setScene(boss(upgradeNumber));
        }
    }

    private Scene boss(int upgradeNumber) {
        stage.setWidth(150);
        stage.setHeight(150);
        Integer[] bossHP = {coutOmega[upgradeNumber]*10};
        Button attaque = new Button("Attaque");
        Label labelBossHP = new Label("Boss HP : " + Integer.toString(bossHP[0]));

        labelBossHP.setTranslateY(10);labelBossHP.setTranslateX(25);
        attaque.setTranslateY(40);attaque.setTranslateX(25);

        attaque.setOnAction(event -> {
            bossHP[0] = bossHP[0] - clickPower;
            if (bossHP[0] < 0) {
                bossHP[0] = 0;
            }
            labelBossHP.setText("Boss HP : " + Integer.toString(bossHP[0]));
            if (bossHP[0] == 0) {
                clickPower = clickPower*2;
                bossDefeat[upgradeNumber] = true;
                stage.setScene(clicker());
            }
        });

        Group root = new Group(attaque, labelBossHP);
        Scene scene = new Scene(root);
        return scene;
    }
}
