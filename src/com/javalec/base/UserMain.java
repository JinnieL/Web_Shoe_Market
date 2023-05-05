package com.javalec.base;

import java.awt.EventQueue;

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

public class UserMain extends JFrame {

	private JPanel contentPane;
	private JComboBox cbSelection;
	private JTextField tfSelection;
	private JButton btnSelection;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JLabel lblMyPage;
	private JLabel lblCart;
	private JTable innerTable;
	private ArrayList<ProductDto> beanList = null;

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
		setBounds(100, 100, 500, 700);
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
		contentPane.add(getLblCart());
	}
	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"브랜드", "상품명"}));
			cbSelection.setBounds(16, 62, 89, 27);
		}
		return cbSelection;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(105, 61, 297, 26);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}
	private JButton getBtnSelection() {
		if (btnSelection == null) {
			btnSelection = new JButton("검색");
			btnSelection.setBackground(Color.BLACK);
			btnSelection.setForeground(Color.BLACK);
			btnSelection.setBounds(405, 61, 89, 29);
		}
		return btnSelection;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(UserMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel.setBounds(211, 6, 89, 29);
		}
		return lblNewLabel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(16, 112, 468, 404);
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
					/* MouseClick을 할 경우 MyPage로 보내주기 */
					System.out.println("작동 확인");
				}
			});
			ImageIcon icon = new ImageIcon(UserMain.class.getResource("/com/javalec/images/myPageBtn.png"));
			int x = 40;
			int y = 40;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon myPageIcon = resize.imageResizing();
			lblMyPage.setIcon(myPageIcon);
			lblMyPage.setBounds(454, 6, 40, 40);
		}
		return lblMyPage;
	}
	private JLabel getLblCart() {
		if (lblCart == null) {
			lblCart = new JLabel("");
			lblCart.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					redirectCart();
				}
			});
			ImageIcon icon = new ImageIcon(UserMain.class.getResource("/com/javalec/images/cartBtn.png"));
			int x = 60;
			int y = 60;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon cartIcon = resize.imageResizing();
			
			lblCart.setIcon(cartIcon);
			lblCart.setBounds(26, 528, 60, 60);
		}
		return lblCart;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable() {
				public Class getColumnClass(int column) {
					return (column == 0) ? Icon.class : Object.class;
				}
			};
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
			innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
			innerTable.setRowHeight(150);
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
		}
		return innerTable;
	}
	
	/* 01. 테이블 정리 메소드 */
	private void tableInit() {
		outerTable.addColumn("사진");
		outerTable.addColumn("상품명");
		outerTable.addColumn("가격");
		outerTable.setColumnCount(3);
		
		int i = outerTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerTable.removeRow(0);
		}
		
		/*사진 컬럼 크기 */
		int vColIndex = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(vColIndex);
		int width = 250;
		col.setPreferredWidth(width);
		
		/* 상품명 컬럼 크기 */
		vColIndex = 1;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		
		/* 가격 컬럼 크기 */
		vColIndex = 2;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 60;
		col.setPreferredWidth(width);
		System.out.println("01. Metohd Pass");
	}
	
	/* 02. 테이블에 데이터 채워주기 */
	private void queryAction() {
		beanList = new ArrayList<ProductDto>();
		ProductDao productDao = new ProductDao();
		beanList = productDao.selectList();
		int listCount = beanList.size();
		
		for(int i=0; i<listCount; i++) {
			ImageIcon icon = new ImageIcon("./" + beanList.get(i).getProductImageName());
			int x = 250;
			int y = 150;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon productIcon = resize.imageResizing();
			Object[] tempData = {productIcon, beanList.get(i).getProductName(), beanList.get(i).getProductPrice()};
			outerTable.addRow(tempData);
		}
		System.out.println("02. Method Pass");
	}
	
	/* 03. Cart Btn 눌렀을 때 */
	private void redirectCart() {
		CartMain cartMain = new CartMain();
		cartMain.setVisible(true);
		dispose();
	}
	
} // End Class
