package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {


    @FXML private TreeView<String> treeView;

    @FXML private TableView<FCB> tableView;
    @FXML private TableColumn<FCB, CheckBox> columnChoice;
    @FXML private TableColumn<FCB, String> columnName;
    @FXML private TableColumn<FCB, Integer> columnSize;
    @FXML private TableColumn<FCB, String> columnTime;

    //根目录图标
    private final Node rootIcon = new ImageView(
            new Image(getClass().getResourceAsStream("/images/003-folder-6.png"))
    );

    //目录树
    private DirectoryTree directoryTree = new DirectoryTree();

    //当前路径


    public void initialize() {

        //--------------------------------Tree View-------------------------------------
        //初始化目录树
        TreeItem<String> rootItem = new TreeItem<String>(
                directoryTree.getRoot().getName(), rootIcon
        );
        //根目录展开
        rootItem.setExpanded(true);
        //添加子目录
        for (int i = 1; i < directoryTree.getDirectoryTree().size(); i++) {
            if(directoryTree.getDirectoryTree().get(i).getType() == FCB.Type.folder) {
                TreeItem<String> item = new TreeItem<String> (
                        directoryTree.getDirectoryTree().get(i).getName(), new ImageView(
                        new Image(getClass().getResourceAsStream("/images/004-folder-5.png"))
                )
                );
                rootItem.getChildren().add(item);
                item.setExpanded(true);
            }
        }
        //TreeView添加根目录
        treeView.setRoot(rootItem);

        //--------------------------------Table View-------------------------------------


//        columnChoice.setCellValueFactory(cellData -> cellData.getValue().);
        columnName.setCellValueFactory(new PropertyValueFactory<FCB, String>("name"));
//        columnSize.setCellValueFactory(new PropertyValueFactory<FCB, Integer>("size"));
//        columnTime.setCellValueFactory(new PropertyValueFactory<FCB, String>("modifyTime"));


//        columnSize.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSize()));
//        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        ObservableList<FCB> list = FXCollections.observableArrayList();

        for (int i = 0; i < directoryTree.getDirectoryTree().size(); i++) {
            list.add(directoryTree.getDirectoryTree().get(i));
        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getName());
//        }
//        columnName.setCellValueFactory(cellData -> (new SimpleStringProperty(cellData.getValue().name)));


        // FIXME: 2017/6/8 tableview加载不了数据, 可能因为Property?
//        tableView.setItems(list);

    }
}
