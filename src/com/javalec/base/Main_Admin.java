package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.Dao_Admin;
import com.javalec.dto.Dto_Admin;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Main_Admin extends JFrame {

	private JPanel contentPane;
	private JRadioButton rbInsert;
	private JRadioButton rbUpdate;
	private JRadioButton rbDelete;
	private JRadioButton rbSearch;
	private JTextField textField;
	private JButton btnOK;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JLabel lblProductImage;
	private JLabel lblNewLabel_1_1_1;
	private JTextField textField_1;
	private JLabel lblNewLabel_1_1_1_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_1;
	private JTextField textField_3;
	private JLabel lblNewLabel_1_1;
	private JTextField textField_4;
	private JLabel lblNewLabel_1_1_2;
	private JTextField textField_5;
	private JLabel lblNewLabel_1_1_2_1;
	private JComboBox cbSize;
	private JLabel lblNewLabel_1_1_2_1_1;
	private JTextField textField_6;
	private JLabel lblNewLabel_1_1_2_2;
	private JTextField textField_7;
	private JButton btnComplete;

	
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	ArrayList<Dto_Admin> beanList = null; 		// 초기 테이블 요약 정보 받아올 리스트
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Admin frame = new Main_Admin();
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
	public Main_Admin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit(); 
				searchAction();
			}
		});
		setTitle("관리자 페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getRbInsert());
		contentPane.add(getRbUpdate());
		contentPane.add(getRbDelete());
		contentPane.add(getRbSearch());
		contentPane.add(getTextField());
		contentPane.add(getBtnOK());
		contentPane.add(getScrollPane());
		contentPane.add(getLblProductImage());
		contentPane.add(getLblNewLabel_1_1_1());
		contentPane.add(getTextField_1());
		contentPane.add(getLblNewLabel_1_1_1_1());
		contentPane.add(getTextField_2());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTextField_3());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getTextField_4());
		contentPane.add(getLblNewLabel_1_1_2());
		contentPane.add(getTextField_5());
		contentPane.add(getLblNewLabel_1_1_2_1());
		contentPane.add(getCbSize());
		contentPane.add(getLblNewLabel_1_1_2_1_1());
		contentPane.add(getTextField_6());
		contentPane.add(getLblNewLabel_1_1_2_2());
		contentPane.add(getTextField_7());
		contentPane.add(getBtnComplete());
	}
	private JRadioButton getRbInsert() {
		if (rbInsert == null) {
			rbInsert = new JRadioButton("입력");
			rbInsert.setBounds(8, 19, 60, 23);
		}
		return rbInsert;
	}
	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("수정");
			rbUpdate.setBounds(68, 19, 60, 23);
		}
		return rbUpdate;
	}
	private JRadioButton getRbDelete() {
		if (rbDelete == null) {
			rbDelete = new JRadioButton("삭제");
			rbDelete.setBounds(129, 19, 60, 23);
		}
		return rbDelete;
	}
	private JRadioButton getRbSearch() {
		if (rbSearch == null) {
			rbSearch = new JRadioButton("검색");
			rbSearch.setSelected(true);
			rbSearch.setBounds(189, 19, 60, 23);
		}
		return rbSearch;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(243, 20, 113, 21);
		}
		return textField;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.setBounds(358, 20, 59, 23);
		}
		return btnOK;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(8, 48, 414, 151);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.setSelectionModel(null);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}
	private JLabel getLblProductImage() {
		if (lblProductImage == null) {
			lblProductImage = new JLabel("");
			lblProductImage.setBounds(8, 221, 181, 182);
		}
		return lblProductImage;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("브랜드 코드");
			lblNewLabel_1_1_1.setBounds(205, 224, 89, 15);
		}
		return lblNewLabel_1_1_1;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(306, 221, 116, 21);
		}
		return textField_1;
	}
	private JLabel getLblNewLabel_1_1_1_1() {
		if (lblNewLabel_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1 = new JLabel("브랜드 명");
			lblNewLabel_1_1_1_1.setBounds(205, 252, 89, 15);
		}
		return lblNewLabel_1_1_1_1;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setColumns(10);
			textField_2.setBounds(306, 249, 116, 21);
		}
		return textField_2;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품 코드");
			lblNewLabel_1.setBounds(205, 280, 89, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setEditable(false);
			textField_3.setColumns(10);
			textField_3.setBounds(306, 277, 116, 21);
		}
		return textField_3;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("제품명");
			lblNewLabel_1_1.setBounds(205, 308, 89, 15);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTextField_4() {
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setEditable(false);
			textField_4.setColumns(10);
			textField_4.setBounds(306, 305, 116, 21);
		}
		return textField_4;
	}
	private JLabel getLblNewLabel_1_1_2() {
		if (lblNewLabel_1_1_2 == null) {
			lblNewLabel_1_1_2 = new JLabel("가격");
			lblNewLabel_1_1_2.setBounds(205, 338, 89, 15);
		}
		return lblNewLabel_1_1_2;
	}
	private JTextField getTextField_5() {
		if (textField_5 == null) {
			textField_5 = new JTextField();
			textField_5.setEditable(false);
			textField_5.setColumns(10);
			textField_5.setBounds(306, 335, 116, 21);
		}
		return textField_5;
	}
	private JLabel getLblNewLabel_1_1_2_1() {
		if (lblNewLabel_1_1_2_1 == null) {
			lblNewLabel_1_1_2_1 = new JLabel("사이즈");
			lblNewLabel_1_1_2_1.setBounds(205, 366, 43, 15);
		}
		return lblNewLabel_1_1_2_1;
	}
	private JComboBox getCbSize() {
		if (cbSize == null) {
			cbSize = new JComboBox();
			cbSize.setBounds(250, 363, 53, 23);
		}
		return cbSize;
	}
	private JLabel getLblNewLabel_1_1_2_1_1() {
		if (lblNewLabel_1_1_2_1_1 == null) {
			lblNewLabel_1_1_2_1_1 = new JLabel("재고량");
			lblNewLabel_1_1_2_1_1.setBounds(323, 366, 43, 15);
		}
		return lblNewLabel_1_1_2_1_1;
	}
	private JTextField getTextField_6() {
		if (textField_6 == null) {
			textField_6 = new JTextField();
			textField_6.setEditable(false);
			textField_6.setColumns(10);
			textField_6.setBounds(362, 363, 60, 21);
		}
		return textField_6;
	}
	private JLabel getLblNewLabel_1_1_2_2() {
		if (lblNewLabel_1_1_2_2 == null) {
			lblNewLabel_1_1_2_2 = new JLabel("입고날짜");
			lblNewLabel_1_1_2_2.setBounds(205, 394, 99, 15);
		}
		return lblNewLabel_1_1_2_2;
	}
	private JTextField getTextField_7() {
		if (textField_7 == null) {
			textField_7 = new JTextField();
			textField_7.setEditable(false);
			textField_7.setColumns(10);
			textField_7.setBounds(306, 391, 116, 21);
		}
		return textField_7;
	}
	private JButton getBtnComplete() {
		if (btnComplete == null) {
			btnComplete = new JButton("완료");
			btnComplete.setBounds(152, 435, 97, 23);
		}
		return btnComplete;
	}
	
	
	
	// ----------------------functions
	
	private void tableInit() {		// 테이블 디자인하기 - 초기화. 
		// outerTable - 뼈대 만들기
		outerTable.addColumn("순서");
		outerTable.addColumn("브랜드 명");
		outerTable.addColumn("제품명");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("재고");
		outerTable.setColumnCount(5);

		int i = outerTable.getRowCount(); 		// 현재 행 개수(데이터 개수) 세기
		for (int j=0; j<i; j++) {
			outerTable.removeRow(0); 		// 테이블 데이터 지우기
		}
		
		// Inner table : 데이터 보여주는 부분.
		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF); 		// 데이터 크기에 따라 열 크기가 변하게 하지 않겠다(off)
		
		int vColIndex = 0; 		// 순서에 대한 데이터 들어감.
		TableColumn col = innerTable.getColumnModel().getColumn(vColIndex) ;		// 제일 첫 번째 테이블 정의 
		int width = 30; 
		col.setPreferredWidth(width); 		// 첫번째 칼럼 폭 크기 정하기. 
		
		vColIndex = 1; 	
		col = innerTable.getColumnModel().getColumn(vColIndex) ;
		width = 120; 
		col.setPreferredWidth(width); 		// 두번째 칼럼 폭 크기 정하기.  (칼럼 폭은 실행해보면서 자기가 맞춰보는걸로) 
		
		vColIndex = 2; 		
		col =innerTable.getColumnModel().getColumn(vColIndex) ;
		width = 120; 
		col.setPreferredWidth(width); 	
		
		vColIndex = 3; 		
		col = innerTable.getColumnModel().getColumn(vColIndex) ;
		width = 100; 
		col.setPreferredWidth(width); 	
		
		vColIndex = 4; 		
		col = innerTable.getColumnModel().getColumn(vColIndex) ;
		width = 100; 
		col.setPreferredWidth(width); 
	}
	
	private void searchAction() { 		// 테이블에 입력할 데이터 다오에서 받아오기
		beanList = new ArrayList<Dto_Admin>();
		Dao_Admin dao = new Dao_Admin();
		beanList = dao.selectList();
		
		int listCount = beanList.size();
		
		for(int i = 0; i < listCount; i++) {
			String seqno = Integer.toString(i+1) ;// db에서 데이터 불러오는 순서 (나중의 조건절 검색을 위해 추가함)
			String brandName = beanList.get(i).getBrandName();
			String productName = beanList.get(i).getProductName();
			String size = Integer.toString(beanList.get(i).getSize());
			String stock = Integer.toString(beanList.get(i).getStock());
			
			String[] qTxt = {seqno, brandName, productName, size, stock};
			outerTable.addRow(qTxt);
		}
	
	
	
	
	
	}
	
	
	
	
	
	
	
}
