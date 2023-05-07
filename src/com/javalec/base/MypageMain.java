package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.MypageDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class MypageMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField tfName;
	private JLabel lblNewLabel_1;
	private JTextField tfPassword;
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

	
	String userId;
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
		setTitle("마이페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfName());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTfPassword());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getTfPhone());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getTfAddress());
		contentPane.add(getLblNewLabel_2_2());
		contentPane.add(getTfEmail());
		contentPane.add(getBtnUpdate());
		contentPane.add(getBtnDelete());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("이름 :");
			lblNewLabel.setBounds(12, 30, 57, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(81, 27, 116, 21);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("비밀번호 :");
			lblNewLabel_1.setBounds(12, 67, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfPassword() {
		if (tfPassword == null) {
			tfPassword = new JTextField();
			tfPassword.setColumns(10);
			tfPassword.setBounds(81, 64, 116, 21);
		}
		return tfPassword;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("주소 :");
			lblNewLabel_2.setBounds(12, 141, 57, 15);
		}
		return lblNewLabel_2;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setColumns(10);
			tfPhone.setBounds(81, 101, 116, 21);
		}
		return tfPhone;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("전화번호 :");
			lblNewLabel_2_1.setBounds(12, 104, 57, 15);
		}
		return lblNewLabel_2_1;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setColumns(10);
			tfAddress.setBounds(81, 138, 116, 21);
		}
		return tfAddress;
	}
	private JLabel getLblNewLabel_2_2() {
		if (lblNewLabel_2_2 == null) {
			lblNewLabel_2_2 = new JLabel("전자우편 :");
			lblNewLabel_2_2.setBounds(12, 177, 57, 15);
		}
		return lblNewLabel_2_2;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(81, 174, 116, 21);
		}
		return tfEmail;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				updateAction();
				}
			});
			btnUpdate.setBounds(30, 217, 97, 23);
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
			btnDelete.setBounds(158, 217, 97, 23);
		}
		return btnDelete;
	}

	private void updateAction() {
		String name = tfName.getText();
		String password = tfPassword.getText();
		String phone = tfPhone.getText();
		String address = tfAddress.getText();
		String email = tfEmail.getText();
		
		MypageDao dao = new MypageDao();
		boolean result = dao.updateAction(); 
		
		if(result) {
			JOptionPane.showMessageDialog(this, "주소록 정보 수정!\n" + tfName.getText() + "님의 정보가 수정 되었습니다.!", "주소록 정보", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "주소록 정보 수정!\n" + "입력중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void deleteAction() {
	    int result = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
	    if (result == JOptionPane.YES_OPTION) {
	        PreparedStatement ps = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            String query = "delete from userinfo where id = ?";
	            ps = conn_mysql.prepareStatement(query);
	            ps.setString(1, userId.trim());
	            ps.executeUpdate();
	            conn_mysql.close();
	            JOptionPane.showMessageDialog(null, "userId" + "탈퇴되었습니다.");
	            System.exit(0); // 프로그램 종료
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}


