package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.funtion.ImageResize;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class ProductDetailMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblProductImage;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JLabel lblBuy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductDetailMain frame = new ProductDetailMain();
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
	public ProductDetailMain() {
		setTitle("상품 상세");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblProductImage());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblBuy());
	}
	private JLabel getLblProductImage() {
		if (lblProductImage == null) {
			lblProductImage = new JLabel("");
			ImageIcon productIcon = new ImageIcon(ProductDetailMain.class.getResource("/com/javalec/images/P000001.png"));
			int x = 200;
			int y = 100;
			ImageResize imageResize = new ImageResize(productIcon, x, y);
			ImageIcon resizeIcon = imageResize.imageResizing();
			lblProductImage.setIcon(resizeIcon);
			lblProductImage.setBounds(6, 60, 400, 200);
		}
		return lblProductImage;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(ProductDetailMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel_1.setBounds(352, 6, 90, 42);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 13));
			lblNewLabel.setBounds(418, 60, 81, 16);
		}
		return lblNewLabel;
	}
	
	private JLabel getLblBuy() {
		if (lblBuy == null) {
			lblBuy = new JLabel("");
			ImageIcon icon = new ImageIcon(ProductDetailMain.class.getResource("/com/javalec/images/buyBtn.png"));
			int x = 120;
			int y = 80;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon buyIcon = resize.imageResizing();
			lblBuy.setIcon(buyIcon);
			lblBuy.setBounds(45, 350, 120, 80);
		}
		return lblBuy;
	}
}	// End Class



