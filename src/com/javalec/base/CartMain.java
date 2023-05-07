package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.CartDao;
import com.javalec.dto.CartDto;
import com.javalec.funtion.ImageResize;
import com.javalec.util.ShareVar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CartMain extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JLabel lblTotal;
	private JTextField tfTotal;
	private JButton btnEmpty;
	private JButton btnOrder;
	private JButton btnUpdate;
	private JButton btnCancel;
	
	// Table
	String userid;
	
	
	/* Constructor */
	
	
	/* 로그인 유저를 가져올 getter & setter */
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	

	
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	

	
	ArrayList<CartDto> beanList = null;
	int qty = 0;
	
	private JLabel lblUser;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblMain;
	private JComboBox cbQty;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CartMain frame = new CartMain();
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
	public CartMain() {
		setTitle("장바구니");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {	// windowopend
				tableInit();	// table 정리
				searchAction();	// table 내용

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getLblTotal());
		contentPane.add(getTfTotal());
		contentPane.add(getBtnEmpty());
		contentPane.add(getBtnOrder());
		contentPane.add(getBtnUpdate());
		contentPane.add(getBtnCancel());
		contentPane.add(getLblUser());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblMain());
		contentPane.add(getCbQty());
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 38, 585, 327);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	
	// table 정의 innerTable + outerTable, 이미지 사이즈
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable() {									// 테이블 데이터 지정
				public Class getColumnClass(int column) {				// 속성을 바꿔준다.
					return (column == 0) ? Icon.class : Object.class;	// 0번째 컬럼은 이미지 나머지는 오브젝트로 넣는다
				}
				
			};
			innerTable.addMouseListener(new MouseAdapter() {	

				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);	
			innerTable.setRowHeight(150);
		}
		
		return innerTable;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("");
			lblTotal.setHorizontalAlignment(SwingConstants.TRAILING);
			lblTotal.setBounds(333, 377, 124, 16);
		}
		return lblTotal;
	}
	private JTextField getTfTotal() {
		if (tfTotal == null) {
			tfTotal = new JTextField();
			tfTotal.setHorizontalAlignment(SwingConstants.TRAILING);
			tfTotal.setEditable(false);
			tfTotal.setBounds(457, 369, 130, 26);
			tfTotal.setColumns(10);
		}
		return tfTotal;
	}
	private JButton getBtnEmpty() {
		if (btnEmpty == null) {
			btnEmpty = new JButton("장바구니 비우기");
			btnEmpty.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	// 장바구니 비우기
					tableEmpty();
					tableInit();
					searchAction();
				}
			});
			btnEmpty.setBounds(461, 402, 130, 29);
		}
		return btnEmpty;
	}
	private JButton getBtnOrder() {
		if (btnOrder == null) {
			btnOrder = new JButton("주문");
			btnOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redirectPurchase();
				}
			});
			btnOrder.setBounds(6, 402, 85, 29);
		}
		return btnOrder;
	}
	
	// 수량 업데이트
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수량 수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableUpdate();
					tableInit();
					searchAction();
				}
			});
			btnUpdate.setEnabled(false);
			btnUpdate.setBounds(243, 402, 85, 29);
		}
		return btnUpdate;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("상품 취소");
			btnCancel.setEnabled(false);
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableDelete();
					tableInit();
					searchAction();
				}
			});
			btnCancel.setBounds(87, 402, 85, 29);
		}
		return btnCancel;
	}
	
	// ================= function ===================
	
	
	// table 초기 작업
	private void tableInit() {
		outerTable.addColumn("상품사진");	// 타이틀 네임
		outerTable.addColumn("상품명");
		outerTable.addColumn("사이즈(mm)");
		outerTable.addColumn("수량");
		outerTable.addColumn("가격");		
		outerTable.setColumnCount(5);		// 타이틀이 몇개냐
	
		int i = outerTable.getRowCount();	// 테이블에 데이터가 몇개 있는지
		
		for(int j=0; j<i;j++) {
			outerTable.removeRow(0);		// 지워주기
		}
		
		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF); // 사이즈 조절 안한다
		
		// 상품이미지 사이즈
		int vColIndex = 0;					// 데이터 크기 조절 
		TableColumn col = innerTable.getColumnModel().getColumn(vColIndex);
		int width = 200;
		col.setPreferredWidth(width);
		
		// 상품명 사이즈
		vColIndex =1;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 150;
		col.setPreferredWidth(width);
		
		// 신발  사이즈
		vColIndex = 2;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		
		// 수량 사이즈
		vColIndex =3;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 40;
		col.setPreferredWidth(width);
		
		// 가격 사이즈
		vColIndex =4;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		
		btnCancel.setEnabled(false);
		btnUpdate.setEnabled(false);
		cbQty.setEnabled(false);
		cbQty.setSelectedIndex(0);
		
	}
	
	// table data
	private void searchAction() {
		beanList = new ArrayList<CartDto>();
		CartDao dao = new CartDao();
		
		beanList = dao.selectList(userid);
		int priceSum = 0;			// 총 합계
		int priceQty = 0;			// 상품별 수량 가격 합계
		
		int listCount = beanList.size(); // table data 갯수
	
		for(int i = 0; i < listCount; i++) {
			priceQty = beanList.get(i).getCartPrice() * beanList.get(i).getCartQty();
			
			
			ImageIcon icon = new ImageIcon("./" + beanList.get(i).getFilename());
			int x = 180;
			int y = 130;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon cartImage = resize.imageResizing();
			Object[] tempData = {cartImage, beanList.get(i).getName(), beanList.get(i).getCartSize() + "mm" ,beanList.get(i).getCartQty() + "개",
																		String.format("%,d", priceQty)+ "원"};
			priceSum += priceQty; 
			outerTable.addRow(tempData);
		}
		
		
		tfTotal.setText(String.format("%,d", priceSum)+ "원");
		lblTotal.setText("총 " + listCount + "개 합계:" );
		lblUser.setText(userid + " 님의 장바구니 입니다.");
		
	
	}
	
	// 장바구니 취소
	private void tableDelete() {
		int i = innerTable.getSelectedRow();
		
		CartDao dao = new CartDao(beanList.get(i).getCartNO());
		Boolean result = dao.deleteAction(userid);
		
		if(result) {
			JOptionPane.showMessageDialog(this,"장바구니 취소\n" + beanList.get(i).getName() + " 신발이 \n취소 되었습니다!", "장바구니 정보", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this,"장바구니 신발 삭제\n" +  "취소 중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	// 장바구니 비우기
	private void tableEmpty() {
		CartDao dao = new CartDao();
		int i = JOptionPane.showConfirmDialog(this, "장바구니를 비우시겠습니까???", "장바구니",JOptionPane.YES_NO_OPTION);
		boolean result =  dao.alldeleteAction(userid);
		if(i == 0) {
			if(result) {
				JOptionPane.showMessageDialog(this,"장바구니 비우기\n"  + "님의 장바구니가 비워졌습니다.!", "장바구니 정보", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this,"장바구니 비우기\n" +  "비우는 중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}	
		
	}
	
	// main 버튼
	private void redirectUserMain() {
		UserMain userMain = new UserMain();
		userMain.setUserid(userid);
		userMain.setVisible(true);
		dispose();
	}
	
	// 주문 버튼
	private void redirectPurchase() {
		PurchaseMain main = new PurchaseMain();
		main.setUserid(userid);
		main.setVisible(true);
		dispose();
	}
	
	// 수량 update
	private void tableUpdate() {
		int i = innerTable.getSelectedRow();
		int userqty = Integer.parseInt((String)cbQty.getSelectedItem());	// 사용자 선택한 수량
		// 업데이트
		
			CartDao dao = new CartDao(beanList.get(i).getCartNO(),userqty);
			Boolean result = dao.tableUpdate(userid);
			
			if(result) {
				JOptionPane.showMessageDialog(this,"상품 수량 변경\n" + beanList.get(i).getName() + "신발 \n수량이 변경되었습니다.", "상품 정보", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this,"상품 수량 변경\n" +  "변경 중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		
	
		
	}
	
	// table 클릭
	private void tableClick() {
		int i = innerTable.getSelectedRow();
		qty = beanList.get(i).getCartQty();
		
		btnCancel.setEnabled(true);
		btnUpdate.setEnabled(true);
		cbQty.setEnabled(true);

		
	}	
	

	
	
	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("");
			lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
			lblUser.setBounds(337, 10, 254, 16);
		}
		return lblUser;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(CartMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel.setBounds(254, 10, 93, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("상품을 선택 하시면 수정/취소를 할 수 있습니다.");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(94, 388, 227, 16);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("개");
			lblNewLabel_2.setBounds(233, 407, 19, 16);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblMain() {
		if (lblMain == null) {
			lblMain = new JLabel("");
			lblMain.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					redirectUserMain();
				}
			});
			//lblMain.setIcon(new ImageIcon(CartMain.class.getResource("/com/javalec/images/backArrow.png")));
			ImageIcon icon = new ImageIcon(ProductDetailMain.class.getResource("/com/javalec/images/backArrow.png"));
			int x = 20;
			int y = 20;
			
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon backArrow = resize.imageResizing();
			
			lblMain.setIcon(backArrow);
			lblMain.setBounds(6, 0, 41, 37);
		}
		return lblMain;
	}
	private JComboBox getCbQty() {
		if (cbQty == null) {
			cbQty = new JComboBox();
			cbQty.setEnabled(false);
			cbQty.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
			cbQty.setBounds(173, 403, 60, 27);
		}
		return cbQty;
	}
} // end

