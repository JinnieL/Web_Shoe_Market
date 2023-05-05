package com.javalec.funtion;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.javalec.base.ProductDetailMain;

public class ImageResize {
	
	/* 이미지 사이즈를 조정해주는 Function Class 
	 icon : 내가 넣을 사진
	 x : 사진의 width (가로 길이)
	 y : 사진의 length (세로 길이)
	 
	 팀원분들 보시라고 사용법 적어놓겠습니다.
	 
	 1) 원하는 클래스에서 ImageIcon 타입의 변수를 하나 만들어서 원하는 사진을 넣는다.
	  - 아직 프로젝트 구현이 끝나지 않았기 때문에 DB의 이미지를 가져올 수 없어 임의로 클래스 내에 사진을 사용했습니다.
	 ImageIcon productIcon = new ImageIcon(ProductDetailMain.class.getResource("/com/javalec/images/P000001.png"));
	 
	 2) 원하는 x,y축 길이를 설정한다.
	 int x = 200;
	 int y = 100;
	 ImageResize 클래스를 불러와 icon, x, y를 Parameter로 가진 생성자를 호출하여 데이터를 넣어준다.
	 ImageResize imageResize = new ImageResize(productIcon, x, y);
	 
	 3) 원하는 이미지를 ImageIcon 타입으로 생성하고, imageResize Class의 imageResizing 메소드를 불러 원하는 변수에 리턴 데이터(ImageIcon 타입)를 넣어준다.
	  - 변수 이름은 예시일 뿐 임의로 작성하셔도 괜찮습니다.
	 ImageIcon resizeIcon = imageResize.imageResizing();
	 
	 4) 원하는 라벨에 setIcon메소드를 통해 넣어준다.
	 lblProductImage.setIcon(resizeIcon);
	 
	 */
	
	/* Field */
	ImageIcon icon;
	int x;
	int y;
	
	/* Constructor */
	public ImageResize() {
		// TODO Auto-generated constructor stub
	}
	
	/* 이미지아이콘, X축, Y축 데이터를 가져와 줄 생성자 */
	public ImageResize(ImageIcon icon, int x, int y) {
		super();
		this.icon = icon;
		this.x = x;
		this.y = y;
	}

	public ImageIcon imageResizing() {
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		return changeIcon;
	}
	

}
