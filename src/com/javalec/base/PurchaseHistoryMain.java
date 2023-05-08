package com.javalec.base;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.ProductDao;
import com.javalec.dao.PurchaseHistoryDao;
import com.javalec.dto.ProductDto;
import com.javalec.dto.PurchaseHistoryDto;
import com.javalec.funtion.ImageResize;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PurchaseHistoryMain extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTable innerTable;
	
	private ImageIcon icon;
	private String userid;
	private int purchaseNo;
	
	private ArrayList<PurchaseHistoryDto> beanList;
	
	
	/* Table */
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnCancle;
	private JLabel lblLoginUser;
	
	
	/* User ID를 가져올 getter & setter */
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
					PurchaseHistoryMain frame = new PurchaseHistoryMain();
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
	public PurchaseHistoryMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();
				queryAction();
			}
		});
		setTitle("주문 내역");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getBtnCancle());
		contentPane.add(getLblLoginUser());
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 44, 658, 320);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(PurchaseHistoryMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel.setBounds(279, 1, 80, 35);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					redirectMain();
				}
			});
			ImageIcon icon = new ImageIcon(PurchaseHistoryMain.class.getResource("/com/javalec/images/backArrow.png"));
			int x = 30;
			int y = 30;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon backArrow = resize.imageResizing();
			lblNewLabel_1.setIcon(backArrow);
			lblNewLabel_1.setBounds(17, 6, 56, 35);
		}
		return lblNewLabel_1;
	}
	/********* InnerTable *********/
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable() {
				public Class getColumnClass(int column) {
					return (column == 2) ? Icon.class : Object.class;
				}
			};
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innerTable.setModel(outerTable);
			innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
			innerTable.setRowHeight(150);
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return innerTable;
	}
	
	private JButton getBtnCancle() {
		if (btnCancle == null) {
			btnCancle = new JButton("주문 취소하기");
			btnCancle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    int result = JOptionPane.showConfirmDialog(null, "주문을 취소하시겠습니까?", "주문 취소", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    /* 주문 취소 물어보기 */
				    if (result == JOptionPane.YES_OPTION) {
				        cancleAction();
				        tableInit();
				        queryAction();
				        btnCancle.setEnabled(false);
				    } else {
				    	tableInit();
				    	queryAction();
				    	btnCancle.setEnabled(false);
				    }
				}
			});
			btnCancle.setEnabled(false);
			btnCancle.setBounds(6, 376, 117, 57);
		}
		return btnCancle;
	}
	
	private JLabel getLblLoginUser() {
		if (lblLoginUser == null) {
			lblLoginUser = new JLabel("");
			lblLoginUser.setHorizontalAlignment(SwingConstants.TRAILING);
			lblLoginUser.setBounds(443, 10, 187, 16);
		}
		return lblLoginUser;
	}
	
	/******************* Functions *******************/
	
	/* 01. 창이 열리면 테이블을 정리해주는 메소드 */
	private void tableInit() {
		outerTable.addColumn("주문날짜");
		outerTable.addColumn("주문번호");
		outerTable.addColumn("상품사진");
		outerTable.addColumn("상품명");
		outerTable.addColumn("수량");
		outerTable.addColumn("주문금액");
		outerTable.setColumnCount(6);
		
		int i = outerTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerTable.removeRow(0);
		}
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	    innerTable.setDefaultRenderer(String.class, centerRenderer);
		
	    /* 주문날짜 컬럼 크기*/
		int vColIndex = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(vColIndex);
		int width = 100;
		col.setPreferredWidth(width);
		
		/* 주문번호 컬럼 크기 */
		vColIndex = 1;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 50;
		col.setPreferredWidth(width);
		
		/* 상품사진 컬럼 크기 */
		vColIndex = 2;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 250;
		col.setPreferredWidth(width);
		
		/* 상품명 컬럼 크기 */
		vColIndex = 3;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 130;
		col.setPreferredWidth(width);
		
		/* 주문갯수 컬럼 크기 */
		vColIndex = 4;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 40;
		col.setPreferredWidth(width);
		
		/* 주문금액 컬럼 크기 */
		vColIndex = 5;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 60;
		col.setPreferredWidth(width);
		
		lblLoginUser.setText(userid + "님의 주문 현황");
		
		
		System.out.println("01. Method Pass");
	}

	
	private void queryAction() {
		beanList = new ArrayList<PurchaseHistoryDto>();
		PurchaseHistoryDao historyDao = new PurchaseHistoryDao();
		beanList = historyDao.getPurchaseHistory(userid);
		int listCount = beanList.size();
		
		for(int i=0; i<listCount; i++) {
	        try {
			String date = beanList.get(i).getPurchaseInsertdate();
	        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = inputFormat.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
	        String formattedDate = outputFormat.format(parsedDate);
			icon = new ImageIcon("./" + beanList.get(i).getProductImageName());
			System.out.println("./" + beanList.get(i).getProductImageName());
			int x = 250;
			int y = 150;
			ImageResize resize = new ImageResize(icon, x, y);
			ImageIcon productIcon = resize.imageResizing();
			int wkQty = beanList.get(i).getPurchaseQty();
			int wkPrice = beanList.get(i).getProductPrice();
			String wkReuslt = Integer.toString((wkPrice * wkQty));
			Object[] tempData = {formattedDate, beanList.get(i).getPurchaseNo(), productIcon, beanList.get(i).getProductName(), beanList.get(i).getPurchaseQty(), wkReuslt};
			outerTable.addRow(tempData);
	        } catch(ParseException e) {
	        	e.printStackTrace();
	        }
	        

		}
	}
	
	/* 03. 메인으로 리다이렉트 */
	private void redirectMain() {
		UserMain userMain = new UserMain();
		userMain.setUserid(userid);
		userMain.setVisible(true);
		dispose();
	}
	
	/* 04. 테이블 클릭 */
	private void tableClick() {
		int i = innerTable.getSelectedRow();
		purchaseNo = (int)innerTable.getValueAt(i, 1);
		btnCancle.setEnabled(true);
	}
	
	private void cancleAction() {
		PurchaseHistoryDao historyDao = new PurchaseHistoryDao();
		boolean result = historyDao.canclePurchase(purchaseNo);
		if(result == true) {
			JOptionPane.showMessageDialog(null, "주문이 취소 되었습니다.");
		} else {
			JOptionPane.showMessageDialog(this, "잘못된 접근입니다.");
		}
		
	}

}	// End Class

