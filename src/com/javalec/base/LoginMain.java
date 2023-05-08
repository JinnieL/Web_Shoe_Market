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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	private JPasswordField pfUserPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	String userid;
	String message = ""; 		// 사용자가 입력하지 않은 데이터(id or pw) 알려주기
	private JLabel lblNewLabel_2;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				/* 시작 시 자동으로 userid 텍스트 창에 포커스 */
				tfUserId.requestFocus();
			}
		});
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
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
		contentPane.add(getPfUserPassword());
		contentPane.add(getLblNewLabel_2());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID : ");
			lblNewLabel.setBounds(158, 198, 57, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfUserId() {
		if (tfUserId == null) {
			tfUserId = new JTextField();
			tfUserId.setBounds(242, 194, 146, 21);
			tfUserId.setColumns(10);
		}
		return tfUserId;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password : ");
			lblPassword.setBounds(158, 236, 78, 15);
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
			btnOK.setBounds(209, 287, 97, 23);
		}
		return btnOK;
	}
	private JButton getBtnJoin() {
		if (btnJoin == null) {
			btnJoin = new JButton("회원가입");
			btnJoin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "회원가입을 하시겠습니까??", "회원가입", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION) {
						JoinMain joinMain = new JoinMain();
						joinMain.setVisible(true);
						dispose();
					} else {
						
					}
				}
			});
			btnJoin.setBounds(209, 356, 97, 23);
		}
		return btnJoin;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("아직 회원이 아니신가요?");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(158, 329, 200, 15);
		}
		return lblNewLabel_1;
	}
	private JRadioButton getRbUser() {
		if (rbUser == null) {
			rbUser = new JRadioButton("회원");
			rbUser.setSelected(true);
			buttonGroup.add(rbUser);
			rbUser.setBounds(148, 150, 57, 23);
		}
		return rbUser;
	}
	private JRadioButton getRbAdmin() {
		if (rbAdmin == null) {
			rbAdmin = new JRadioButton("관리자");
			buttonGroup.add(rbAdmin);
			rbAdmin.setBounds(265, 150, 75, 23);
		}
		return rbAdmin;
	}
	private JPasswordField getPfUserPassword() {
		if (pfUserPassword == null) {
			pfUserPassword = new JPasswordField();
			pfUserPassword.setBounds(242, 233, 146, 21);
		}
		return pfUserPassword;
	}
	
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(LoginMain.class.getResource("/com/javalec/images/logo.png")));
			lblNewLabel_2.setBounds(54, 6, 400, 132);
		}
		return lblNewLabel_2;
	}
	
	//------------------functions
	private void loginAction() {
		if (rbUser.isSelected()) {		// 사용자가 로그인 할 때
			boolean result = true;
			System.out.println(result);
			if(tfUserId.getText().length() != 0) {
				result = existsUserID();
			}
			
			if(result == true) {
				loginCheck();		// 데이터 베이스에서 유저 ID, PW가 있는지 확인 -> Dao 역할
			} else {
				JOptionPane.showMessageDialog(this, "아이디가 존재하지 않습니다.");
				tfUserId.requestFocus();
			}
			
			int i_chk = insertFieldCheck();
			if (i_chk != 0) { 		// id나 pw가 제대로 입력 되지 않은 경우
				JOptionPane.showMessageDialog(this, message + "확인해 주세요", "로그인 오류", JOptionPane.INFORMATION_MESSAGE);
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
		
		if (pfUserPassword.getText().trim().length() == 0) {
			i ++;
			message = "비밀번호를 ";
			pfUserPassword.requestFocus(); 		// pw 입력하도록 커서 돌려줌.
		}
		
		if (tfUserId.getText().trim().length() == 0) {
			i ++;
			message = "아이디를 ";
			tfUserId.requestFocus();
		} 
		
		return i;
	}
	
	/* 아이디 체크 */
	private boolean existsUserID() {
		boolean result = false;
		userid = tfUserId.getText();
		LoginDao loginDao = new LoginDao();
		int count = loginDao.existsUserID(userid);
		if(count == 0) {
			return result = false;
		} else {
			return result = true;
		}
		
	}
	
	
	private void loginCheck() {
		String id = tfUserId.getText();
		char[] pass = pfUserPassword.getPassword();
		String password = new String(pass);
		
		LoginDao loginDao = new LoginDao(id, password) ;
		boolean result = loginDao.loginCheck(id, password);
		
		if (result == true) {
			JOptionPane.showMessageDialog(this, id + " 님, 환영합니다!", "로그인 성공!", JOptionPane.INFORMATION_MESSAGE);;
			UserMain userMain = new UserMain();
			String userid = tfUserId.getText();
			if(userid.equals("admin") && rbAdmin.isSelected()) {
				AdminMain admianMain = new AdminMain();
				admianMain.setVisible(true);
			} else {
				userMain.setUserid(userid);
				userMain.setVisible(true);
				dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호를 확인해 주세요", "로그인 실패", JOptionPane.INFORMATION_MESSAGE);
			tfUserId.requestFocus();
			
		}
		
	}

}
