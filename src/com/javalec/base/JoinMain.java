package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.JoinDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

public class JoinMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField tfName;
	private JLabel lblNewLabel_1;
	private JTextField tfId;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JTextField tfPhone;
	private JLabel lblNewLabel_1_3_1;
	private JTextField tfAddress;
	private JLabel lblNewLabel_1_3_2;
	private JTextField tfEmail;
	private JButton btnOK;
	private JButton btnCancel;
	private JPasswordField tfPassword;
	private JPasswordField tfPassword2;
	
	private String userName;
	private String userid;
	private String userPassword;
	private String userPhone;
	private String userAddress;
	private String userEmail;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinMain frame = new JoinMain();
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
	public JoinMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfName());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTfId());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getLblNewLabel_1_2());
		contentPane.add(getLblNewLabel_1_3());
		contentPane.add(getTfPhone());
		contentPane.add(getLblNewLabel_1_3_1());
		contentPane.add(getTfAddress());
		contentPane.add(getLblNewLabel_1_3_2());
		contentPane.add(getTfEmail());
		contentPane.add(getBtnOK());
		contentPane.add(getBtnCancel());
		contentPane.add(getTfPassword());
		contentPane.add(getTfPassword2());
		
		JButton btnCheckID = new JButton("중복 확인");
		btnCheckID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			/* 아이디 확인 */
			checkID();
				
			}
		});
		btnCheckID.setBounds(353, 107, 110, 23);
		contentPane.add(btnCheckID);
		contentPane.add(getLblNewLabel_2());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("이름 :");
			lblNewLabel.setBounds(30, 67, 57, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(147, 64, 190, 21);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("아이디 :");
			lblNewLabel_1.setBounds(30, 110, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setColumns(10);
			tfId.setBounds(147, 107, 190, 21);
		}
		return tfId;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("비밀번호 : ");
			lblNewLabel_1_1.setBounds(30, 151, 78, 15);
		}
		return lblNewLabel_1_1;
	}
	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("비밀번호 확인 :");
			lblNewLabel_1_2.setBounds(30, 192, 95, 15);
		}
		return lblNewLabel_1_2;
	}
	private JLabel getLblNewLabel_1_3() {
		if (lblNewLabel_1_3 == null) {
			lblNewLabel_1_3 = new JLabel("전화번호");
			lblNewLabel_1_3.setBounds(30, 233, 57, 15);
		}
		return lblNewLabel_1_3;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setColumns(10);
			tfPhone.setBounds(147, 230, 190, 21);
		}
		return tfPhone;
	}
	private JLabel getLblNewLabel_1_3_1() {
		if (lblNewLabel_1_3_1 == null) {
			lblNewLabel_1_3_1 = new JLabel("주소");
			lblNewLabel_1_3_1.setBounds(30, 275, 57, 15);
		}
		return lblNewLabel_1_3_1;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setColumns(10);
			tfAddress.setBounds(147, 272, 190, 21);
		}
		return tfAddress;
	}
	private JLabel getLblNewLabel_1_3_2() {
		if (lblNewLabel_1_3_2 == null) {
			lblNewLabel_1_3_2 = new JLabel("이메일");
			lblNewLabel_1_3_2.setBounds(30, 318, 57, 15);
		}
		return lblNewLabel_1_3_2;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(147, 315, 190, 21);
		}
		return tfEmail;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("완료");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i_chk = JoinCheck();
					if (i_chk == 0) {
						registerAction();
					}
				}
			});
			btnOK.setBounds(109, 371, 97, 23);
		}
		return btnOK;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("취소");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "주문을 취소하시겠습니까?", "주문 취소", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION) {
						dispose();
					} else {
						
					}
				}
			});
			btnCancel.setBounds(240, 371, 97, 23);
			
		}
		return btnCancel;
	}
	private JPasswordField getTfPassword() {
		if (tfPassword == null) {
			tfPassword = new JPasswordField();
			tfPassword.setBounds(147, 148, 190, 21);
		}
		return tfPassword;
	}
	private JPasswordField getTfPassword2() {
		if (tfPassword2 == null) {
			tfPassword2 = new JPasswordField();
			tfPassword2.setBounds(147, 189, 190, 21);
		}
		return tfPassword2;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(JoinMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel_2.setBounds(204, 1, 88, 40);
		}
		return lblNewLabel_2;
	}
	
	
	
	/* Functions */
	private int JoinCheck() {

		int i = 0;
		String message = "";

		if(tfEmail.getText().trim().length() == 0) {
			i++;
			message = "이메일을(를)";
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
		if(tfPassword2.getPassword().length == 0) {
			i++;
			message = "비밀번호 확인을";
			tfPassword2.requestFocus();
		}
		if(tfPassword.getPassword().length == 0) {
			i++;
			message = "비밀번호를";
			tfPassword.requestFocus();
		}
		if(tfId.getText().trim().length() == 0) {
			i++;
			message = "아이디를";
			tfId.requestFocus();
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
	
	private void checkID() {
		String insertID = tfId.getText();
		JoinDao joinDao = new JoinDao();
		int result = joinDao.checkID(insertID);
		
		if(result == 0) {
			JOptionPane.showMessageDialog(this, "중복되는 ID가 없습니다.");
		} else {
			JOptionPane.showMessageDialog(this, "중복되는 ID입니다.");
		}
		
	}
	
	private void registerAction() {
		userName = tfName.getText();
		userid = tfId.getText();
		char[] Pass = tfPassword.getPassword();
		userPassword = new String(Pass);
		char[] rePass = tfPassword2.getPassword();
		String userRePass = new String(rePass);
		if(userPassword.equals(userRePass)) {
			
		} else {
			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			return;
		}
		userPhone = tfPhone.getText();
		userAddress = tfAddress.getText();
		userEmail = tfEmail.getText();
		
		JoinDao joinDao = new JoinDao();
		boolean result = joinDao.register(userName, userid, userPassword, userPhone, userAddress, userEmail);
		if(result == true) {
			JOptionPane.showMessageDialog(this, userid + "님 회원가입을 축하합니다.");
			LoginMain loginMain = new LoginMain();
			loginMain.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "중복되는 아이디로는 가입할 수 없습니다.", "경고!", JOptionPane.WARNING_MESSAGE);
		}
	}

}	// End Class
