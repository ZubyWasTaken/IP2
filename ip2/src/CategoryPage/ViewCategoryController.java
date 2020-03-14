/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CategoryPage;

import QuestionPage.EditPage;
import SQL.SQLHandler;
import com.jfoenix.controls.JFXButton;
import ip2.Category;
import ip2.Shaker;
import ip2.SwitchWindow;
import ip2.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author erino
 */
public class ViewCategoryController implements Initializable {

    User currentUser;

    @FXML
    private TableView<Category> categoryTable;

    @FXML
    private TableColumn<Category, String> name;

    ObservableList<Category> data = FXCollections.observableArrayList();
    @FXML
    private Button deleteButton;
    
    
    @FXML
    private JFXButton editCat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            Connection conn = SQLHandler.getConn();
            ResultSet rs = conn.createStatement().executeQuery("Select * from Categories");
            while (rs.next()) {
                data.add(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        name.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
        categoryTable.setItems(data);

    }

    @FXML
    private String getTablePos() {
        TablePosition pos = (TablePosition) categoryTable.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        Category item = categoryTable.getItems().get(index);
        String categoryName = (String) name.getCellObservableValue(item).getValue();

        return categoryName;
    }

    @FXML
    private void editCategoryButton(ActionEvent event) throws IOException, SQLException {
        try {
             ArrayList<String> allCategories = new ArrayList<>();
            String catName = getTablePos();

            SQLHandler sql = new SQLHandler();
            allCategories = sql.getAllCategories();

            Category currentCategory = new Category(catName);
            currentCategory = search(catName);

            SwitchWindow.switchWindow((Stage) editCat.getScene().getWindow(), new EditCategory(currentCategory));
        } catch (Exception e) {
            System.out.print("Select a category to edit");
        }
    }

    @FXML
    private void deleteCategory(ActionEvent event) throws SQLException, IOException {

        String catname = getTablePos();
        ArrayList<String> allCategories = new ArrayList<>();
        SQLHandler sql = new SQLHandler();
        allCategories = sql.getAllCategories();
        
        Category currentCategory = search(catname);
        currentCategory.deleteCategory(currentCategory);
        Parent root;
        root = FXMLLoader.load(getClass().getResource("ViewCategoryTable.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    @FXML
    private void addCategoryButton(ActionEvent event) throws SQLException, IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    @FXML
    public Category search(String name) throws SQLException, IOException {
        SQLHandler sql = new SQLHandler();
        List categoryInfo = sql.searchCategoriesTable(name);

        int categoryId = (int) categoryInfo.get(0);
        String categoryName = (String) categoryInfo.get(1);
        Category currentCategory = new Category(categoryId, categoryName);
        
        return currentCategory;
        

    }

    @FXML
    public void homeButton(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/AdminHomePage/AdminHome.fxml"));

        Scene scene = new Scene(root);
        Stage reg = new Stage(StageStyle.DECORATED);
        reg.setTitle("Home");
        reg.setScene(scene);

        reg.show();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    private void deleteCategoryFailed() {
        Shaker shake = new Shaker(deleteButton);
        shake.shake();
        categoryTable.requestFocus();
    }

    public void setData(User user) {
        currentUser = user;
    }
}
