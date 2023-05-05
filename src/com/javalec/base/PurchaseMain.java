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
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.javalec.funtion.BuyAction;


public class PurchaseMain extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JButton BtnBuy;
	private JButton BtnCancellation;
	private JTable innerTable;
	private JLabel lblNewLabel;

	
	
	
	
	//  Table
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	
	
	
	
	
	
	
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
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 45, 572, 276);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JButton getBtnBuy() {
		if (BtnBuy == null) {
			BtnBuy = new JButton("구매");
			BtnBuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuyAction();
				}
			});
			BtnBuy.setBounds(113, 365, 117, 29);
		}
		return BtnBuy;
	}
	private JButton getBtnCancellation() {
		if (BtnCancellation == null) {
			BtnCancellation = new JButton("취소");
			BtnCancellation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CancellationAction();
				}
			});
			BtnCancellation.setBounds(387, 365, 117, 29);
		}
		return BtnCancellation;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.setModel(outerTable);
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
	
	private void tableInit() {
		outerTable.addColumn("신발");
		outerTable.addColumn("이름");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("수량");
		outerTable.addColumn("가격");
		outerTable.setColumnCount(5);    // 표의 열을 표기 
		
		int i = outerTable.getRowCount();   // 표의 행을 표기
		for(int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}
		
		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
		
		
		// 물품 이미지 
		int vColIndex = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(vColIndex);
		int width = 200;
		col.setPreferredWidth(width);
		
		
		// 상품명 
		vColIndex = 1;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 150;
		col.setPreferredWidth(width);

		
		// 상품 사이즈 
		vColIndex = 2;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
		
		
		// 상품 수량
		vColIndex = 3;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 35;
		col.setPreferredWidth(width);

		
		// 상품  가격
		vColIndex = 4;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);
	}
	
	// 구매 버튼 액션
		private void BuyAction() {
			BuyAction ba = new BuyAction();
			ba.setVisible(true);
		}
	
	
	// 취소 버튼 액션 
		private void CancellationAction() {
			CartMain cm = new CartMain();
			cm.setVisible(true);
		}
		
	
	
	
	
	
	
	
	
	
	
}// End
