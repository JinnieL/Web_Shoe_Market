package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.AdminDao;
import com.javalec.dao.ProductDao;
import com.javalec.dto.AdminDto;
import com.javalec.funtion.ImageResize;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdmianMain extends JFrame {

	private JPanel contentPane;
	private JRadioButton rbInsert;
	private JRadioButton rbUpdate;
	private JRadioButton rbDelete;
	private JRadioButton rbSearch;
	private JTextField tfSearch;
	private JButton btnOK;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JLabel lblProductImage;
	private JLabel lblNewLabel_1_1_1;
	private JTextField tfBrandNo;
	private JLabel lblNewLabel_1_1_1_1;
	private JTextField tfBrandName;
	private JLabel lblNewLabel_1;
	private JTextField tfProductCode;
	private JLabel lblNewLabel_1_1;
	private JTextField tfProductName;
	private JLabel lblNewLabel_1_1_2;
	private JTextField tfProductPrice;
	private JLabel lblNewLabel_1_1_2_1;
	private JComboBox cbSize;
	private JLabel lblNewLabel_1_1_2_1_1;
	private JTextField tfProductStock;
	private JLabel lblNewLabel_1_1_2_2;
	private JTextField tfInsertdate;
	private JButton btnComplete;
	
	private int productCode;
	private int size;

	String message = ""; 	// 사용자가 입력하지 않은 부분 체크해줄 메시지 
	
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	ArrayList<AdminDto> beanList = null; 		// 초기 테이블 요약 정보 받아올 리스트
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbSearch;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdmianMain frame = new AdmianMain();
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
	public AdmianMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit(); 
				searchAction();
			}
		});
		setTitle("관리자 페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getRbInsert());
		contentPane.add(getRbUpdate());
		contentPane.add(getRbDelete());
		contentPane.add(getRbSearch());
		contentPane.add(getTfSearch());
		contentPane.add(getBtnOK());
		contentPane.add(getScrollPane());
		contentPane.add(getLblProductImage());
		contentPane.add(getLblNewLabel_1_1_1());
		contentPane.add(getTfBrandNo());
		contentPane.add(getLblNewLabel_1_1_1_1());
		contentPane.add(getTfBrandName());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTfProductCode());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getTfProductName());
		contentPane.add(getLblNewLabel_1_1_2());
		contentPane.add(getTfProductPrice());
		contentPane.add(getLblNewLabel_1_1_2_1());
		contentPane.add(getCbSize());
		contentPane.add(getLblNewLabel_1_1_2_1_1());
		contentPane.add(getTfProductStock());
		contentPane.add(getLblNewLabel_1_1_2_2());
		contentPane.add(getTfInsertdate());
		contentPane.add(getBtnComplete());
		contentPane.add(getCbSearch());
	}
	private JRadioButton getRbInsert() {
		if (rbInsert == null) {
			rbInsert = new JRadioButton("입력");
			rbInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbInsert);
			rbInsert.setBounds(8, 19, 60, 23);
		}
		return rbInsert;
	}
	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("수정");
			rbUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
					
				}
			});
			buttonGroup.add(rbUpdate);
			rbUpdate.setBounds(68, 19, 60, 23);
		}
		return rbUpdate;
	}
	private JRadioButton getRbDelete() {
		if (rbDelete == null) {
			rbDelete = new JRadioButton("삭제");
			rbDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbDelete);
			rbDelete.setBounds(129, 19, 60, 23);
		}
		return rbDelete;
	}
	private JRadioButton getRbSearch() {
		if (rbSearch == null) {
			rbSearch = new JRadioButton("검색");
			rbSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbSearch);
			rbSearch.setSelected(true);
			rbSearch.setBounds(189, 19, 60, 23);
		}
		return rbSearch;
	}
	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setColumns(10);
			tfSearch.setBounds(373, 18, 200, 21);
		}
		return tfSearch;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnOK.setBounds(585, 19, 59, 23);
		}
		return btnOK;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(8, 48, 636, 288);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}
	private JLabel getLblProductImage() {
		if (lblProductImage == null) {
			lblProductImage = new JLabel("");
			lblProductImage.setBounds(8, 348, 284, 219);
		}
		return lblProductImage;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("브랜드 코드");
			lblNewLabel_1_1_1.setBounds(304, 348, 89, 15);
		}
		return lblNewLabel_1_1_1;
	}
	private JTextField getTfBrandNo() {
		if (tfBrandNo == null) {
			tfBrandNo = new JTextField();
			tfBrandNo.setEditable(false);
			tfBrandNo.setColumns(10);
			tfBrandNo.setBounds(405, 345, 116, 21);
		}
		return tfBrandNo;
	}
	private JLabel getLblNewLabel_1_1_1_1() {
		if (lblNewLabel_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1 = new JLabel("브랜드 명");
			lblNewLabel_1_1_1_1.setBounds(304, 376, 89, 15);
		}
		return lblNewLabel_1_1_1_1;
	}
	private JTextField getTfBrandName() {
		if (tfBrandName == null) {
			tfBrandName = new JTextField();
			tfBrandName.setEditable(false);
			tfBrandName.setColumns(10);
			tfBrandName.setBounds(405, 373, 116, 21);
		}
		return tfBrandName;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품 코드");
			lblNewLabel_1.setBounds(304, 404, 89, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfProductCode() {
		if (tfProductCode == null) {
			tfProductCode = new JTextField();
			tfProductCode.setEditable(false);
			tfProductCode.setColumns(10);
			tfProductCode.setBounds(405, 401, 116, 21);
		}
		return tfProductCode;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("제품명");
			lblNewLabel_1_1.setBounds(304, 432, 89, 15);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTfProductName() {
		if (tfProductName == null) {
			tfProductName = new JTextField();
			tfProductName.setEditable(false);
			tfProductName.setColumns(10);
			tfProductName.setBounds(405, 429, 181, 21);
		}
		return tfProductName;
	}
	private JLabel getLblNewLabel_1_1_2() {
		if (lblNewLabel_1_1_2 == null) {
			lblNewLabel_1_1_2 = new JLabel("가격");
			lblNewLabel_1_1_2.setBounds(304, 462, 89, 15);
		}
		return lblNewLabel_1_1_2;
	}
	private JTextField getTfProductPrice() {
		if (tfProductPrice == null) {
			tfProductPrice = new JTextField();
			tfProductPrice.setEditable(false);
			tfProductPrice.setColumns(10);
			tfProductPrice.setBounds(405, 459, 116, 21);
		}
		return tfProductPrice;
	}
	private JLabel getLblNewLabel_1_1_2_1() {
		if (lblNewLabel_1_1_2_1 == null) {
			lblNewLabel_1_1_2_1 = new JLabel("사이즈");
			lblNewLabel_1_1_2_1.setBounds(304, 490, 43, 15);
		}
		return lblNewLabel_1_1_2_1;
	}
	private JComboBox getCbSize() {
		if (cbSize == null) {
			cbSize = new JComboBox();
			cbSize.setModel(new DefaultComboBoxModel(new String[] {"170", "180", "190", "200", "210", "220", "230", "240", "250", "255", "260", "265", "270", "275", "280", "290", "300"}));
			cbSize.setBounds(349, 487, 76, 23);
		}
		return cbSize;
	}
	private JLabel getLblNewLabel_1_1_2_1_1() {
		if (lblNewLabel_1_1_2_1_1 == null) {
			lblNewLabel_1_1_2_1_1 = new JLabel("재고량");
			lblNewLabel_1_1_2_1_1.setBounds(475, 493, 43, 15);
		}
		return lblNewLabel_1_1_2_1_1;
	}
	private JTextField getTfProductStock() {
		if (tfProductStock == null) {
			tfProductStock = new JTextField();
			tfProductStock.setEditable(false);
			tfProductStock.setColumns(10);
			tfProductStock.setBounds(514, 490, 60, 21);
		}
		return tfProductStock;
	}
	private JLabel getLblNewLabel_1_1_2_2() {
		if (lblNewLabel_1_1_2_2 == null) {
			lblNewLabel_1_1_2_2 = new JLabel("입고날짜");
			lblNewLabel_1_1_2_2.setBounds(304, 518, 99, 15);
		}
		return lblNewLabel_1_1_2_2;
	}
	private JTextField getTfInsertdate() {
		if (tfInsertdate == null) {
			tfInsertdate = new JTextField();
			tfInsertdate.setEditable(false);
			tfInsertdate.setColumns(10);
			tfInsertdate.setBounds(405, 515, 116, 21);
		}
		return tfInsertdate;
	}
	private JButton getBtnComplete() {
		if (btnComplete == null) {
			btnComplete = new JButton("완료");
			btnComplete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPartition();
				}
			});
			btnComplete.setBounds(261, 633, 97, 23);
		}
		return btnComplete;
	}
	
	private JComboBox getCbSearch() {
		if (cbSearch == null) {
			cbSearch = new JComboBox();
			cbSearch.setModel(new DefaultComboBoxModel(new String[] {"브랜드 이름", "제품 명"}));
			cbSearch.setBounds(245, 17, 116, 27);
		}
		return cbSearch;
	}
	
	// ----------------------functions
	
	private void tableInit() {		// 테이블 디자인하기 - 초기화. 
		// outerTable - 뼈대 만들기
		outerTable.addColumn("No");
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
		beanList = new ArrayList<AdminDto>();
		AdminDao dao = new AdminDao();
		beanList = dao.selectList();
		
		int listCount = beanList.size();
		
		for(int i = 0; i < listCount; i++) {
			String productCode = Integer.toString(beanList.get(i).getProductCode());// db에서 데이터 불러오는 순서 (나중의 조건절 검색을 위해 추가함)
			String brandName = beanList.get(i).getBrandName();
			String productName = beanList.get(i).getProductName();
			String size = Integer.toString(beanList.get(i).getSize());
			String stock = Integer.toString(beanList.get(i).getProductStock());
			
			String[] qTxt = {productCode, brandName, productName, size, stock};
			outerTable.addRow(qTxt);
		}
	
	}
	
	/* 테이블 클릭했을 때 실행되는 메소드 */
	private void tableClick() {
		int i = innerTable.getSelectedRow();
		productCode = Integer.parseInt((String)innerTable.getValueAt(i, 0));
		size = Integer.parseInt((String)innerTable.getValueAt(i, 3));
		AdminDao adminDao = new AdminDao();
		beanList = adminDao.queryAction(size, productCode);
		int listCount = beanList.size();
		
		try {
			for(int j=0; j<listCount; j++) {
				ImageIcon icon = new ImageIcon("./" + beanList.get(j).getProductImageName());
				int x = 250;
				int y = 150;
				ImageResize resize = new ImageResize(icon, x, y);
				ImageIcon productIcon = resize.imageResizing();
				
				/* formatter 변경 (날씨) */
				String date = beanList.get(j).getDate();
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = inputFormat.parse(date);
				String formattedDate = outputFormat.format(parsedDate);
		        
				tfBrandNo.setText(Integer.toString(beanList.get(j).getBrandNo()));
				tfBrandName.setText(beanList.get(j).getBrandName());
				tfProductCode.setText(Integer.toString(beanList.get(j).getProductCode()));
				tfProductName.setText(beanList.get(j).getProductName());
				lblProductImage.setIcon(productIcon);
				tfProductPrice.setText(Integer.toString(beanList.get(j).getProductPrice()));
				tfProductStock.setText(Integer.toString(beanList.get(j).getProductStock()));
				tfInsertdate.setText(formattedDate);
				cbSize.setSelectedItem(Integer.toString(beanList.get(j).getSize()));
				
			}
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	// 검색어 입력할 때
	private void conditionQuery() {
		int i = cbSearch.getSelectedIndex();
		String conditionQueryColumn = "";
		switch (i) {
		case 0 : 
			conditionQueryColumn = "brandName";
			break;
		case 1 : 
			conditionQueryColumn = "productName";
			break;
		default : 
			break;
		}
		tableInit() ; 		// 테이블 초기화
		clearColumn();		// 컬럼 내용 삭제
		conditionQueryAction(conditionQueryColumn); 
	}
	
	private void clearColumn() {
		tfBrandNo.setText("");
		tfBrandName.setText("");
		tfProductCode.setText("");
		tfProductName.setText("");
		tfProductPrice.setText("");
		tfProductStock.setText("");
		tfInsertdate.setText("");
		
	}
	
	
	// 사용자가 입력한 조건 검색
	private void conditionQueryAction(String conditionQueryColumn) {
		AdminDao admindao = new AdminDao(conditionQueryColumn, tfSearch.getText());
		ArrayList <AdminDto> dtoList = admindao.conditionList();
		int listCount = dtoList.size();
	
		for(int i = 0; i < listCount; i++) {
			String productCode = Integer.toString(beanList.get(i).getProductCode());// db에서 데이터 불러오는 순서 (나중의 조건절 검색을 위해 추가함)
			String brandName = beanList.get(i).getBrandName();
			String productName = beanList.get(i).getProductName();
			String size = Integer.toString(beanList.get(i).getSize());
			String stock = Integer.toString(beanList.get(i).getProductStock());
			
			String[] qTxt = {productCode, brandName, productName, size, stock};
			outerTable.addRow(qTxt);
		}
				
		
	}
	
	private void insertAction() { 		// <<<<브랜드 코드, 이름 다를 때 체크할 과정 있어야 할 듯
		int brandNo = Integer.parseInt(tfBrandNo.getText());
		String brandName = tfBrandName.getText();
		int productCode = Integer.parseInt(tfProductCode.getText());
		String productName = tfProductName.getText();
		int productPrice = Integer.parseInt(tfProductPrice.getText());
		int size = Integer.parseInt(cbSize.getSelectedItem().toString());
		int productStock = Integer.parseInt(tfProductStock.getText());
		tfInsertdate.setEditable(false);
		tfInsertdate.setVisible(false);
		
		AdminDao admindao = new AdminDao(brandNo, brandName, productCode, productName, productPrice, size, productStock);
		boolean result = admindao.insertAction();
		
		if (result) {
			JOptionPane.showMessageDialog(this, "제품 정보 입력이 정상적으로 처리되었습니다.", "제품 등록", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "제품 입력에 오류가 발생했습니다. \n관리자에게 문의하세요.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	private void updateAction() {
		int brandNo = Integer.parseInt(tfBrandNo.getText());
		String brandName = tfBrandName.getText();
		int productCode = Integer.parseInt(tfProductCode.getText());
		String productName = tfProductName.getText();
		int productPrice = Integer.parseInt(tfProductPrice.getText());
		int size = Integer.parseInt(cbSize.getSelectedItem().toString());
		int productStock = Integer.parseInt(tfProductStock.getText());
		tfInsertdate.setEditable(false);
		tfInsertdate.setVisible(false);
		
		AdminDao admindao = new AdminDao(brandNo, brandName, productCode, productName, productPrice, size, productStock);
		boolean result = admindao.updateAction();
		
		if (result) {
			JOptionPane.showMessageDialog(this, "제품 정보 수정이 정상적으로 처리되었습니다.", "제품 정보 수정", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "수정 작업에 오류가 발생했습니다. \n관리자에게 문의하세요.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	private void deleteAction() {
		int brandNo = Integer.parseInt(tfBrandNo.getText());
		String brandName = tfBrandName.getText();
		int productCode = Integer.parseInt(tfProductCode.getText());
		String productName = tfProductName.getText();
		int productPrice = Integer.parseInt(tfProductPrice.getText());
		int size = Integer.parseInt(cbSize.getSelectedItem().toString());
		int productStock = Integer.parseInt(tfProductStock.getText());
		tfInsertdate.setEditable(false);
		tfInsertdate.setVisible(false);
		
		AdminDao admindao = new AdminDao(brandNo, brandName, productCode, productName, productPrice, size, productStock);
		boolean result = admindao.insertAction();
		
		if (result) {
			JOptionPane.showMessageDialog(this, "제품 정보 입력이 정상적으로 처리되었습니다.", "제품 등록", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "제품 입력에 오류가 발생했습니다. \n관리자에게 문의하세요.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	private int insertFieldCheck() {
		int i=0;
		if(tfBrandNo.getText().length() == 0) {
			i++;
			message = "브랜드 코드";
			tfBrandNo.requestFocus();
		}
		if(tfBrandName.getText().length() == 0) {
			i++;
			message = "브랜드 이름";
			tfBrandName.requestFocus();
		}
		if(tfProductCode.getText().length() == 0) {
			i++;
			message = "제품 코드";
			tfProductCode.requestFocus();
		}
		if(tfProductName.getText().length() == 0) {
			i++;
			message = "제품 이름";
			tfProductName.requestFocus();
		}
		if(tfProductPrice.getText().length() == 0) {
			i++;
			message = "가격";
			tfProductPrice.requestFocus();
		}
		if(tfProductStock.getText().length() == 0) {
			i++;
			message = "재고량";
			tfProductStock.requestFocus();
		}
		return i;
	}
	
	private void screenPartition() {
		// 입력일 경우
		if(rbInsert.isSelected()) {
			tfBrandNo.setEditable(true);
			tfBrandName.setEditable(true);
			tfProductCode.setEditable(true);
			tfProductName.setEditable(true);
			tfProductPrice.setEditable(true);
			tfProductStock.setEditable(true);
			btnComplete.setVisible(true);
			btnComplete.setEnabled(true);
		}
		
		// 검색일 경우
		if(rbSearch.isSelected()) {
			tfBrandNo.setEditable(false);
			tfBrandName.setEditable(false);
			tfProductCode.setEditable(false);
			tfProductName.setEditable(false);
			tfProductPrice.setEditable(false);
			tfProductStock.setEditable(false);
			btnComplete.setVisible(false);
			btnComplete.setEnabled(false);
		}
		
		// 수정 또는 삭제일 경우
		if(rbUpdate.isSelected() || rbDelete.isSelected()) {
			tfBrandNo.setEditable(false);
			tfBrandName.setEditable(false);
			tfProductCode.setEditable(false);
			tfProductName.setEditable(false);
			tfProductPrice.setEditable(false);
			tfProductStock.setEditable(false);
			btnComplete.setVisible(true);
			btnComplete.setEnabled(true);
		}
		
	}
	
	private void actionPartition() {
		// 제품을 입력할 경우
		if (rbInsert.isSelected()) {
			int i_chk = insertFieldCheck();
			if (i_chk == 0) {
				insertAction();
				tableInit();
				searchAction();
				clearColumn();
			} else {
				JOptionPane.showMessageDialog(this, message + " 입력정보를 확인하세요!", "제품등록", JOptionPane.INFORMATION_MESSAGE );
			}
		}
		
		// 제품 수정할 경우
		if (rbUpdate.isSelected()) {
			int i_chk = insertFieldCheck();
			if (i_chk == 0) {
				updateAction();
				tableInit();
				searchAction();
				clearColumn();
			} else {
				JOptionPane.showMessageDialog(this, message + " 수정할 정보를 확인하세요!", "수정", JOptionPane.INFORMATION_MESSAGE );
			}
		}
		
		// 제품 삭제할 경우
		if (rbDelete.isSelected()) {
			int i_chk = insertFieldCheck();
			if (i_chk == 0) {
				deleteAction();
				tableInit();
				searchAction();
				clearColumn();
			} else {
				JOptionPane.showMessageDialog(this,"제품 삭제에 실패했습니다. \n관리자에게 문의하세요", "삭제", JOptionPane.INFORMATION_MESSAGE );
			}
		}
		
		
		
		
		
		
		
			
	}
	
}
