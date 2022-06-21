import javafx.scene.layout.Pane;

/**
 * Klasa odpowiada za rysowanie drzewa zadanego typu Double.
 */
public class TreeDrawerDouble extends TreeDrawer<Double>
{
    /**
     * Konstruktor klasy TreeDrawer.
     * @param width Szerokość okna.
     * @param drawablePane Pane, na którym będzie narysowane drzewo.
     * @param text Elementy drzewa oddzielone spacjami.
     */
    public TreeDrawerDouble(int width, Pane drawablePane, String text)
    {
        super(width, drawablePane, text);
    }

    /**
     * Funkcja konwersuje łańcuch znaków na zadnay typ Double.
     * @param str Łańcuch znaków.
     * @return Przekonwertowana wartośc typu Double.
     */
    @Override
    public Double getVal(String str)
    {
        return Double.parseDouble(str);
    }
}
