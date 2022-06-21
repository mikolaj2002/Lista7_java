import javafx.scene.layout.Pane;

/**
 * Klasa odpowiada za rysowanie drzewa zadanego typu Integer.
 */
public class TreeDrawerInteger extends TreeDrawer<Integer>
{
    /**
     * Konstruktor klasy TreeDrawer.
     * @param width Szerokość okna.
     * @param drawablePane Pane, na którym będzie narysowane drzewo.
     * @param text Elementy drzewa oddzielone spacjami.
     */
    public TreeDrawerInteger(int width, Pane drawablePane, String text)
    {
        super(width, drawablePane, text);
    }

    /**
     * Funkcja konwersuje łańcuch znaków na zadnay typ Integer.
     * @param str Łańcuch znaków.
     * @return Przekonwertowana wartośc typu Integer.
     */
    @Override
    public Integer getVal(String str)
    {
        return Integer.parseInt(str);
    }
}
