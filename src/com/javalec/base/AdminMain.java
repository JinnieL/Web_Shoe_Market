package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.AdminDao;
import com.javalec.dto.AdminDto;
import com.javalec.util.ShareVar;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMain extends JFrame {

	private JPanel contentPane;
	private JScrollPane ScrollPane;
	private JRadioButton rbInsert;
	private JRadioButton rbUpdate;
	private JRadioButton rbDelete;
	private JRadioButton rbSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfSearch;
	private JButton btnOK;
	private JLabel lblProductImage;
	private JTable innerTable;
	private JLabel lblNewLabel_1;
	private JTextField tfBrandNo;
	private JLabel lblNewLabel_1_1;
	private JTextField tfBrandName;
	private JTextField tfProductCode;
	private JTextField tfProductName;
	private JLabel lblNewLabel_1_1_1;
	private JLabel lblNewLabel_1_1_1_1;
	private JLabel lblNewLabel_1_1_2;
	private JTextField tfProductPrice;
	private JLabel lblNewLabel_1_1_2_1;
	private JLabel lblNewLabel_1_1_2_1_1;
	private JTextField tfProductStock;
	private JLabel lblNewLabel_1_1_2_2;
	private JTextField tfProductInsertdate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JComboBox cbSize;
	private JButton btnInsert;

	// 데이터베이스 위치, 아이디, 비밀번호 연결하기
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;	
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	

	String message = ""; 		// 사용자가 입력하지 않은 데이터 알려주기
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain frame = new AdminMain();
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
	public AdminMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit(); 		// table initialize. 검색 초기 화면
				searchAction(); 		// DB가서 데이터 불러오기
			}
		});
		setTitle("관리자 페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getRbInsert());
		contentPane.add(getRbUpdate());
		contentPane.add(getRbDelete());
		contentPane.add(getRbSearch());
		contentPane.add(getTfSearch());
		contentPane.add(getBtnOK());
		contentPane.add(getLblProductImage());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTfBrandNo());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getTfBrandName());
		contentPane.add(getTfProductCode());
		contentPane.add(getTfProductName());
		contentPane.add(getLblNewLabel_1_1_1());
		contentPane.add(getLblNewLabel_1_1_1_1());
		contentPane.add(getLblNewLabel_1_1_2());
		contentPane.add(getTfProductPrice());
		contentPane.add(getLblNewLabel_1_1_2_1());
		contentPane.add(getLblNewLabel_1_1_2_1_1());
		contentPane.add(getTfProductStock());
		contentPane.add(getLblNewLabel_1_1_2_2());
		contentPane.add(getTfProductInsertdate());
		contentPane.add(getBtnUpdate());
		contentPane.add(getBtnDelete());
		contentPane.add(getCbSize());
		contentPane.add(getBtnInsert());
	}
	private JScrollPane getScrollPane() {
		if (ScrollPane == null) {
			ScrollPane = new JScrollPane();
			ScrollPane.setBounds(12, 56, 410, 124);
			ScrollPane.setViewportView(getInnerTable());
		}
		return ScrollPane;
	}
	private JRadioButton getRbInsert() {
		if (rbInsert == null) {
			rbInsert = new JRadioButton("입력");
			buttonGroup.add(rbInsert);
			rbInsert.setBounds(12, 17, 60, 23);
		}
		return rbInsert;
	}
	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("수정");
			buttonGroup.add(rbUpdate);
			rbUpdate.setBounds(72, 17, 60, 23);
		}
		return rbUpdate;
	}
	private JRadioButton getRbDelete() {
		if (rbDelete == null) {
			rbDelete = new JRadioButton("삭제");
			buttonGroup.add(rbDelete);
			rbDelete.setBounds(133, 17, 60, 23);
		}
		return rbDelete;
	}
	private JRadioButton getRbSearch() {
		if (rbSearch == null) {
			rbSearch = new JRadioButton("검색");
			buttonGroup.add(rbSearch);
			rbSearch.setSelected(true);
			rbSearch.setBounds(193, 17, 60, 23);
		}
		return rbSearch;
	}
	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.setBounds(247, 18, 113, 21);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery() ;
				}
			});
			btnOK.setBounds(362, 18, 59, 23);
		}
		return btnOK;
	}
	private JLabel getLblProductImage() {
		if (lblProductImage == null) {
			lblProductImage = new JLabel("");
			lblProductImage.setBounds(12, 201, 181, 182);
		}
		return lblProductImage;
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
			innerTable.setModel(outerTable); 		// connection of innerTable & ScrollPane
		}
		return innerTable;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품 코드");
			lblNewLabel_1.setBounds(205, 257, 89, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfBrandNo() {
		if (tfBrandNo == null) {
			tfBrandNo = new JTextField();
			tfBrandNo.setEditable(false);
			tfBrandNo.setBounds(306, 198, 116, 21);
			tfBrandNo.setColumns(10);
		}
		return tfBrandNo;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("제품명");
			lblNewLabel_1_1.setBounds(205, 285, 89, 15);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTfBrandName() {
		if (tfBrandName == null) {
			tfBrandName = new JTextField();
			tfBrandName.setEditable(false);
			tfBrandName.setColumns(10);
			tfBrandName.setBounds(306, 226, 116, 21);
		}
		return tfBrandName;
	}
	private JTextField getTfProductCode() {
		if (tfProductCode == null) {
			tfProductCode = new JTextField();
			tfProductCode.setEditable(false);
			tfProductCode.setColumns(10);
			tfProductCode.setBounds(306, 254, 116, 21);
		}
		return tfProductCode;
	}
	private JTextField getTfProductName() {
		if (tfProductName == null) {
			tfProductName = new JTextField();
			tfProductName.setEditable(false);
			tfProductName.setColumns(10);
			tfProductName.setBounds(306, 282, 116, 21);
		}
		return tfProductName;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("브랜드 코드");
			lblNewLabel_1_1_1.setBounds(205, 201, 89, 15);
		}
		return lblNewLabel_1_1_1;
	}
	private JLabel getLblNewLabel_1_1_1_1() {
		if (lblNewLabel_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1 = new JLabel("브랜드 명");
			lblNewLabel_1_1_1_1.setBounds(205, 229, 89, 15);
		}
		return lblNewLabel_1_1_1_1;
	}
	private JLabel getLblNewLabel_1_1_2() {
		if (lblNewLabel_1_1_2 == null) {
			lblNewLabel_1_1_2 = new JLabel("가격");
			lblNewLabel_1_1_2.setBounds(205, 315, 89, 15);
		}
		return lblNewLabel_1_1_2;
	}
	private JTextField getTfProductPrice() {
		if (tfProductPrice == null) {
			tfProductPrice = new JTextField();
			tfProductPrice.setEditable(false);
			tfProductPrice.setColumns(10);
			tfProductPrice.setBounds(306, 312, 116, 21);
		}
		return tfProductPrice;
	}
	private JLabel getLblNewLabel_1_1_2_1() {
		if (lblNewLabel_1_1_2_1 == null) {
			lblNewLabel_1_1_2_1 = new JLabel("사이즈");
			lblNewLabel_1_1_2_1.setBounds(205, 343, 43, 15);
		}
		return lblNewLabel_1_1_2_1;
	}
	private JComboBox getCbSize() {
		if (cbSize == null) {
			cbSize = new JComboBox();
			cbSize.setModel(new DefaultComboBoxModel(new String[] {"170", "175", "180", "185", "190", "195", "200", "205", "210", "215", "220", "225", "230", "235", "240", "245", "250", "255", "260", "265", "270", "275", "280", "285", "290"}));
			cbSize.setBounds(250, 340, 53, 23);
		}
		return cbSize;
	}
	private JLabel getLblNewLabel_1_1_2_1_1() {
		if (lblNewLabel_1_1_2_1_1 == null) {
			lblNewLabel_1_1_2_1_1 = new JLabel("재고량");
			lblNewLabel_1_1_2_1_1.setBounds(323, 343, 43, 15);
		}
		return lblNewLabel_1_1_2_1_1;
	}
	private JTextField getTfProductStock() {
		if (tfProductStock == null) {
			tfProductStock = new JTextField();
			tfProductStock.setEditable(false);
			tfProductStock.setColumns(10);
			tfProductStock.setBounds(362, 340, 60, 21);
		}
		return tfProductStock;
	}
	private JLabel getLblNewLabel_1_1_2_2() {
		if (lblNewLabel_1_1_2_2 == null) {
			lblNewLabel_1_1_2_2 = new JLabel("입고날짜");
			lblNewLabel_1_1_2_2.setBounds(205, 371, 99, 15);
		}
		return lblNewLabel_1_1_2_2;
	}
	private JTextField getTfProductInsertdate() {
		if (tfProductInsertdate == null) {
			tfProductInsertdate = new JTextField();
			tfProductInsertdate.setEditable(false);
			tfProductInsertdate.setColumns(10);
			tfProductInsertdate.setBounds(306, 368, 116, 21);
		}
		return tfProductInsertdate;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.setEnabled(false);
			btnUpdate.setBounds(177, 426, 97, 23);
		}
		return btnUpdate;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteClick();
					tableInit();
					searchAction();
					clearColumn();
					btnUpdate.setEnabled(false); 		// 다시 불러온 뒤에 버튼 클릭 못하도록 비활성화시키기.
					btnDelete.setEnabled(false);
					
				}
			});
			btnDelete.setEnabled(false);
			btnDelete.setBounds(325, 426, 97, 23);
		}
		return btnDelete;
	}
	
	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("입력");
			btnInsert.setEnabled(false);
			btnInsert.setBounds(12, 426, 97, 23);
		}
		return btnInsert;
	}
	
	
	
	
	
	
	
	// ---------------------functions
	
	private void tableInit() {		// 테이블 디자인하기 - 초기화
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
	
	
	private void searchAction() { 		// 관리자 페이지 제일 첫 화면(화면테이블에 db데이터 불러오기)		
		AdminDao dao = new AdminDao();
		ArrayList<AdminDto> dtoList = dao.selectList();
		int listCount = dtoList.size();
		
		for(int i = 0; i < listCount; i++) {
			String size = Integer.toString(dtoList.get(i).getSize());
			String stock = Integer.toString(dtoList.get(i).getProductStock());
			String[] qTxt = {dtoList.get(i).getBrandName(), dtoList.get(i).getProductName(), size, stock};
			outerTable.addRow(qTxt);
		}
		
	}
	
	private void tableClick() {			// 테이블 클릭시 상세정보들 텍스트 필드에 띄워주기
		if (rbDelete.isSelected()) {
			tfBrandNo.setEditable(false);
			tfBrandName.setEditable(false);
			tfProductCode.setEditable(false);
			tfProductName.setEditable(false);
			tfProductPrice.setEditable(false);
			cbSize.setEditable(false);
			tfProductStock.setEditable(false);
			tfProductInsertdate.setEditable(false);
			btnDelete.setVisible(true);
			btnUpdate.setVisible(false);
		}
		
		
		
	}
	
	private void conditionQuery() { 		
		int i = innerTable.getSelectedRow(); 		// 몇 번째 줄인지 알려줌		
		String wkSequence = (String) innerTable.getValueAt(i, 0) ; 	// seq 번호를 알아내자. getValueAt(row, column).i번째 컬럼의 0번째 row를 가져와?? 
		
		
		String whereDefault = "select seqno, name, telno, address, email, relation from userinfo"; 		// 검색하려는 순서대로 불러오기 
		String whereDefault1 = " where seqno=";
		
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver"); 		
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 		
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1 + wkSequence) ; 		// sql 가서 데이터 불러오기
			
			while(rs.next()) { 		// 읽을 데이터가 있으면 계속 반복해라 rs.next() : 데이터 타입이 boolean.
				tfSeqNo.setText(rs.getString(1));
				tfName.setText(rs.getString(2));
				tfTelno.setText(rs.getString(3));
				tfAddress.setText(rs.getString(4));
				tfEmail.setText(rs.getString(5));
				tfRelation.setText(rs.getString(6));
				
			}
			
		
			conn_mysql.close(); 		
			
		} catch (Exception e) {
			e.printStackTrace(); 		
		}
	}
	
	private void deleteClick() {
		PreparedStatement ps = null;			// ? 값에 입력해서 쓰려면 preparedStatement 클래스가 필요함.
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver"); 		
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); 		
						
			String query = "delete from userinfo where seqno = ?"; 
			
			ps = conn_mysql.prepareStatement(query) ; // 위에서 작성한 쿼리문 실행시키기 
			ps.setInt(1,  Integer.parseInt(tfSeqNo.getText()));  		// 순서 숫자 -> 숫자는 왠만하면 숫자로 받아서 쓰는게.. 
			ps.executeUpdate(); 		// 쿼리 업데이트 시키기 
			conn_mysql.close(); 		
			
			JOptionPane.showMessageDialog(null, tfName.getText() + "님의 정보가 삭제되었습니다. ");
		} catch (Exception e) {
			e.printStackTrace(); 		
		}
		
	}
	
	
	private void screenPartition() {
		// 검색
		if (rbSearch.isSelected()) {
			tfBrandNo.setEditable(false);
			tfBrandName.setEditable(false);
			tfProductCode.setEditable(false);
			tfProductName.setEditable(false);
			tfProductPrice.setEditable(false);
			cbSize.setEditable(false);
			tfProductStock.setEditable(false);
			tfProductInsertdate.setEditable(false);
			btnOK.setVisible(true);
			btnUpdate.setVisible(false);
			btnInsert.setVisible(false);
			btnDelete.setVisible(false);
		}
		
		// 입력
		if (rbInsert.isSelected()) {
			tfBrandNo.setEditable(true);
			tfBrandName.setEditable(true);
			tfProductCode.setEditable(true);
			tfProductName.setEditable(true);
			tfProductPrice.setEditable(true);
			cbSize.setEditable(true);
			tfProductStock.setEditable(true);
			tfProductInsertdate.setEditable(true);
			btnUpdate.setVisible(false);
			btnInsert.setVisible(true);
			btnInsert.setEnabled(true);
			btnDelete.setVisible(false);
		}
		
		// 수정
		if (rbUpdate.isSelected() || rbDelete.isSelected()) {
			tfBrandNo.setEditable(true);
			tfBrandName.setEditable(true);
			tfProductCode.setEditable(true);
			tfProductName.setEditable(true);
			tfProductPrice.setEditable(true);
			cbSize.setEditable(true);
			tfProductStock.setEditable(true);
			tfProductInsertdate.setEditable(true);
			btnUpdate.setVisible(true);
			btnUpdate.setEnabled(true);
			btnInsert.setVisible(false);
			btnDelete.setVisible(false);
		}
		
		
		
		
		
		
	}
		
	private void actionPartition() {
		// 입력할 때
		if(rbInsert.isSelected()) {
			int i_chk = insertFieldCheck();
			if (i_chk == 0) {
				insertAction();
				tableInit();
				searchAction();
				clearColumn();
			} else {
				JOptionPane.showMessageDialog(this, message + "입력해 주세요.", "입력창", JOptionPane.INFORMATION_MESSAGE); );
			}
		}
	}
	
	private int insertFieldCheck() {
		int i = 0;
		if (tfProductCode.getText().trim().length() == 0 ){
			i ++;
			message = "제품 코드를 ";
			tfProductCode.requestFocus();
		}		
		if (tfProductName.getText().trim().length() == 0 ){
			i ++;
			message = "제품 이름을 ";
			tfProductName.requestFocus();
		}
		if (tfBrandNo.getText().trim().length() == 0 ){
			i ++;
			message = "브랜드 코드를 ";
			tfBrandNo.requestFocus();
		}
		if (tfBrandName.getText().trim().length() == 0 ){
			i ++;
			message = "브랜드 이름을 ";
			tfBrandName.requestFocus();
		}
		if (tfProductPrice.getText().trim().length() == 0 ){
			i ++;
			message = "제품 가격을 ";
			tfProductPrice.requestFocus();
		}
		if (tfProductStock.getText().trim().length() == 0) {
			i ++;
			message = "재고량을 ";
			tfProductStock.requestFocus();
		}
		
		return i;	
	}
	
	private void insertAction() {
		//String
	}
	
	private void clearColumn() {
		tfBrandNo.setText("");
		tfBrandName.setText("");
		tfProductCode.setText("");
		tfProductName.setText("");
		tfProductPrice.setText("");
		tfProductStock.setText("");
		cbSize.setSelectedIndex(0);
		tfProductInsertdate.setText("");
	}
	
	
	
}
