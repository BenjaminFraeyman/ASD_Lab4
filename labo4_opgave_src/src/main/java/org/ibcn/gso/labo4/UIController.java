package org.ibcn.gso.labo4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.ibcn.gso.labo3.util.Pixel;
import org.ibcn.gso.labo4.api.EffectType;
import org.ibcn.gso.labo4.api.EngineFacade;
import org.ibcn.gso.labo4.api.ImageObserver;
import org.ibcn.gso.labo4.impl.EngineImpl;

public class UIController implements Initializable, ImageObserver {

    @FXML
    private BorderPane root;
    @FXML
    private HBox toolbar;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveAsButton;
    @FXML
    private ImageView imageContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setAutoResizeImage();

        EngineFacade facade = new EngineImpl();
        facade.register(this);

        loadButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Image");
            setExtensionFilter(chooser);
            File sourceFile = chooser.showOpenDialog(toolbar.getScene().getWindow());
            if (sourceFile != null) {
                facade.load(sourceFile);
            }
            toolbar.getChildren().filtered(node -> node instanceof ToggleButton).forEach(node -> ((ToggleButton) node).setSelected(false));
        });
        

        saveAsButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setInitialFileName("output.png");
            chooser.setTitle("Save Image As");
            setExtensionFilter(chooser);
            File targetFile = chooser.showSaveDialog(toolbar.getScene().getWindow());
            if (targetFile != null) {
                facade.saveAs(targetFile);
            }
        });
        saveAsButton.disableProperty().bind(imageContainer.imageProperty().isNull());

        Arrays.stream(EffectType.values()).forEach(effectType -> {
            ToggleButton toggleButton = new ToggleButton(effectType.getLabel());
            toggleButton.setOnAction(e -> facade.toggle(effectType));
            toggleButton.disableProperty().bind(imageContainer.imageProperty().isNull());
            toolbar.getChildren().add(toggleButton);
        });
    }

    @Override
    public void updated(Pixel[][] image) {
        imageContainer.setImage(convertToImage(image));
    }

    private Image convertToImage(Pixel[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Pixel p = pixels[row][col];
                image.setRGB(col, row, new Color(p.r, p.g, p.b).getRGB());
            }
        }

        WritableImage wImage = new WritableImage(width, height);
        SwingFXUtils.toFXImage(image, wImage);
        return wImage;
    }

    private void setAutoResizeImage() {
        imageContainer.fitWidthProperty().bind(root.widthProperty());
        imageContainer.fitHeightProperty().bind(root.heightProperty());
    }

    private void setExtensionFilter(FileChooser chooser) {
        chooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
    }

}
