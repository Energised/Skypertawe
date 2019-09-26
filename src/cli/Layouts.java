/**
 * Layouts.java
 * @author Dan Woolsey
 *
 * Enum class to store all used types of Layouts for readability and usability
 *
 * NB: Uses the interface LayoutData for convention even though it's an empty interface
 */

package src.cli;

import com.googlecode.lanterna.gui2.*;

public enum Layouts implements LayoutData
{
    CENTERED_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                GridLayout.Alignment.CENTER,
                                                false, false, 1, 1)),

    RIGHT_PANEL_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                   GridLayout.Alignment.END,
                                                   true, false)),

    FILLED_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.FILL,
                                              GridLayout.Alignment.FILL,
                                              true, true, 1, 1)),

    BEGINNING_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING,
                                                 GridLayout.Alignment.BEGINNING,
                                                 false, false, 1, 1)),

    END_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                           GridLayout.Alignment.BEGINNING,
                                           true, true, 1, 1)),

    BOTTOM_BUTTON_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                      GridLayout.Alignment.CENTER,
                                                      true, true)),

    SEPARATOR_VERT_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                                      GridLayout.Alignment.FILL,
                                                      false, true)),

    SEPARATOR_HORI_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.FILL,
                                                      GridLayout.Alignment.CENTER,
                                                      true, false)),

    TITLE_LAYOUT(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                                             GridLayout.Alignment.CENTER,
                                             true,
                                             false)),

    VERT_FILL(GridLayout.createLayoutData(GridLayout.Alignment.FILL,
                                          GridLayout.Alignment.FILL,
                                          false, true));

    public final LayoutData data;

    private Layouts(LayoutData l)
    {
        this.data = l;
    }
}
