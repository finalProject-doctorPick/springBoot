package com.example.controller;

public class team5 {
	// 1. 무조건 출석
	private static boolean 출석 = true;
			
	public static void main(String[] args) {
		// 2. 지각 3회 시 커피
		int lateCnt = 0;
		String penalty = "";
		
		if(lateCnt == 3) {
			penalty = "스타벅스 venti 사이즈 돌린다." ;
			lateCnt = 0;
		}
		
		// 3. 진척없는 업무 1시간 이상 금지
		for(int soloFight = 0; soloFight <= 60; soloFight++) {
			if(soloFight == 60) {
				System.out.println("도와주세요... PL님...");
			}
		}
		
		// 3. 스트레스 받을 시 샤우팅하기
		boolean stress = false;
		if(stress) {
			shouting();
		}
		
		// 4. 쉴 땐 쉬고 일할 땐 일하자
		String time = "업무시간";
		if(time.equals("쉬는시간")) {
			rest();
		}else {
			work();
		}
		
		// 5. 이어폰은 한쪽만 낀다.
		int earphone = 2;
		String earphoneUse = earphone % 2 == 1 ? "OK" : "욕설";
		
		// 6. 대화의 감정 섞지 않기
		String[] talk = {"착하게", "친절하게", "침착하게", "알아듣게", "부정적인 감정"};
		talk[4] = "이성적으로";
		
		// 7. 백업 생활화하기
		String project = "진행 중...";
		switch (project) {
			case "박살": rollback(); break;
			case "종료": byebye(); break;
			default: backup(); break;
		}
		
	}

	public static void shouting() {
		System.out.println("고함을 지르고 오다.");
	}
	
	public static void rest() {
		System.out.println("잠깐 봐준다.");
	}
	
	public static void work() {
		System.out.println("일해야지?");
	}
	
	public static void rollback() {
		System.out.println("복구해라ㅡㅡ");
	}
	
	public static void byebye() {
		System.out.println("나중에 밥이나 먹자 :)");
	}
	
	public static void backup() {
		System.out.println(" ctrl + s ");
	}
}
