package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JButton btnNewButton;
	private JButton btnOK;
	private JButton btnCancel;
	private JPasswordField tfPassword;
	private JPasswordField tfPassword2;

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
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 400);
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
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnOK());
		contentPane.add(getBtnCancel());
		contentPane.add(getTfPassword());
		contentPane.add(getTfPassword2());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("이름 :");
			lblNewLabel.setBounds(12, 27, 57, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(129, 24, 116, 21);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("아이디 :");
			lblNewLabel_1.setBounds(12, 70, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setColumns(10);
			tfId.setBounds(129, 67, 116, 21);
		}
		return tfId;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("비밀번호 : ");
			lblNewLabel_1_1.setBounds(12, 111, 78, 15);
		}
		return lblNewLabel_1_1;
	}
	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("비밀번호 확인 :");
			lblNewLabel_1_2.setBounds(12, 152, 95, 15);
		}
		return lblNewLabel_1_2;
	}
	private JLabel getLblNewLabel_1_3() {
		if (lblNewLabel_1_3 == null) {
			lblNewLabel_1_3 = new JLabel("전화번호");
			lblNewLabel_1_3.setBounds(12, 193, 57, 15);
		}
		return lblNewLabel_1_3;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setColumns(10);
			tfPhone.setBounds(129, 190, 116, 21);
		}
		return tfPhone;
	}
	private JLabel getLblNewLabel_1_3_1() {
		if (lblNewLabel_1_3_1 == null) {
			lblNewLabel_1_3_1 = new JLabel("주소");
			lblNewLabel_1_3_1.setBounds(12, 235, 57, 15);
		}
		return lblNewLabel_1_3_1;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setColumns(10);
			tfAddress.setBounds(129, 232, 116, 21);
		}
		return tfAddress;
	}
	private JLabel getLblNewLabel_1_3_2() {
		if (lblNewLabel_1_3_2 == null) {
			lblNewLabel_1_3_2 = new JLabel("이메일");
			lblNewLabel_1_3_2.setBounds(12, 278, 57, 15);
		}
		return lblNewLabel_1_3_2;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(129, 275, 116, 21);
		}
		return tfEmail;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("비밀번호 확인");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				passwordCheck();
				}
			});
			btnNewButton.setBounds(258, 148, 109, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("완료");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i_chk = JoinCheck();
					if (i_chk == 0) {
						checkOk();
					}
				}
			});
			btnOK.setBounds(91, 316, 97, 23);
		}
		return btnOK;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("취소");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelCheck();
				}
			});
			btnCancel.setBounds(224, 316, 97, 23);
			
		}
		return btnCancel;
	}
	private JPasswordField getTfPassword() {
		if (tfPassword == null) {
			tfPassword = new JPasswordField();
			tfPassword.setHorizontalAlignment(SwingConstants.TRAILING);
			tfPassword.setBounds(129, 108, 116, 21);
		}
		return tfPassword;
	}
	private JPasswordField getTfPassword2() {
		if (tfPassword2 == null) {
			tfPassword2 = new JPasswordField();
			tfPassword2.setHorizontalAlignment(SwingConstants.TRAILING);
			tfPassword2.setBounds(129, 149, 116, 21);
		}
		return tfPassword2;
	}

	private void checkOk() {
		String name =tfName.getText();
		String str = "환영합니다.";
		JOptionPane.showMessageDialog(null, name +"님\n" + str);
		
	}
	
	private void cancelCheck() {
		JOptionPane.showMessageDialog(null, "회원 가입을 취소 하시겠습니까?" );
		dispose();
	}
	
	private int JoinCheck() {

		int i = 0;
		String message = "";

		if(tfName.getText().trim().length() == 0) {
			i++;
			message = "이름을";
			tfName.requestFocus();
		}
		if(tfPhone.getText().trim().length() == 0) {
			i++;
			message = "전화번호를";
			tfName.requestFocus();
		}
		if(tfAddress.getText().trim().length() == 0) {
			i++;
			message = "주소를";
			tfName.requestFocus();
		}
		if(tfEmail.getText().trim().length() == 0) {
			i++;
			message = "이메일을(를)";
			tfName.requestFocus();
		}
		if(i>0) {
			JOptionPane.showMessageDialog(null, message + "확인하세요!");
			}
			return i;
		}
		

	
	private void passwordCheck() {
		char[] pass = tfPassword.getPassword();
		char[] pass2 = tfPassword2.getPassword();
		
		String passString = new String(pass);
		String passString2 = new String(pass2);
				
		if(passString.equals(passString2)) {
			JOptionPane.showMessageDialog(null, "일치 합니다.");
		}else {
			JOptionPane.showMessageDialog(null, "일치 하지 않습니다.");
		}
	}
}
