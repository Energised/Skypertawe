/**
 * HomeWindow.java
 * @author Dan Woolsey
 *
 * Subclassed version of Home
 */

package src.cli;

import src.obj.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;

import java.util.Arrays;

public class HomeWindow extends AbstractWindow
{
    public HomeWindow(Screen s, Account ac)
    {
        setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK));
        setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));
        Panel content = new Panel();
        setComponent(content);
    }
}
