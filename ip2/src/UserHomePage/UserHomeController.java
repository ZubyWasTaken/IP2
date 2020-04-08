/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserHomePage;

import CasualPlay.CasualGameSelector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import CompetitivePlay.CompetitivePlay;

import LoginRegister.Login;
import PreviousScore.PreviousScore;
import ScoreHistory.ScoreHistory;
import ip2.SwitchWindow;
import ip2.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class UserHomeController implements Initializable {

    User currentUser;
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label userLabel;
    @FXML
    private JFXButton competitivePlayButton;
    @FXML
    private JFXButton casualPlayButton, leaderboard,previousScoreButton;
    @FXML
    private Label casuallabel1;

    @FXML
    private Label casualLabel3;

    @FXML
    private Label compLabel1;

    @FXML
    private Label compLabel3;
    @FXML
    private Button logout;
    @FXML
    private Button casualInfo;

    @FXML
    private Button compInfo;

    @FXML
    private Label compLabel2;

    @FXML
    private Label casualLabel2;

    @FXML
    private Pane pane;
    private Stage myStage;

    @FXML
    public void setStage(Stage stage) {
        myStage = stage;
    }

    @FXML
    public void previousScore(ActionEvent event) throws IOException {
        SwitchWindow.switchWindow((Stage) leaderboard.getScene().getWindow(), new PreviousScore(currentUser));
    }
    
        @FXML
    public void scoreHistory(ActionEvent event) throws IOException {
        SwitchWindow.switchWindow((Stage) previousScoreButton.getScene().getWindow(), new ScoreHistory(currentUser));
    }

//    @FXML
//    private Button highScoreButton;
//
//    @FXML
//    public void highscoreButton(ActionEvent event) throws IOException {     
//        //System.out.println(currentUser);
//
//         SwitchWindow.switchWindow((Stage) leaderboard.getScene().getWindow(), new PreviousScore(currentUser));
//
//    }
    @FXML
    public void casualPlay(ActionEvent event) throws IOException {
        SwitchWindow.switchWindow((Stage) casualPlayButton.getScene().getWindow(), new CasualGameSelector(currentUser));

    }

    @FXML
    public void logoutButton(ActionEvent event) throws IOException {
        SwitchWindow.switchWindow((Stage) logout.getScene().getWindow(), new Login());

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // changed from lambra to anonymous inner class
        // doesn't give null pointer error anymore.
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                userLabel.setText(currentUser.getFirstname());
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserHomePage/pullout.fxml"));
                    VBox box = loader.load();
                    drawer.setSidePane(box);
                    drawerController controller = loader.getController();

                    controller.setData(currentUser);

                    HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
                    transition.setRate(-1);
                    hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        transition.setRate(transition.getRate() * -1);
                        transition.play();

                        if (drawer.isOpened()) {
                            drawer.close();
                            drawer.setDisable(true);
                        } else {
                            drawer.open();
                            drawer.setDisable(false);

                        }
                    }
                    );
                } catch (IOException ex) {
                    Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        drawer.setDisable(true);
        casuallabel1.setVisible(false);
        casualLabel2.setVisible(false);
        casualLabel3.setVisible(false);
        compLabel1.setVisible(false);
        compLabel2.setVisible(false);
        compLabel3.setVisible(false);

        handleMouseEvents();

    }

    public void setData(User user) {
        currentUser = user;

    }

    @FXML
    private void competitvePlay(ActionEvent event) {
        SwitchWindow.switchWindow((Stage) competitivePlayButton.getScene().getWindow(), new CompetitivePlay(currentUser));
    }

    @FXML
    public void handleMouseEvents() {
        hamburger.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                myStage.getScene().setCursor(Cursor.HAND);
            }
        });

        hamburger.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                myStage.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        casualPlayButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                myStage.getScene().setCursor(Cursor.HAND);
            }
        });

        casualPlayButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                myStage.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        competitivePlayButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                myStage.getScene().setCursor(Cursor.HAND);
            }
        });

        competitivePlayButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                myStage.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        logout.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                myStage.getScene().setCursor(Cursor.HAND);
            }
        });

        logout.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                myStage.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        casualInfo.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                casuallabel1.setVisible(true);
                casualLabel2.setVisible(true);
                casualLabel3.setVisible(true);
            }
        });

        casualInfo.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                casuallabel1.setVisible(false);
                casualLabel2.setVisible(false);
                casualLabel3.setVisible(false);
            }
        });

        compInfo.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                compLabel1.setVisible(true);
                compLabel2.setVisible(true);
                compLabel3.setVisible(true);
            }
        });

        compInfo.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                compLabel1.setVisible(false);
                compLabel2.setVisible(false);
                compLabel3.setVisible(false);
            }
        });
    }

}
