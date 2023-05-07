package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.ProductDao;
import com.javalec.dto.ProductDto;
import com.javalec.funtion.ImageResize;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductDetailMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblProductImage;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private ImageIcon productImage;
	private JTextField tfBrandName;
	private JLabel lblNewLabel_2;
	private JTextField tfProductName;
	private JTextField tfPrice;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_3;
	private JComboBox cbSize;
	
	private JButton btnNewButton;
	private JButton btnPurchase;
	private JButton btnNewButton_1_1;
	private JLabel lblMain;
	private JLabel lblNewLabel_3_1;
	private JComboBox cbQty;
	
	private String userid;
	private int productCode;
	private int cartQty;
	private int purchaseQty;
	private int size;
	private ArrayList<ProductDto> beanList;
	private boolean check;
	
	public ProductDetailMain(int productCode, ImageIcon productImage) throws HeadlessException {
		super();
		this.productCode = productCode;
		this.productImage = productImage;
	}
	
	/* getter & setter */
	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public ImageIcon getProductImage() {
		return productImage;
	}

	public void setProductImage(ImageIcon productImage) {
		this.productImage = productImage;
	}

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				productDetail();
			}
		});
		setTitle("상품 상세");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblProductImage());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfBrandName());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getTfProductName());
		contentPane.add(getTfPrice());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getLblNewLabel_3());
		contentPane.add(getCbSize());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnPurchase());
		contentPane.add(getBtnNewButton_1_1());
		contentPane.add(getLblMain());
		contentPane.add(getLblNewLabel_3_1());
		contentPane.add(getCbQty());
	}
	private JLabel getLblProductImage() {
		if (lblProductImage == null) {
			lblProductImage = new JLabel("");
			ImageIcon productIcon = new ImageIcon(ProductDetailMain.class.getResource("/com/javalec/images/P000001.png"));
			int x = 200;
			int y = 100;
			ImageResize imageResize = new ImageResize(productIcon, x, y);
			ImageIcon resizeIcon = imageResize.imageResizing();
			lblProductImage.setIcon(null);
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
			lblNewLabel = new JLabel("브랜드");
			lblNewLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 13));
			lblNewLabel.setBounds(418, 60, 52, 16);
		}
		return lblNewLabel;
	}
	
	private JTextField getTfBrandName() {
		if (tfBrandName == null) {
			tfBrandName = new JTextField();
			tfBrandName.setEditable(false);
			tfBrandName.setBounds(470, 56, 130, 26);
			tfBrandName.setColumns(10);
		}
		return tfBrandName;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("상품명");
			lblNewLabel_2.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 13));
			lblNewLabel_2.setBounds(418, 98, 52, 16);
		}
		return lblNewLabel_2;
	}
	private JTextField getTfProductName() {
		if (tfProductName == null) {
			tfProductName = new JTextField();
			tfProductName.setEditable(false);
			tfProductName.setColumns(10);
			tfProductName.setBounds(470, 94, 200, 26);
		}
		return tfProductName;
	}
	private JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.setEditable(false);
			tfPrice.setColumns(10);
			tfPrice.setBounds(470, 126, 130, 26);
		}
		return tfPrice;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("가격");
			lblNewLabel_2_1.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 13));
			lblNewLabel_2_1.setBounds(418, 130, 52, 16);
		}
		return lblNewLabel_2_1;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("사이즈");
			lblNewLabel_3.setBounds(418, 158, 41, 16);
		}
		return lblNewLabel_3;
	}
	private JComboBox getCbSize() {
		if (cbSize == null) {
			cbSize = new JComboBox();
			cbSize.setBounds(470, 154, 80, 27);
		}
		return cbSize;
	}
	
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("장바구니에 담기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					check = checkQty();
					if(check == true) {
						addToCart();
					} else {
						JOptionPane.showMessageDialog(null, "재고가 부족합니다.\n" + "수량을 확인해주세요.");
					}
				}
			});
			btnNewButton.setBounds(418, 261, 117, 68);
		}
		return btnNewButton;
	}
	private JButton getBtnPurchase() {
		if (btnPurchase == null) {
			btnPurchase = new JButton("주문하기");
			btnPurchase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean result = checkQty();
					if(result == true) {
						PurchaseMain purchaseMain = new PurchaseMain();
						purchaseMain.setVisible(true);
						dispose();
					} else {
						/* 오류 메세지 추가 예정 */
						System.out.println("오류");
					}
				}
			});
			btnPurchase.setBounds(547, 261, 117, 68);
		}
		return btnPurchase;
	}
	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1_1 == null) {
			btnNewButton_1_1 = new JButton("취소");
			btnNewButton_1_1.setBounds(676, 261, 117, 68);
		}
		return btnNewButton_1_1;
	}
	private JLabel getLblMain() {
		if (lblMain == null) {
			lblMain = new JLabel("");
			lblMain.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					redirectMain();
				}
			});
			ImageIcon icon = new ImageIcon(ProductDetailMain.class.getResource("/com/javalec/images/backArrow.png"));
			int x = 40;
			int y = 40;
			
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon backArrow = resize.imageResizing();
			
			lblMain.setIcon(backArrow);
			lblMain.setBounds(21, 6, 52, 42);
		}
		return lblMain;
	}
	
	private JLabel getLblNewLabel_3_1() {
		if (lblNewLabel_3_1 == null) {
			lblNewLabel_3_1 = new JLabel("수량");
			lblNewLabel_3_1.setBounds(418, 186, 41, 16);
		}
		return lblNewLabel_3_1;
	}
	private JComboBox getCbQty() {
		if (cbQty == null) {
			cbQty = new JComboBox();
			cbQty.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
			cbQty.setBounds(470, 182, 80, 27);
		}
		return cbQty;
	}
	
	/* Function */
	/* 01. 상품을 클릭해서 상품 상세 페이지가 열릴 때 정보 입력해주는 메소드 */
	private void productDetail() {
		int x = 400;
		int y = 200;
		ImageResize resize = new ImageResize(productImage, x, y);
		ImageIcon productIcon = resize.imageResizing();
		lblProductImage.setIcon(productIcon);
		
		ProductDao productDao = new ProductDao();
		beanList = productDao.productDetail(productCode);
		tfBrandName.setText(beanList.get(0).getBrandName());
		tfProductName.setText(beanList.get(0).getProductName());
		tfPrice.setText(Integer.toString(beanList.get(0).getProductPrice()));
		ArrayList<String> cbSizeText = new ArrayList<String>();
		for(int i=0; i<beanList.size(); i++) {
			cbSizeText.add(Integer.toString(beanList.get(i).getSize()));
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		for (String size : cbSizeText) {
		    model.addElement(size);
		}
		cbSize.setModel(model);
	}
	
	/* 02. 메인으로 돌아가기 버튼을 눌렀을 때 실행되는 메소드 */
	private void redirectMain() {
		UserMain main = new UserMain();
		main.setVisible(true);
		dispose();
	}
	
	/* 03. 장바구니에 담기 눌렀을 때 실행되는 메소드 */
	private void addToCart() {
		cartQty = Integer.parseInt((String)cbQty.getSelectedItem());
		size = Integer.parseInt((String)cbSize.getSelectedItem());
		ProductDao productDao = new ProductDao(userid, cartQty, productCode, size);

		boolean result = productDao.addToCart();
		if(result == true) {
			JOptionPane.showMessageDialog(this, "장바구니에 상품 추가!\n" + tfProductName.getText() + "이 " + cartQty + "개 추가 되었습니다.");
		}
		
	}
	
	/* 04. 장바구니에 담기, 주문 전 수량을 체크해주는 메소드 */
	private boolean checkQty() {
		size = Integer.parseInt((String)cbSize.getSelectedItem());
		int selectCartQty = Integer.parseInt((String)cbQty.getSelectedItem());
		int selectPurchaseQty = Integer.parseInt((String)cbQty.getSelectedItem());;
		ProductDao productDao = new ProductDao();
		beanList = productDao.checkQty(productCode, size);
		if(selectCartQty > beanList.get(0).getProductStock() || selectPurchaseQty > beanList.get(0).getProductStock()) {
			return false;
		}
		return true;
	}
	
}	// End Class



