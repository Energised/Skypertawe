/**
 * HomeWindow.java
 * @author Dan Woolsey
 *
 * Subclassed version of Home
 *
 * CURRENT STATE OF FRIENDING:
 *  (-) when you friend someone, you also get a request, but you wont be able to accept it
 *  (-) once you accept a request, the search box wont update until next login
 *  (-) the system will stop you from resending friend requests
 */

package src.cli;

import src.*;
import src.obj.*;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.graphics.*;

import java.util.Arrays;
import java.util.ArrayList;

public class HomeWindow extends AbstractWindow
{

    private Screen s;
    private TerminalSize size;
    private Account ac;

    public HomeWindow(Screen s, Account ac) throws Exception
    {
        this.s = s;
        this.ac = ac;
        this.size = s.getTerminalSize();
        this.setTheme();
        Panel content = this.buildMainPanel();
        setComponent(content);
    }

    public void setTheme()
    {
        this.setTheme(new SimpleTheme(TextColor.ANSI.GREEN, TextColor.ANSI.BLACK));
        this.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));
    }

    public Panel buildMainPanel() throws Exception
    {
        Panel contentPanel = new Panel(new GridLayout(1));
        Panel topPanel = new Panel(new GridLayout(3));
        Panel bottomPanel = this.buildBottomPanel();
        Panel topLeftPanel = this.buildTopLeftPanel();
        // get preferred size of top left panel
        // calculate space remaining, use to size up right panel
        TerminalSize ps = topLeftPanel.getPreferredSize();
        Panel topRightPanel = this.buildTopRightPanel(ps);

        Separator sep = new Separator(Direction.VERTICAL);
        sep.setLayoutData(Layouts.SEPARATOR_VERT_LAYOUT.data);

        topPanel.addComponent(topLeftPanel);
        topPanel.addComponent(sep);
        topPanel.addComponent(topRightPanel);
        // NB: not sure why its in this position but will try to handle later
        //topPanel.addComponent(new EmptySpace(TextColor.ANSI.WHITE));

        Separator mainSep = new Separator(Direction.HORIZONTAL);
        mainSep.setLayoutData(Layouts.SEPARATOR_HORI_LAYOUT.data);

        contentPanel.addComponent(topPanel);
        contentPanel.addComponent(mainSep);
        contentPanel.addComponent(bottomPanel);

        return contentPanel;
    }

    public Panel buildTopLeftPanel()
    {
        Panel tlp = new Panel(new GridLayout(1).setVerticalSpacing(1));

        Label b = new Label(Art.banner);
        Label fl = new Label("~ Friends List ~");
        Label fr = new Label("~ Friend Requests ~");

        // insert names of friends
        ArrayList<Account> friends = Main.graph.getFriends(ac);
        ArrayList<String> fnames = new ArrayList<String>();
        for(Account a : friends)
        {
            fnames.add(a.getUsername());
        }

        ComboBox<String> flb = new ComboBox<String>(fnames);
        flb.setReadOnly(true);
        flb.setPreferredSize(new TerminalSize(20, 3)); // only using for the vertical fill (3)

        // insert names of friend requests
        ArrayList<Account> requests = Main.graph.getRequests(ac);
        ArrayList<String> rnames = new ArrayList<String>();
        for(Account a : requests)
        {
            rnames.add(a.getUsername());
        }

        ComboBox<String> frb = new ComboBox<String>(rnames);
        frb.setReadOnly(true);
        frb.setPreferredSize(new TerminalSize(20, 3)); // as above

        // insert new record into graph
        // update graph
        // remove item from list
        Button acceptRequest = new Button("Accept Selected Request", new Runnable()
        {
            @Override
            public void run()
            {
                String rec = frb.getSelectedItem();
                Account recipient = Main.tree.searchBeginningWith(rec).get(0);

                ArrayList<Account> request = new ArrayList<Account>();
                request.add(HomeWindow.this.ac);
                request.add(recipient);

                int count;
                try
                {
                    count = Main.graph.addRecord(request);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    count = 0;
                }
                if(count == 1)
                {
                    MessageDialogButton mdb = MessageDialog.showMessageDialog(Main.w,
                                            "Sent","Request from " + rec + " accepted!",
                                            MessageDialogButton.Close);
                    flb.addItem(rec); // update local friends list
                    frb.removeItem(rec); // update local requests list
                }
                else
                {
                    // message of failure
                }
            }
        });

        fl.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        fr.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        flb.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        frb.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        acceptRequest.setLayoutData(Layouts.CENTERED_LAYOUT.data);

        tlp.addComponent(b);
        tlp.addComponent(fl);
        tlp.addComponent(flb);
        tlp.addComponent(fr);
        tlp.addComponent(frb);
        tlp.addComponent(acceptRequest);

        return tlp;
    }

    public Panel buildTopRightPanel(TerminalSize ps)
    {
        // strip away border and separator size = 12
        int rough_cols = this.size.getColumns() - ps.getColumns();
        //String si = "R: " + this.s.getTerminalSize().getRows() + "\nC: " + this.size.getColumns();
        String rep = "~".repeat(rough_cols-12); // 12 is the right size

        Panel trp = new Panel(new GridLayout(1));

        Label uname = new Label(ac.getUsername());
        Label fname = new Label("|" + ac.getFirstName() + " " + ac.getSurname());
        Label mobNo = new Label("|" + ac.getMobnumber());
        Label bday = new Label("|" + ac.getBirthDate());
        Label city = new Label("|" + ac.getCity());

        Label searchLabel = new Label("~ Search all users ~");

        UserSearchBox searchBox = new UserSearchBox(ac);

        uname.addStyle(SGR.BOLD);
        uname.addStyle(SGR.UNDERLINE);

        //fname.setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.End));
        //mobNo.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        //bday.setLayoutData(Layouts.CENTERED_LAYOUT.data);
        //city.setLayoutData(Layouts.CENTERED_LAYOUT.data);

        // hardcoding these tildas until i can find a better way to deal with this
        trp.addComponent(new Label(rep));
        trp.addComponent(uname);
        trp.addComponent(new EmptySpace());
        trp.addComponent(fname);
        trp.addComponent(mobNo);
        trp.addComponent(bday);
        trp.addComponent(city);
        trp.addComponent(new Label(rep));
        trp.addComponent(new EmptySpace());
        trp.addComponent(searchLabel, Layouts.CENTERED_LAYOUT.data);
        trp.addComponent(searchBox, Layouts.RIGHT_PANEL_LAYOUT.data);
        // has to be a reference to the UserCheckBoxList obj in searchBox
        trp.addComponent(searchBox.searchedUsers, Layouts.CENTERED_LAYOUT.data);

        return trp;
    }

    public Panel buildBottomPanel() throws Exception
    {
        Panel bp = new Panel(new GridLayout(3).setHorizontalSpacing(this.size.getColumns()/3));
        // should change the spacing to terminal length
        //bp.setSize(new TerminalSize(this.screen.getColumns(), 1)); // to keep the height as 1 unit

        Button msg = new Button("Messages", new Runnable()
        {
            @Override
            public void run()
            {
                MessageWindow m = null;
                try
                {
                    m = new MessageWindow(HomeWindow.this.s, HomeWindow.this.ac);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                HomeWindow.this.getTextGUI().addWindowAndWait(m);
            }
        });

        Button edit = new Button("Edit", new Runnable()
        {
            @Override
            public void run()
            {
                EditWindow e = new EditWindow(HomeWindow.this.s, HomeWindow.this.ac);
                HomeWindow.this.getTextGUI().addWindowAndWait(e);
            }
        });

        Button close = new Button("Close", new Runnable(){
            @Override
            public void run()
            {
                HomeWindow.this.close();
            }
        });

        Panel lButton = new Panel(new GridLayout(1));
        Panel mButton = new Panel(new GridLayout(1));
        Panel rButton = new Panel(new GridLayout(1));

        lButton.addComponent(msg);
        mButton.addComponent(edit);
        rButton.addComponent(close);

        bp.addComponent(lButton, Layouts.BOTTOM_BUTTON_LAYOUT.data);
        bp.addComponent(mButton, Layouts.BOTTOM_BUTTON_LAYOUT.data);
        bp.addComponent(rButton, Layouts.BOTTOM_BUTTON_LAYOUT.data);

        return bp;
    }
}
