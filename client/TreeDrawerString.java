import javafx.scene.layout.Pane;

/**
 * Klasa odpowiada za rysowanie drzewa zadanego typu String.
 */
public class TreeDrawerString extends TreeDrawer<String>
{
    /**
     * Konstruktor klasy TreeDrawer.
     * @param width Szerokość okna.
     * @param drawablePane Pane, na którym będzie narysowane drzewo.
     * @param text Elementy drzewa oddzielone spacjami.
     */
    public TreeDrawerString(int width, Pane drawablePane, String text)
    {
        super(width, drawablePane, text);
    }

    /**
     * Funkcja konwersuje łańcuch znaków na zadnay typ String.
     * @param str Łańcuch znaków.
     * @return Przekonwertowana wartośc typu String.
     */
    @Override
    public String getVal(String str)
    {
        return str;
    }
}
