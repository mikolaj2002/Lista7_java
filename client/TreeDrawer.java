import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Klasa odpowiada za rysowanie drzewa zadanego typu T.
 */
public class TreeDrawer<T extends Comparable<T>>
{
    private final int width;
    private final int stepY = 50;
    private final Pane drawablePane;
    private final String text;
    private final BinaryTree<T> tree;

    /**
     * Konstruktor klasy TreeDrawer.
     * @param width Szerokość okna.
     * @param drawablePane Pane, na którym będzie narysowane drzewo.
     * @param text Elementy drzewa oddzielone spacjami.
     */
    public TreeDrawer(int width, Pane drawablePane, String text)
    {
        this.width = width;
        this.drawablePane = drawablePane;
        this.text = text;
        tree = new BinaryTree<T>();
    }

    /**
     * Funkcja rysuje drzewo.
     */
    public void draw()
    {
        for (String element : text.split(" "))
            if (!element.equals("()") && !element.equals(""))
                tree.insert(getVal(element));
        
        
        recDraw(tree.getRoot(), 0, width - stepY, (width - stepY) / 2, 0);
    }

    /**
     * Funkcja konwersuje łańcuch znaków na zadnay typ <T>.
     * @param str Łańcuch znaków.
     * @return Przekonwertowana wartośc typu <T>.
     */
    public T getVal(String str)
    {
        return null;
    }

    private void recDraw(Node<T> node, int beginX, int lastX, int x, int y)
    {
        if (node == null)
            return;
        
        Text textBox = new Text(x - 10, y, node.getVal().toString());
        textBox.setFont(Font.font ("Verdana", 16));
        
        Circle circle = new Circle(x, y, stepY / 2, Color.GREEN);
        circle.setStrokeWidth(3);
        circle.setStroke(Color.RED);

        circle.setOnMouseClicked((event) -> {
            drawablePane.getChildren().removeAll(circle, textBox);
            drawablePane.getChildren().addAll(circle, textBox);
        });
        
        if (node.getLeft() != null)
            drawablePane.getChildren().add(new Line(x, y, (x + beginX) / 2, y + stepY));
        if (node.getRight() != null)
            drawablePane.getChildren().add(new Line(x, y, (x + lastX) / 2, y + stepY));

        drawablePane.getChildren().addAll(circle, textBox);

        recDraw(node.getLeft(), beginX, x, (x + beginX) / 2, y + stepY);
        recDraw(node.getRight(), x, lastX, (x + lastX) / 2, y + stepY);
    }
}
