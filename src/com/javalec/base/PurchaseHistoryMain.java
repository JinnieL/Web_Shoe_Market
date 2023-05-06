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

public class PurchaseHistoryMain extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTable innerTable;
	
	private ImageIcon icon;
	private String userid = "donghyun";
	
	private ArrayList<PurchaseHistoryDto> beanList;
	
	
	/* Table */
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	

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
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 44, 688, 320);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(PurchaseHistoryMain.class.getResource("/com/javalec/images/logoSmall.png")));
			lblNewLabel.setBounds(236, 2, 80, 35);
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
			innerTable.setModel(outerTable);
			innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
			innerTable.setRowHeight(150);
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return innerTable;
	}
	
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
	
	private void redirectMain() {
		UserMain userMain = new UserMain();
		userMain.setVisible(true);
		dispose();
	}







}	// End Class

