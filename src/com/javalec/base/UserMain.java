package com.javalec.base;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.ProductDao;
import com.javalec.dto.ProductDto;
import com.javalec.funtion.ImageResize;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class UserMain extends JFrame {

	private JPanel contentPane;
	private JComboBox cbSelection;
	private JTextField tfSelection;
	private JButton btnSelection;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JLabel lblMyPage;
	private JTable innerTable;
	
	/* Field */
	private String userid;
	
	
	/* Constructor */
	
	/* 로그인 유저를 가져올 getter & setter */
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}



	private ArrayList<ProductDto> beanList = null;
	private ImageIcon icon;
	private ImageIcon productIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain frame = new UserMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Table 생성
	 */
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnCart;
	private JButton btnPurchaseHistory;
	private JLabel lblLoginUser;
	private JButton btnLogout;
	

	/**
	 * Create the frame.
	 */
	public UserMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();
				queryAction();
			}
		});
		setTitle("유저 메인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 629);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbSelection());
		contentPane.add(getTfSelection());
		contentPane.add(getBtnSelection());
		contentPane.add(getLblNewLabel());
		contentPane.add(getScrollPane());
		contentPane.add(getLblMyPage());
		contentPane.add(getBtnCart());
		contentPane.add(getBtnPurchaseHistory());
		contentPane.add(getLblLoginUser());
		contentPane.add(getBtnLogout());
	}
	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"브랜드", "상품명"}));
			cbSelection.setBounds(16, 72, 89, 27);
		}
		return cbSelection;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(105, 71, 297, 26);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}
	private JButton getBtnSelection() {
		if (btnSelection == null) {
			btnSelection = new JButton("검색");
			btnSelection.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnSelection.setBackground(Color.BLACK);
			btnSelection.setForeground(Color.BLACK);
			btnSelection.setBounds(405, 71, 89, 29);
		}
		return btnSelection;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(UserMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel.setBounds(214, 6, 89, 29);
		}
		return lblNewLabel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(16, 112, 478, 404);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JLabel getLblMyPage() {
		if (lblMyPage == null) {
			lblMyPage = new JLabel("");
			lblMyPage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					redirectMyPage();
				}
			});
			ImageIcon icon = new ImageIcon(UserMain.class.getResource("/com/javalec/images/myPageBtn.png"));
			int x = 40;
			int y = 40;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon myPageIcon = resize.imageResizing();
			lblMyPage.setIcon(myPageIcon);
			lblMyPage.setBounds(454, 19, 40, 40);
		}
		return lblMyPage;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable() {
				public Class getColumnClass(int column) {
					return (column == 1) ? Icon.class : Object.class;
				}
			};
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
			innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
			innerTable.setRowHeight(150);
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
		}
		return innerTable;
	}
	
	private JButton getBtnCart() {
		if (btnCart == null) {
			btnCart = new JButton("장바구니 현황");
			btnCart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redirectCart();
				}
			});
			btnCart.setBounds(254, 528, 117, 56);
		}
		return btnCart;
	}
	
	private JLabel getLblLoginUser() {
		if (lblLoginUser == null) {
			lblLoginUser = new JLabel("");
			lblLoginUser.setHorizontalAlignment(SwingConstants.TRAILING);
			lblLoginUser.setBounds(325, 0, 169, 16);
		}
		return lblLoginUser;
	}
	
	/**** Functions ****/
	
	/* 01. 테이블 정리 메소드 */
	private void tableInit() {
		outerTable.addColumn("No");
		outerTable.addColumn("사진");
		outerTable.addColumn("상품명");
		outerTable.addColumn("가격");
		outerTable.setColumnCount(4);
		
		int i = outerTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerTable.removeRow(0);
		}
		/* 사진 컬럼 크기*/
		int vColIndex = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(vColIndex);
		int width = 25;
		col.setPreferredWidth(width);
		
		/*사진 컬럼 크기 */
		vColIndex = 1;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 250;
		col.setPreferredWidth(width);
		
		/* 상품명 컬럼 크기 */
		vColIndex = 2;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 130;
		col.setPreferredWidth(width);
		
		/* 가격 컬럼 크기 */
		vColIndex = 3;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 60;
		col.setPreferredWidth(width);
		
		lblLoginUser.setText(userid + "님 환영합니다.");
		
		
	}
	
	/* 02. 테이블에 데이터 채워주기 */
	private void queryAction() {
		beanList = new ArrayList<ProductDto>();
		ProductDao productDao = new ProductDao();
		beanList = productDao.selectList();
		int listCount = beanList.size();
		
		for(int i=0; i<listCount; i++) {
			icon = new ImageIcon("./" + beanList.get(i).getProductImageName());
			int x = 250;
			int y = 150;
			ImageResize resize = new ImageResize(icon, x, y);
			productIcon = resize.imageResizing();
			Object[] tempData = {beanList.get(i).getProductCode(), productIcon, beanList.get(i).getProductName(), beanList.get(i).getProductPrice()};
			outerTable.addRow(tempData);
		}
	}
	
	/* 03. 카트 버튼을 클릭했을 때 */
	private void redirectCart() {
		CartMain cartMain = new CartMain();
		cartMain.setUserid(userid);
		cartMain.setVisible(true);
		dispose();
	}
	
	/* 04. 테이블을 클릭 했을 때 */
	private void tableClick() {
		int i = innerTable.getSelectedRow();
		int wkCode = (int)innerTable.getValueAt(i, 0);
		ImageIcon wkImage = (ImageIcon)innerTable.getValueAt(i, 1);
		ProductDetailMain productDetailMain = new ProductDetailMain();
		productDetailMain.setUserid(userid);
		productDetailMain.setProductCode(wkCode);
		productDetailMain.setProductImage(wkImage);
		productDetailMain.setVisible(true);
		dispose();
	}
	
	/* 05. 검색 버튼을 눌렀을 때 컬럼을 선택하고 검색을 호출하는 메소드 */
	private void conditionQuery() {
		int i = cbSelection.getSelectedIndex();
		String conditionQueryColumn = "";
		switch(i) {
		case 0:
			conditionQueryColumn = "brandName";
			break;
		case 1:
			conditionQueryColumn = "productName";
			break;
		default:
			break;
		}
		tableInit();
		conditionQueryAction(conditionQueryColumn);
	}
	
	/* 06. 검색 기능을 수행하는 메소드 */
	private void conditionQueryAction(String conditionQueryColumn) {
		ProductDao productDao = new ProductDao(conditionQueryColumn, tfSelection.getText());
		beanList = productDao.conditionList();
		int listCount = beanList.size();
		
		for(int i=0; i<listCount; i++) {
			icon = new ImageIcon("./" + beanList.get(i).getProductImageName());
			int x = 250;
			int y = 150;
			ImageResize resize = new ImageResize(icon, x, y);
			productIcon = resize.imageResizing();
			Object[] tempData = {beanList.get(i).getProductCode(), productIcon, beanList.get(i).getProductName(), beanList.get(i).getProductPrice()};
			outerTable.addRow(tempData);
		}
		
	}
	
	/* 07. 주문 현황 버튼을 클릭했을 때 */
	private JButton getBtnPurchaseHistory() {
		if (btnPurchaseHistory == null) {
			btnPurchaseHistory = new JButton("주문내역");
			btnPurchaseHistory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PurchaseHistoryMain historyMain = new PurchaseHistoryMain();
					historyMain.setUserid(userid);
					historyMain.setVisible(true);
					dispose();
				}
			});
			btnPurchaseHistory.setBounds(377, 528, 117, 56);
		}
		return btnPurchaseHistory;
	}
	
	/* 08. My Page를 클릭했을 때 */
	private void redirectMyPage() {
		MypageMain mypageMain = new MypageMain();
		mypageMain.setUserid(userid);
		mypageMain.setVisible(true);
		dispose();
	}
	
	
	
	
	/* */
	

	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout");
			btnLogout.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
				    if (result == JOptionPane.YES_OPTION) {
				    	LoginMain loginMain = new LoginMain();
				    	loginMain.setVisible(true);
				    	dispose();
				    } else {
				    	
				    }
				}
			});
			btnLogout.setBounds(6, 23, 76, 29);
		}
		return btnLogout;
	}
} // End Class
