package org.schooldesk.client;

import org.schooldesk.client.models.CourseModel;
import org.schooldesk.client.models.UserCredentialModel;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;


public class LoginFrame extends JFrame implements ActionListener{
	private JPanel panel;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JTextField fldLogin;
	private JPasswordField fldPassword;
	private JButton btnLogin;
	private JLabel lblAuthenticationFaild;

	private static final ObjectMapper mapper = new ObjectMapper();

	public LoginFrame(){
		super();
		createComponents();
		jbInit();
		subscribe();
		fillComponents();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void createComponents(){
		panel = new JPanel(new GridBagLayout());
		lblLogin = new JLabel("Login");
		lblPassword = new JLabel("Password");
		fldLogin = new JTextField();
		fldPassword = new JPasswordField();
		btnLogin = new JButton("Login");
		lblAuthenticationFaild = new JLabel("Authentication failed");
	}

	private void jbInit(){
		panel.add(lblLogin, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
	                                             new Insets(2, 2, 2, 2), 0, 0));
		panel.add(fldLogin, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
		                                           new Insets(2, 2, 2, 2), 0, 0));
		panel.add(lblPassword, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
		                                           new Insets(2, 2, 2, 2), 0, 0));
		panel.add(fldPassword, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
		                                           new Insets(2, 2, 2, 2), 0, 0));
		panel.add(lblAuthenticationFaild, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
		                                              new Insets(2, 2, 2, 2), 0, 0));
		panel.add(btnLogin, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
		                                                         new Insets(2, 2, 2, 2), 0, 0));
		add(panel, BorderLayout.CENTER);
		pack();
	}

	private void subscribe(){
		btnLogin.addActionListener(this);
	}

	private void fillComponents(){
		lblAuthenticationFaild.setVisible(false);
		lblAuthenticationFaild.setForeground(Color.red);
		setResizable(false);
	}

	public static void main(String... args)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		LoginFrame f = new LoginFrame();
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin){
			UserCredentialModel userModel = new UserCredentialModel();
			userModel.setEmail(fldLogin.getText());
			userModel.setPassword(new String(fldPassword.getPassword()));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				mapper.writeValue(baos, userModel);
			}
			catch (IOException e1) {
			}

			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost("http://127.0.0.1:8080/user/login");

			java.util.List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			nvps.add(new BasicNameValuePair("UserCredentials", new String(baos.toByteArray())));

			try {
				post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
				CloseableHttpResponse response = httpClient.execute(post);

				Response resp = mapper.readValue(EntityUtils.toString(response.getEntity()), Response.class);

				if (resp.isSuccess())
				{
					lblAuthenticationFaild.setVisible(false);

					HttpGet get = new HttpGet("http://127.0.0.1:8080/course/getCourse");
					response = httpClient.execute(get);
					resp = mapper.readValue(EntityUtils.toString(response.getEntity()), Response.class);
					if (resp.isSuccess()){
						NavigationFrame testFrame = new NavigationFrame((Set<CourseModel>) resp.getData());
						testFrame.setVisible(true);
					}
				}
				else
				{
					lblAuthenticationFaild.setVisible(true);
				}
			}
			catch (Exception e1) {
				lblAuthenticationFaild.setVisible(true);
			}
		}
	}
}
