import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import java.util.ArrayList;

public class CreateAccountGUI extends GUI{

	public CreateAccountGUI() throws Exception{

		super();

		setTitle("Create an account");
		setSize(600, 400);
		setBackground(Color.CYAN);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		String[] labels = {"Username: ", "First Name: ", "Surname: ",
                            "Mobile Number: ", "Date Of Birth: ", "City: ",
                            "Profile Pic (e.g. pic.png): "};
		int input = labels.length;

        ArrayList<JTextField> fields = new ArrayList<JTextField>();

		JPanel create = new JPanel(new SpringLayout());
		for (int i = 0; i < input; i++) {
			JLabel para = new JLabel(labels[i], JLabel.TRAILING);
			create.add(para);
			JTextField form = new JTextField(10);
            fields.add(form);
			para.setLabelFor(form);
			create.add(form);
			super.add(create);
		}
		JButton fin = new JButton("Create");
		fin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent sendMessage){
                try
                {
                    String username = fields.get(0).getText();
                    String firstname = fields.get(1).getText();
                    String surname = fields.get(2).getText();
                    String mobnum = fields.get(3).getText();
                    String dob = fields.get(4).getText();
                    String city = fields.get(5).getText();
                    String imgpath = fields.get(6).getText();
                    Account acc = new Account(username, firstname, surname,
                                          mobnum, dob, city, 0, null, imgpath);
                    ReadWriteAccount rwa = new ReadWriteAccount("data.db");
                    rwa.write(acc);
                    // call homeGUI and dispose this screen
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
			}
		});
		super.add(fin, BorderLayout.SOUTH);
		CreateAccountGUI.makeForm(create,
				input, 2,
				6, 6,
				20, 20);

        setVisible(true);

	}

	private static SpringLayout.Constraints getConstraintsForCell(
			int row, int col,
			Container parent,
			int cols) throws Exception{
		SpringLayout layout = (SpringLayout) parent.getLayout();
		Component c = parent.getComponent(row * cols + col);
		return layout.getConstraints(c);
	}

	public static void makeForm(Container parent,
			int rows, int cols,
			int initialX, int initialY,
			int xPad, int yPad) throws Exception {
		SpringLayout layout;
		try {
			layout = (SpringLayout)parent.getLayout();
		} catch (ClassCastException exc) {
			System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
			return;
		}

		//Align all cells in each column and make them the same width.
		Spring x = Spring.constant(initialX);
		for (int c = 0; c < cols; c++) {
			Spring width = Spring.constant(0);
			for (int r = 0; r < rows; r++) {
				width = Spring.max(width,
						getConstraintsForCell(r, c, parent, cols).
						getWidth());
			}
			for (int r = 0; r < rows; r++) {
				SpringLayout.Constraints constraints =
						getConstraintsForCell(r, c, parent, cols);
				constraints.setX(x);
				constraints.setWidth(width);
			}
			x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
		}

		//Align all cells in each row and make them the same height.
		Spring y = Spring.constant(initialY);
		for (int r = 0; r < rows; r++) {
			Spring height = Spring.constant(0);
			for (int c = 0; c < cols; c++) {
				height = Spring.max(height,
						getConstraintsForCell(r, c, parent, cols).
						getHeight());
			}
			for (int c = 0; c < cols; c++) {
				SpringLayout.Constraints constraints =
						getConstraintsForCell(r, c, parent, cols);
				constraints.setY(y);
				constraints.setHeight(height);
			}
			y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
		}

		//Set the parent's size.
		SpringLayout.Constraints pCons = layout.getConstraints(parent);
		pCons.setConstraint(SpringLayout.SOUTH, y);
		pCons.setConstraint(SpringLayout.EAST, x);
	}
	public static void main(String[] args) throws Exception {
		CreateAccountGUI b = new CreateAccountGUI();
		b.setVisible(true);
	}

}
