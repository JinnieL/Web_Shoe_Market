package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.MypageDao;
import com.javalec.dao.UserDao;
import com.javalec.dto.UserDto;
import com.javalec.funtion.ImageResize;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;

public class MypageMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField tfName;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField tfPhone;
	private JLabel lblNewLabel_2_1;
	private JTextField tfAddress;
	private JLabel lblNewLabel_2_2;
	private JTextField tfEmail;
	private JButton btnUpdate;
	private JButton btnDelete;
	private String id_mysql;
	private String url_mysql;
	private String pw_mysql;

	
	private String userid;
	private ArrayList<UserDto> beanList;
	
	
	
	private JLabel lblNewLabel_3;
	private JLabel lblBackArrow;
	private JLabel lblLoginUser;
	private JLabel lblNewLabel_1_1;
	private JPasswordField pfPassword;
	private JPasswordField pfRePassword;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MypageMain frame = new MypageMain();
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
	public MypageMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				lblLoginUser.setText(userid + "님의 정보");
				textInIt();
			}
		});
		setTitle("마이페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfName());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getTfPhone());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getTfAddress());
		contentPane.add(getLblNewLabel_2_2());
		contentPane.add(getTfEmail());
		contentPane.add(getBtnUpdate());
		contentPane.add(getBtnDelete());
		contentPane.add(getLblNewLabel_3());
		contentPane.add(getLblBackArrow());
		contentPane.add(getLblLoginUser());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getPfPassword());
		contentPane.add(getPfRePassword());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("이름 :");
			lblNewLabel.setBounds(48, 68, 57, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(150, 68, 116, 21);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("비밀번호 :");
			lblNewLabel_1.setBounds(48, 105, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("주소 :");
			lblNewLabel_2.setBounds(48, 215, 57, 15);
		}
		return lblNewLabel_2;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setColumns(10);
			tfPhone.setBounds(150, 178, 179, 21);
		}
		return tfPhone;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("전화번호 :");
			lblNewLabel_2_1.setBounds(48, 178, 57, 15);
		}
		return lblNewLabel_2_1;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setColumns(10);
			tfAddress.setBounds(150, 215, 208, 21);
		}
		return tfAddress;
	}
	private JLabel getLblNewLabel_2_2() {
		if (lblNewLabel_2_2 == null) {
			lblNewLabel_2_2 = new JLabel("이메일 :");
			lblNewLabel_2_2.setBounds(48, 251, 57, 15);
		}
		return lblNewLabel_2_2;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(150, 251, 208, 21);
		}
		return tfEmail;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int u_chk = updateCheck();
					if(u_chk == 0) {
						boolean result = passwordCheck();
						if(result) {
							updateAction();
						} else {
							
						}
					}
				}
			});
			btnUpdate.setBounds(66, 291, 97, 23);
		}
		return btnUpdate;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("탈퇴");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				deleteAction();
				}
			});
			btnDelete.setBounds(194, 291, 97, 23);
		}
		return btnDelete;
	}
	
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon(MypageMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel_3.setBounds(165, -7, 80, 42);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblBackArrow() {
		if (lblBackArrow == null) {
			lblBackArrow = new JLabel("");
			lblBackArrow.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					redirectUserMain();
				}
			});
			
			ImageIcon icon = new ImageIcon(MypageMain.class.getResource("/com/javalec/images/backArrow.png"));
			int x = 40;
			int y = 40;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon backArrow = resize.imageResizing();
			
			lblBackArrow.setIcon(backArrow);
			lblBackArrow.setBounds(11, 2, 48, 52);
		}
		return lblBackArrow;
	}
	
	private JLabel getLblLoginUser() {
		if (lblLoginUser == null) {
			lblLoginUser = new JLabel("");
			lblLoginUser.setHorizontalAlignment(SwingConstants.CENTER);
			lblLoginUser.setBounds(106, 28, 192, 21);
		}
		return lblLoginUser;
	
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("비밀번호 확인 :");
			lblNewLabel_1_1.setBounds(48, 139, 90, 15);
		}
		return lblNewLabel_1_1;
	}
	
	private JPasswordField getPfPassword() {
		if (pfPassword == null) {
			pfPassword = new JPasswordField();
			pfPassword.setBounds(150, 101, 179, 26);
		}
		return pfPassword;
	}
	private JPasswordField getPfRePassword() {
		if (pfRePassword == null) {
			pfRePassword = new JPasswordField();
			pfRePassword.setBounds(150, 133, 179, 26);
		}
		return pfRePassword;
	}
	
	/* Functions */

	/* 01. 창이 열릴 때 컬럼에 데이터 채워주기 */
	private void textInIt() {
		beanList = new ArrayList<UserDto>();
		UserDao userDao = new UserDao();
		beanList = userDao.userInfo(userid);
		for(int i=0; i<beanList.size(); i++) {
			tfName.setText(beanList.get(i).getUserName());
			char[] pass = beanList.get(i).getUserPassword().toCharArray();
			String password = new String(pass);
			pfPassword.setText(password);
			tfAddress.setText(beanList.get(i).getUserAddress());
			tfPhone.setText(beanList.get(i).getUserPhone());
			tfEmail.setText(beanList.get(i).getUserEmail());
		}
		
	}
	
	private int updateCheck() {
		int i = 0;
		String message = "";

		if(tfEmail.getText().trim().length() == 0) {
			i++;
			message = "이메일을";
			tfEmail.requestFocus();
		}
		if(tfAddress.getText().trim().length() == 0) {
			i++;
			message = "주소를";
			tfAddress.requestFocus();
		}
		if(tfPhone.getText().trim().length() == 0) {
			i++;
			message = "전화번호를";
			tfPhone.requestFocus();
		}
		if(tfName.getText().trim().length() == 0) {
			i++;
			message = "이름을";
			tfName.requestFocus();
		}
		if(i>0) {
			JOptionPane.showMessageDialog(null, message + " 확인하세요!");
			}
			return i;
		}
	
	/* 02. 수정 버튼을 눌렀을 때 실행되는 메소드 */
	private void updateAction() {
		String name = tfName.getText();
		char[] pass = pfPassword.getPassword();
		char[] rePass = pfRePassword.getPassword();
		String password = new String(pass);
		String RePassword = new String(rePass);
		String phone = tfPhone.getText();
		String address = tfAddress.getText();
		String email = tfEmail.getText();
		
		UserDao userDao = new UserDao(userid, name, password, address, phone, email);
		boolean result = userDao.updateAction();
		if(result) {
			JOptionPane.showMessageDialog(this, "주소록 정보 수정!\n" + tfName.getText() + "님의 정보가 수정 되었습니다.!", "주소록 정보", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "주소록 정보 수정!\n" + "입력중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* 03. 비밀번호 확인하는 메소드 */
	private boolean passwordCheck() {
		boolean check = false;
		char[] pass = pfPassword.getPassword();
		char[] rePass = pfRePassword.getPassword();
		String password = new String(pass);
		String RePassword = new String(rePass);
		if(password.equals(RePassword)) {
			check = true;
		} else {
			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			check = false;
		}
		return check;
	}
	
	
	/* 04. 삭제 */
	private void deleteAction() {
	    UserDao userDao = new UserDao();
		int result = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION);
	    if (result == JOptionPane.YES_OPTION) {
	    	boolean delete = userDao.deleteAction(userid);
	    	if(delete == true) {
	    		JOptionPane.showMessageDialog(null, "회원탈퇴 완료", "지금까지 Shoemarket을 이용해주셔서 감사합니다.", JOptionPane.INFORMATION_MESSAGE);
	    		LoginMain loginMain = new LoginMain();
	    		loginMain.setVisible(true);
	    		dispose();
	    	} else {
	    		
	    	}
	    }
	}
	
	private void redirectUserMain() {
		UserMain userMain = new UserMain();
		userMain.setUserid(userid);
		userMain.setVisible(true);
		dispose();
	}

}	// End Class


