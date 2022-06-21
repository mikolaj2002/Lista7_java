import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Klasa reprezentująca okno aplikacji.
 */
public class Window
{
    private final Stage primaryStage;
    private final VBox mainPane;
    private final GridPane gridPane;
    private final Label promptLabel;
    private final ChoiceBox<String> typeChoiceBox;
    private final TextField commandField;
    private final Button createTreeButton;
    private final Button insertButton;
    private final Button searchButton;
    private final Button deleteButton;
    private final Button drawButton;
    private final Button exitButton;
    private final ScrollPane scrollPane;
    private final Pane drawablePane;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Konstruktor klasy Window.
     * @param primaryStage Referencja na Stage'a.
     */
    public Window(Stage primaryStage)
    {
        this.primaryStage = primaryStage;

        drawablePane = new Pane();

        promptLabel = new Label("Choose tree type");

        typeChoiceBox = new ChoiceBox<String>();
        typeChoiceBox.setItems(FXCollections.observableArrayList("Integer", "Double", "String"));
        typeChoiceBox.setValue("Integer");

        commandField = new TextField();

        createTreeButton = new Button("Create tree");
        createTreeButton.setOnAction((event) -> {
            drawablePane.getChildren().clear();
            startConnection();
        });

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(0, 0, 20, 0));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.add(typeChoiceBox, 0, 0);
        gridPane.add(createTreeButton, 0, 1);

        insertButton = new Button("Insert");
        searchButton = new Button("Search");
        deleteButton = new Button("Delete");
        drawButton = new Button("Draw");
        exitButton = new Button("Exit");

        mainPane = new VBox();
        mainPane.setSpacing(15);
        mainPane.setPadding(new Insets(10));
        mainPane.getChildren().addAll(promptLabel, gridPane, drawablePane);

        scrollPane = new ScrollPane();
        scrollPane.setContent(mainPane);
    }

    /**
     * Funckja wyświetla okno i ustawia akcję przycisków.
     */
    public void start()
    {
        primaryStage.setTitle("Lista 7");
        primaryStage.setScene(new Scene(scrollPane, 850, 600));
        primaryStage.show();

        primaryStage.getScene().getWindow().setOnCloseRequest((event) -> {
            if (socket == null)
                return;
            out.println("exit");
            try { socket.close(); }
            catch (final IOException ex)
            { System.out.println("I/O error: " + ex.getMessage()); }
        });
    }

    private void startConnection()
    {
        try 
        {
            socket = new Socket("localhost", 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(typeChoiceBox.getValue());
        }
        catch (final UnknownHostException ex)
        {
            Text errorText = new Text("Server not found: " + ex.getMessage());
            drawablePane.getChildren().clear();
            drawablePane.getChildren().add(errorText);
            return;
        }
        catch (final IOException ex)
        {
            Text errorText = new Text("I/O error: " + ex.getMessage());
            drawablePane.getChildren().clear();
            drawablePane.getChildren().add(errorText);
            return;
        }

        promptLabel.setText("Choose action");
        gridPane.getChildren().removeAll(typeChoiceBox, createTreeButton);
        gridPane.add(commandField, 0, 0);
        gridPane.add(insertButton, 1, 1);
        gridPane.add(deleteButton, 2, 1);
        gridPane.add(searchButton, 3, 1);
        gridPane.add(drawButton, 4, 1);
        gridPane.add(exitButton, 5, 1);

        insertButton.setOnAction((event) -> {
            if (!commandField.getText().trim().equals(""))
                executeCommand("insert " + commandField.getText().trim());
        });
        deleteButton.setOnAction((event) -> {
            if (!commandField.getText().trim().equals(""))
                executeCommand("delete " + commandField.getText().trim());
        });
        searchButton.setOnAction((event) -> {
            if (!commandField.getText().trim().equals(""))
                executeCommand("search " + commandField.getText().trim());
        });
        drawButton.setOnAction((event) -> {
            executeCommand("draw " + commandField.getText().trim());
        });
        exitButton.setOnAction((event) -> {
            executeCommand("exit");
        });
    }

    private void executeCommand(String command)
    {
        commandField.setText("");

        out.println(command);

        if (command.equals("exit"))
        {
            try { socket.close(); }
            catch (final IOException ex)
            { System.out.println("I/O error: " + ex.getMessage()); }
            System.exit(0);
        }

        String output = null;
        try { output = in.readLine(); }
        catch (final IOException ex)
        {
            Text errorText = new Text("I/O error: " + ex.getMessage());
            drawablePane.getChildren().clear();
            drawablePane.getChildren().add(errorText);
            return;
        }

        drawablePane.getChildren().clear();

        if (output.equals("true") || output.equals("false"))
        {
            Text isElementText = new Text();
            isElementText.setText("Element " +
                (Boolean.parseBoolean(output) ? "" : "not ") + "found");
            drawablePane.getChildren().add(isElementText);
            return;
        }

        else if (output.equals("Unrecognised command") || output.equals("Conversion error"))
        {
            Text errorText = new Text();
            errorText.setText(output);
            drawablePane.getChildren().add(errorText);
            return;
        }

        else if (output.equals("()"))
        {
            Text emptyTree = new Text();
            emptyTree.setText("Tree is empty");
            drawablePane.getChildren().add(emptyTree);
            return;
        }

        switch (typeChoiceBox.getValue())
        {
        case "Integer":
            TreeDrawerInteger treeDrawerInteger = new TreeDrawerInteger(
                (int)primaryStage.getWidth(), drawablePane, output);
            treeDrawerInteger.draw();
            break;
        case "Double":
            TreeDrawerDouble treeDrawerDouble = new TreeDrawerDouble(
                (int)primaryStage.getWidth(), drawablePane, output);
            treeDrawerDouble.draw();
            break;
        case "String":
            TreeDrawerString treeDrawerString = new TreeDrawerString(
                (int)primaryStage.getWidth(), drawablePane, output);
            treeDrawerString.draw();
            break;
        }    
    }
}
