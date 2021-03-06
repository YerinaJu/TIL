package ver05;

import util.Select;
import java.util.InputMismatchException;
import java.util.Scanner;

import util.Util;

public class PhoneInfoManage {
	
	final PhoneInfor[] pb;
	int count;
	
	private static PhoneInfoManage m = new PhoneInfoManage();
	
	public static PhoneInfoManage getInstance() {
		if(m==null) {
			m = new PhoneInfoManage();
		}
		return m;
	}
	
	// 외부에서의 인스턴스 생성을 금지함.
	private PhoneInfoManage() {
		this(100);
	}
	
	private PhoneInfoManage(int size) {
		pb  = new PhoneInfor[size];
		count = 0;
	}
	
		// 친구정보 입력 메서드	
		public void insert() {
			System.out.println("정보를 저장할 카테고리를 입력하세요.");
			
			System.out.println("1. 대학");
			System.out.println("2. 회사");
			System.out.println("3. 동호회");
			int select = 0;
			
			try {
				select = Util.keyboard.nextInt();
				Util.keyboard.nextLine();
				insertMember(select);
			} catch(InputMismatchException e) {
				System.out.println("값을 다시 입력하세요.");
			}
			
		}
		
		public void insertMember(int select) {
			PhoneInfor pi = null;
			
			// 기본정보 받기
			System.out.println("친구의 이름을 입력해 주세요.");
			String name = Util.keyboard.nextLine();
			System.out.println("친구의 전화번호를 입력해주세요.");
			String phoneNumber = Util.keyboard.nextLine();
			
			// 흐름처리
			if(select == 1) {
				System.out.println("주소를 입력해주세요.");
				String address = Util.keyboard.nextLine();
				System.out.println("메일주소를 입력해주세요.");
				String email = Util.keyboard.nextLine();
				System.out.println("전공을 입력해주세요.");
				String major = Util.keyboard.nextLine();
				System.out.println("학년을 입력해주세요.");
				int grade = Util.keyboard.nextInt();
				
				pi = new PhoneUnivInfo(name, phoneNumber, address, email, major, grade);
				
				
			}else if(select == 2) {
				System.out.println("회사명을 입력해주세요");
				String company = Util.keyboard.nextLine();
				System.out.println("이메일을 입력해주세요.");
				String email = Util.keyboard.nextLine();
				
				pi = new PhoneCompanyInfo(name, phoneNumber, company, email);
						
				
			}else if(select == 3){
				System.out.println("닉네임을 입력해주세요.");
				String nickname = Util.keyboard.nextLine();
				
				pi = new PhoneCafeInfo(name, phoneNumber, nickname);				
			} else {
				System.out.println("해당 숫자만 입력할 수 있습니다.");
			}
			
			// 배열 저장
			pb[count++] = pi;			
		}
		public void showAllData() {
			
			
			System.out.println("======================");
			
			
			if(count>0) {
				for(int i=0;i<count;i++) {
					pb[i].showData();
					System.out.println("======================");
				}		
			} else {
				System.out.println("아직 등록된 정보가 없습니다.");
			}
			
			System.out.println("======================");
		}
		
		// 친구정보를 이름 기준으로 검색 후 정보를 출력하는 메소드
		public void searchPrint() {
			
			System.out.println("검색할 사용자의 이름을 입력해주세요.");
			String name = Util.keyboard.nextLine();
			
//			
			int index = searchIndex(name);
			
			if(index<0) {
				System.out.println("검색한 이름의 정보가 없습니다.");
			} else {
				
				pb[index].showBasicInfo();
			}
			
		}
		
		// 친구정보를 이름 기준으로 검색 후 정보를 삭제하는 메소드
		public void searchDelete() {
			
			System.out.println("삭제하고자 하는 사용자의 이름을 입력하세요.");
			String name = Util.keyboard.nextLine();
			int index = searchIndex(name);
			
			if(index<0) {
				System.out.println("검색하신 이름의 데이터가 존재하지 않습니다.");
			} else {
				pb[index].showBasicInfo();
				System.out.println("해당 이름의 주소를 삭제하시겠습니까?");
				System.out.println("1. 예\n2. 아니오");
				
				int choice = Util.keyboard.nextInt();
				
				if(choice == 1) {
					for(int i=index;i<count-1;i++) {
						pb[i] = pb[i+1];	
					}
					System.out.println("삭제했습니다.");
					count--;					
				} else {
					System.out.println("취소했습니다.");
				}
			}
		}
		public int searchIndex(String keyword) {
			int index = -1;						
			
			for(int i=0;i<count;i++) {
				if(pb[i].name.equals(keyword)) {
					index = i;
					break;
				}
			}
			return index;
		}
		public void modifyData() {
			System.out.println("정보를 수정하고자 하는 사용자의 이름을 입력하세요.");
			String name = Util.keyboard.nextLine();
			
			int index = searchIndex(name);
			if(index<0) {
				System.out.println("검색하신 이름의 데이터가 존재하지 않습니다.");
			} else {
				System.out.println("======================");
				pb[index].showData();
				System.out.println("======================");
				System.out.println("수정하시겠습니까?");
				System.out.println("1. 예\n2. 아니오");
				int select = Util.keyboard.nextInt();
				if(select == 1) {
					insert();
				} else {
					System.out.println("취소했습니다.");
				}
				System.out.println("수정을 완료했습니다.");
			}
			
		}
		public int printMenu() {
			System.out.println("---------------------------------");
			System.out.println("사용할 메뉴의 번호를 선택해 주세요.");
			System.out.printf("%d. 입력\n%d. 검색\n%d. 삭제\n%d. 수정\n%d. 전체 정보 출력\n%d. 종료\n",Select.INSERT,Select.SEARCH,Select.DELETE,Select.MODIFY,Select.PRINT,Select.QUIT);
			System.out.println("---------------------------------");
			
			int choice = 0;
			
			try {
				choice = Util.keyboard.nextInt();	
				Util.keyboard.nextLine();
				return choice;
			} catch(InputMismatchException e) {
				System.out.println("숫자만 입력할 수 있습니다. 값을 다시 입력하세요.");
				Util.keyboard = new Scanner(System.in);
				choice = Util.keyboard.nextInt();
				return choice;
			}
		}
	
}
