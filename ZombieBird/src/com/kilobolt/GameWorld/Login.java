package com.kilobolt.GameWorld;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {

	public static void main(String[] args) {

		final ArrayList<String> UN = new ArrayList<String>();

		for (int i = 1; i <= 10; i++) {

			UN.add("user" + i);

		}
		for (int i = 0; i < UN.size(); i++) {
			System.out.println(UN.get(i));
		}

		JFrame frame = new JFrame("Login");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints c = new GridBagConstraints();

		JPanel panel = new JPanel(new GridBagLayout());
		frame.add(panel);

		JLabel UN2 = new JLabel("Username:");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(UN2, c);

		JLabel PW2 = new JLabel("Password:");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(PW2, c);

		final JTextField UN1 = new JTextField(10);
		UN1.setText("");
		c.gridx = 1;
		c.gridy = 0;
		panel.add(UN1, c);

		final JTextField PW = new JTextField(10);
		PW.setText("");
		c.gridx = 1;
		c.gridy = 1;
		panel.add(PW, c);

		JButton Login = new JButton("Login");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(Login, c);

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		Login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				String Username = UN1.getText();
				String Password = PW.getText();

				if (!Username.equals("") && !Password.equals("")) {

					if (Username.equals(Password)) {
						for (int i = 0; i < UN.size(); i++) {

					if (Username.equals(UN.get(i))) {
							JOptionPane.showMessageDialog(null, "Welcome " + Username, "Login Success",
										JOptionPane.PLAIN_MESSAGE); // switch to menu page
	}
}
	JOptionPane.showMessageDialog(null, "Incorrect Username/Password", "Login Failed",JOptionPane.ERROR_MESSAGE);
		UN1.setText(null);
		PW.setText(null);

} else {
	JOptionPane.showMessageDialog(null, "Incorrect Username/Password", "Login failed",JOptionPane.ERROR_MESSAGE);
		UN1.setText(null);
		PW.setText(null);

}

} else {
	JOptionPane.showMessageDialog(null, "Incorrect Username/Password", "Login Failed",JOptionPane.ERROR_MESSAGE);
		UN1.setText(null);
		PW.setText(null);
	}

	}
	});

}
}
