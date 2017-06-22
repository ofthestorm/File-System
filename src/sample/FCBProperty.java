package sample;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * Created by keke on 2017/6/16.
 */
public class FCBProperty {
    private StringProperty name;
    private StringProperty size;
    private StringProperty time;
//    private boolean choice;
    public MyCheckBox checkbox = new MyCheckBox();
    private FCB fcb;
    private ObjectProperty<ImageView> imageView;
    private Button open = new Button("open");
    private Button delete = new Button("delete");
    private ObjectProperty<Button> openButton = new SimpleObjectProperty<Button>(open);
    private ObjectProperty<Button> deleteButton = new SimpleObjectProperty<Button>(delete);


    public FCBProperty(FCB fcb) {
        this.fcb = fcb;
        this.name = new SimpleStringProperty(fcb.getName());
        this.size = new SimpleStringProperty(Integer.toString(fcb.getSize()));
        this.time = new SimpleStringProperty(fcb.getModifyTime());
        if(fcb.getType() == FCB.Type.document) {
            imageView = new SimpleObjectProperty<ImageView>(new ImageView(new Image(getClass().getResourceAsStream("/images/file.png"))));
        } else {
            imageView = new SimpleObjectProperty<ImageView>(new ImageView(new Image(getClass().getResourceAsStream("/images/004-folder-5.png"))));
        }
//        this.choice = false;
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                System.out.println("open" + fcb.name);
                SystemController.currentFCB = fcb;
                if(fcb.getType() == FCB.Type.document) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("file.fxml"));
                        Stage stage = new Stage();
                        Main.stages.add(stage);
                        stage.setTitle("File");
                        stage.setScene(new Scene(root, 800, 500));
                        stage.show();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    SystemController.currentDirectory = fcb;
                    SystemController.updateFileList();
                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



            }

        });

    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public ObjectProperty<ImageView> imageViewProperty() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView.set(imageView);
    }

    public Button getOpenButton() {
        return openButton.get();
    }

    public ObjectProperty<Button> openButtonProperty() {
        return openButton;
    }

    public void setOpenButton(Button openButton) {
        this.openButton.set(openButton);
    }

    public Button getDeleteButton() {
        return deleteButton.get();
    }

    public ObjectProperty<Button> deleteButtonProperty() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton.set(deleteButton);
    }

    public FCB getFcb() {
        return fcb;
    }
}
