package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ChatRoom {

	private JFrame frame;
	static JTextField textField;
	static JTextArea textArea;
	static Socket socket = null;
	DataTransfer dataTransfer=new DataTransfer();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			socket = new Socket("1.15.226.19",9999);
			System.out.println(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatRoom window = new ChatRoom();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		while(true){
			InputStream in= null;
			try {
				in = socket.getInputStream();
				byte[] mes=new byte[1024];
				int len=in.read(mes);
				String Rmes=new String(mes);
				textArea.append(Rmes);
			} catch (IOException e) {
				e.printStackTrace();
			}
					}
	}

	/**
	 * Create the application.
	 */
	public ChatRoom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 647, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(23, 288, 406, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mes=textField.getText();
				dataTransfer.transstring(socket,mes);
				textField.setText("");
			}
		});
		btnNewButton.setBounds(439, 293, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 23, 406, 244);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
