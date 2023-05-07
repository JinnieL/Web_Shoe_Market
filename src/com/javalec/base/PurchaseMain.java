package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import com.javalec.dao.PurchaseDao;
import com.javalec.dto.PurchaseDto;
import com.javalec.funtion.ImageResize;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


public class PurchaseMain extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JButton BtnBuy;
	private JButton BtnCancellation;
	private JTable innerTable;
	private JLabel lblNewLabel;

	
	/* Field */
	String userid;
	
	
	/* Constructor */
	
	/* 로그인 유저를 가져올 getter & setter */
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	//  Table
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	ArrayList<PurchaseDto> beanList = null;


	
	private JButton BtnDelete;
	private JLabel lblUser;
	private JTextField tfTotal;
	private JLabel lblSum;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseMain frame = new PurchaseMain();
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
	public PurchaseMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();
				searchAction();
			}
		});
		setTitle("주문");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getBtnBuy());
		contentPane.add(getBtnCancellation());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnDelete());
		contentPane.add(getLblUser());
		contentPane.add(getTfTotal());
		contentPane.add(getLblSum());
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 45, 572, 276);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JButton getBtnBuy() {			// <<<<<<<<<<<<<<<<<<<<<<<<
		if (BtnBuy == null) {
			BtnBuy = new JButton("구매");
			BtnBuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuyAction();
				}
			});
			BtnBuy.setBounds(381, 366, 104, 29);
		}
		return BtnBuy;
	}
	private JButton getBtnCancellation() {
		if (BtnCancellation == null) {
			BtnCancellation = new JButton("장바구니");
			BtnCancellation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CancellationAction();
				}
			});
			BtnCancellation.setBounds(6, 366, 104, 29);
		}
		return BtnCancellation;
	}
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
			innerTable.setModel(outerTable);
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setRowHeight(150);
		}
		return innerTable;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(PurchaseMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel.setBounds(252, 6, 85, 27);
		}
		return lblNewLabel;
	}
	
	//--------------Function
	
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
		
		BtnDelete.setEnabled(false);
		lblUser.setText(userid + "님의 주문페이지 입니다.");
		
	}
	
	
	// table 데이터
	private void searchAction() {
		beanList = new ArrayList<PurchaseDto>();
		PurchaseDao dao = new PurchaseDao();
		beanList = dao.selectList(userid);
		int priceSum = 0;			// 총 합계
		int priceQty = 0;			// 상품별 수량 가격 합계
		
		int listCount = beanList.size(); // table data 갯수
	
		for(int i = 0; i < listCount; i++) {
			priceQty = beanList.get(i).getPurchassePrice() * beanList.get(i).getPurchasseQty();
			
			
			ImageIcon icon = new ImageIcon("./" + beanList.get(i).getFilename());
			int x = 180;
			int y = 130;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon cartImage = resize.imageResizing();
			Object[] tempData = {cartImage, beanList.get(i).getName(), beanList.get(i).getPurchasseSize() + "mm" ,beanList.get(i).getPurchasseQty() + "개",
																		String.format("%,d", priceQty)+ "원"};
			priceSum += priceQty; 
			outerTable.addRow(tempData);
		}
		
		tfTotal.setText(String.format("%,d", priceSum)+ "원");
		lblSum.setText("총 " + listCount + "개 합계:");
		
		
			
	}
	
	
	// table Click
	
	private void tableClick() {
		int i = innerTable.getSelectedRow();
		BtnDelete.setEnabled(true);
	

	}
	
	private void tableDelete() {
		int i = innerTable.getSelectedRow();
		
		PurchaseDao dao = new PurchaseDao(beanList.get(i).getPurchasseNO());
		Boolean result = dao.deleteAction(userid);
		
		if(result) {
			JOptionPane.showMessageDialog(this,"주문 취소\n" + beanList.get(i).getName() + " 주문이 \n취소 되었습니다!", "주문 정보", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this,"주문 신발 삭제\n" +  "주문 중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	
	
	// 구매 버튼 액션
		private void BuyAction() {
			PurchaseDao dao = new PurchaseDao();
			Boolean result = dao.updateQty();
			Boolean result1 = dao.addToPurchase();
			Boolean result2 = dao.alldeleteAction(userid);
			String[] buttonNaum = {"메인","주문내역"};
			if(result) {
				int i = JOptionPane.showOptionDialog(this, "상품이 구매 되었습니다!!", "구매완료", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttonNaum, buttonNaum[0]);
					if(i == 0) {
						UserMain userMain = new UserMain();
						userMain.setUserid(userid);
						userMain.setVisible(true);
						dispose();
					}else {
						PurchaseHistoryMain historyMain = new PurchaseHistoryMain();
						historyMain.setUserid(userid);
						historyMain.setVisible(true);
						dispose();
						
					}
			}else {
				JOptionPane.showMessageDialog(this,"구매오류\n" +  "구매 중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(result1 == false) {
				JOptionPane.showMessageDialog(this,"1관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(result2 == false) {
				JOptionPane.showMessageDialog(this,"2관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			dispose();

		}
	
	
	// 취소 버튼 액션 
		private void CancellationAction() {
			CartMain cm = new CartMain();
			cm.setUserid(userid);
			cm.setVisible(true);
			dispose();
		}
		
		
		
	private JButton getBtnDelete() {
		if (BtnDelete == null) {
			BtnDelete = new JButton("취소");
			BtnDelete.setEnabled(false);
			BtnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableDelete();
					tableInit();
					searchAction();
					
				}
			});
			BtnDelete.setBounds(480, 366, 104, 29);
		}
		return BtnDelete;
	}
	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel("");
			lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
			lblUser.setBounds(349, 17, 229, 16);
		}
		return lblUser;
	}
	private JTextField getTfTotal() {
		if (tfTotal == null) {
			tfTotal = new JTextField();
			tfTotal.setBounds(467, 333, 111, 26);
			tfTotal.setColumns(10);
		}
		return tfTotal;
	}
	private JLabel getLblSum() {
		if (lblSum == null) {
			lblSum = new JLabel("");
			lblSum.setHorizontalAlignment(SwingConstants.TRAILING);
			lblSum.setBounds(331, 338, 135, 16);
		}
		return lblSum;
	}
}// End
