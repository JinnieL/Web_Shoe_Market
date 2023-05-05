package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.CartDao;
import com.javalec.dto.CartDto;
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

public class CartMain extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JLabel lblTotal;
	private JTextField tfTotal;
	private JButton btnEmpty;
	private JButton btnOrder;
	private JButton btnModify;
	private JButton btnCancel;
	private JButton btnMain;
	
	// Table
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	ArrayList<CartDto> beanList = null;
	private JLabel lblUser;
	private JLabel lblNewLabel;
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {	// windowopend
				tableInit();	// table 정리
				searchAction();	// table 내용
			}
		});
		setTitle("Cart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getLblTotal());
		contentPane.add(getTfTotal());
		contentPane.add(getBtnEmpty());
		contentPane.add(getBtnOrder());
		contentPane.add(getBtnModify());
		contentPane.add(getBtnCancel());
		contentPane.add(getBtnMain());
		contentPane.add(getLblUser());
		contentPane.add(getLblNewLabel());
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 38, 490, 223);
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
			lblTotal.setBounds(240, 270, 124, 16);
		}
		return lblTotal;
	}
	private JTextField getTfTotal() {
		if (tfTotal == null) {
			tfTotal = new JTextField();
			tfTotal.setHorizontalAlignment(SwingConstants.TRAILING);
			tfTotal.setEditable(false);
			tfTotal.setBounds(366, 265, 130, 26);
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
			btnEmpty.setBounds(370, 298, 130, 29);
		}
		return btnEmpty;
	}
	private JButton getBtnOrder() {
		if (btnOrder == null) {
			btnOrder = new JButton("주문");
			btnOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnOrder.setBounds(0, 298, 85, 29);
		}
		return btnOrder;
	}
	private JButton getBtnModify() {
		if (btnModify == null) {
			btnModify = new JButton("수량수정");
			btnModify.setEnabled(false);
			btnModify.setBounds(82, 298, 85, 29);
		}
		return btnModify;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("취소");
			btnCancel.setEnabled(false);
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableDelete();
					tableInit();
					searchAction();
				}
			});
			btnCancel.setBounds(164, 298, 85, 29);
		}
		return btnCancel;
	}
	private JButton getBtnMain() {
		if (btnMain == null) {
			btnMain = new JButton("메인");
			btnMain.setBounds(246, 298, 85, 29);
		}
		return btnMain;
	}
	
	// ================= function ===================
	
	
	// table 초기 작업
	private void tableInit() {
		outerTable.addColumn("상품사진");	// 타이틀 네임
		outerTable.addColumn("상품명");
		outerTable.addColumn("수량");
		outerTable.addColumn("가격");		
		outerTable.setColumnCount(4);		// 타이틀이 몇개냐
	
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
		
		// 수량 사이즈
		vColIndex =2;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 40;
		col.setPreferredWidth(width);
		
		// 가격 사이즈
		vColIndex =3;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		
	}
	
	// table data
	private void searchAction() {
		beanList = new ArrayList<CartDto>();
		CartDao dao = new CartDao();
		beanList = dao.selectList();
		int priceSum = 0;			// 총 합계
		int priceQty = 0;			// 상품별 수량 가격 합계
		
		int listCount = beanList.size(); // table data 갯수
		System.out.println(listCount);
		
		for(int i = 0; i < listCount; i++) {
			priceQty = beanList.get(i).getCartPrice() * beanList.get(i).getCartQty();
			
			ImageIcon icon = new ImageIcon("./" + beanList.get(i).getName());
			System.out.println("./" + beanList.get(i).getName());
			Object[] tempData = {icon, beanList.get(i).getName(), beanList.get(i).getCartQty() + "개",
																	priceQty + "원"};
			priceSum += priceQty; 
			outerTable.addRow(tempData);
		}
		
		tfTotal.setText(Integer.toString(priceSum)+ "원");
		lblTotal.setText("총 " + listCount + "개 합계:" );
		lblUser.setText( " 님의 장바구니 입니다.");
	
	}
	
	
	private void tableDelete() {
		int i = innerTable.getSelectedRow();
		
		CartDao dao = new CartDao(beanList.get(i).getCartNO());
		Boolean result = dao.deleteAction();
		
		if(result) {
			JOptionPane.showMessageDialog(this,"장바구니 취소\n" +  "신발이 취소 되었습니다!", "장바구니 정보", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this,"장바구니 신발 삭제\n" +  "취소 중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private void tableEmpty() {
		CartDao dao = new CartDao();
		int i = JOptionPane.showConfirmDialog(this, "장바구니를 비우시겠습니까???", "장바구니",JOptionPane.YES_NO_OPTION);
		boolean result =  dao.alldeleteAction();
		if(i == 0) {
			if(result) {
				JOptionPane.showMessageDialog(this,"장바구니 비우기\n"  + "님의 장바구니가 비워졌습니다.!", "장바구니 정보", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this,"장바구니 비우기\n" +  "비우는 중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			
		}
		
	}
	
	
	
	
	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("");
			lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
			lblUser.setBounds(185, 10, 311, 16);
		}
		return lblUser;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(CartMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel.setBounds(6, 10, 79, 16);
		}
		return lblNewLabel;
	}
} // end

