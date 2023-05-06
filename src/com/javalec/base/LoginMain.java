package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.LoginDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField tfUserId;
	private JLabel lblPassword;
	private JButton btnOK;
	private JButton btnJoin;
	private JLabel lblNewLabel_1;
	private JRadioButton rbUser;
	private JRadioButton rbAdmin;
	private JLabel lblNewLabel_1_1;
	private JPasswordField pfUserPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	String message = ""; 		// 사용자가 입력하지 않은 데이터(id or pw) 알려주기

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMain frame = new LoginMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginMain() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 386);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfUserId());
		contentPane.add(getLblPassword());
		contentPane.add(getBtnOK());
		contentPane.add(getBtnJoin());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getRbUser());
		contentPane.add(getRbAdmin());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getPfUserPassword());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID : ");
			lblNewLabel.setBounds(103, 98, 57, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfUserId() {
		if (tfUserId == null) {
			tfUserId = new JTextField();
			tfUserId.setBounds(145, 95, 116, 21);
			tfUserId.setColumns(10);
		}
		return tfUserId;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password : ");
			lblPassword.setBounds(61, 142, 78, 15);
		}
		return lblPassword;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loginAction();
				}
			});
			btnOK.setBounds(110, 181, 97, 23);
		}
		return btnOK;
	}
	private JButton getBtnJoin() {
		if (btnJoin == null) {
			btnJoin = new JButton("회원가입");
			btnJoin.setBounds(111, 254, 97, 23);
		}
		return btnJoin;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("아직 회원이 아니신가요?");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(61, 229, 200, 15);
		}
		return lblNewLabel_1;
	}
	private JRadioButton getRbUser() {
		if (rbUser == null) {
			rbUser = new JRadioButton("회원");
			rbUser.setSelected(true);
			buttonGroup.add(rbUser);
			rbUser.setBounds(61, 43, 57, 23);
		}
		return rbUser;
	}
	private JRadioButton getRbAdmin() {
		if (rbAdmin == null) {
			rbAdmin = new JRadioButton("관리자");
			buttonGroup.add(rbAdmin);
			rbAdmin.setBounds(184, 43, 75, 23);
		}
		return rbAdmin;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1_1.setIcon(new ImageIcon(LoginMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel_1_1.setBounds(110, 287, 97, 44);
		}
		return lblNewLabel_1_1;
	}
	private JPasswordField getPfUserPassword() {
		if (pfUserPassword == null) {
			pfUserPassword = new JPasswordField();
			pfUserPassword.setBounds(145, 139, 116, 21);
		}
		return pfUserPassword;
	}
	
	
	
	
	
	
	//------------------functions
	
	
	private void loginAction() {
		if (rbUser.isSelected()) {		// 사용자가 로그인 할 때
			int i_chk = insertFieldCheck();
			if (i_chk != 0) { 		// id나 pw가 제대로 입력 되지 않은 경우
				JOptionPane.showMessageDialog(this, message + "확인해 주세요", "로그인 오류", JOptionPane.INFORMATION_MESSAGE);
			} else {		// id, pw 정상 입력된 상태 
				loginCheck();		// 데이터 베이스에서 유저 ID, PW가 있는지 확인 -> Dao 역할
							
			} 
		}
		
		if (rbAdmin.isSelected()) { 		// 관리자가 로그인 할 때 <<<<< 확인해야돼!!! 어떻게 할지 
			int i_chk = insertFieldCheck();
			if (i_chk != 0) { 		// id나 pw가 제대로 입력 되지 않은 경우
				JOptionPane.showMessageDialog(this, message + "확인해 주세요", "로그인 오류", JOptionPane.INFORMATION_MESSAGE);
			} else {		// id, pw 정상 입력된 상태 
				loginCheck();		// 데이터 베이스에서 유저 ID, PW가 있는지 확인	
			} 
		}
		
	}
	
	
	private int insertFieldCheck() { 		// 사용자가 입력한 Id, pw 확인하기
		int i = 0;
		
		if (tfUserId.getText().trim().length() == 0) {
			i ++;
			message = "아이디를 ";
			tfUserId.requestFocus(); 		// id 입력하도록 커서 돌려줌.
		} 
		
		if (pfUserPassword.getText().trim().length() == 0) {
			i ++;
			message = "비밀번호를 ";
			pfUserPassword.requestFocus(); 		// pw 입력하도록 커서 돌려줌.
		}
		return i;
	}
	
	private void loginCheck() {
		String id = tfUserId.getText();
		String pw = pfUserPassword.getText();
		
		LoginDao loginDao = new LoginDao(id, pw) ;
		String result = loginDao.loginCheck(); 
		
		if (result.equals(id)) {
			JOptionPane.showMessageDialog(this, result + " 님, 환영합니다!", "로그인 성공!", JOptionPane.INFORMATION_MESSAGE);;
		} else {
			JOptionPane.showMessageDialog(this, "존재하지 않거나 잘못 입력된 회원정보입니다. \n" + "Id나 Password를 확인해 주세요", "Error", JOptionPane.INFORMATION_MESSAGE);;
			
		}
		
	}
	
	
	
	
	
}
