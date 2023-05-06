package com.javalec.funtion;

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.javalec.base.CurrentMain;
import com.javalec.base.UserMain;

public class BuyAction extends JFrame {

	private JPanel contentPane;
	private JLabel lblUserName;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyAction frame = new BuyAction();
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
	public BuyAction() {
		setTitle("구매 완료!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 303, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblUserName());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getLblNewLabel_3());
	}
	private JLabel getLblUserName() {
		if (lblUserName == null) {
			lblUserName = new JLabel("New label");
			lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUserName.setBounds(71, 128, 119, 16);
		}
		return lblUserName;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("님");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(193, 128, 17, 16);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("구매해주셔서 감사합니다! :)");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(71, 156, 151, 16);
		}
		return lblNewLabel_2;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("메인 페이지");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redirectMain();
				}
			});
			btnNewButton.setBounds(31, 274, 99, 45);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("주문 현황");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CurrentMain();
				}
			});
			btnNewButton_1.setBounds(161, 274, 99, 45);
		}
		return btnNewButton_1;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon(BuyAction.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel_3.setBounds(100, 16, 87, 25);
		}
		return lblNewLabel_3;
	}
	
	
	//-----Function
	
	
	// 주문 현황으로 넘어가는 버튼 액션
		private void CurrentMain() {
			CurrentMain crm = new CurrentMain();
			crm.setVisible(true);
		}
	
	// 메인 화면으로 넘어가는 버튼액
		private void redirectMain() {
			UserMain main = new UserMain();
			main.setVisible(true);
			dispose();
		}
	
	// 아이디 보여지는것 
		Label label = new Label();
		
		String tfId = "User Data"; // 삽입할 유저 데이터
		

	
	
	
	
}// End
