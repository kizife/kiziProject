/*
구성 순서
0. 게임 방식 선택
1. 사용자의 가위, 바위, 보 입력
2. 컴퓨터의 가위, 바위, 보 설정
3. 각각 승패 설정 및 카운트+1
4. 최종 결과 출력
 */

        package com.ssafy.ws01.step3;

import java.util.Scanner; //사용자 입력 관련.

public class GameTest {
    public static void main(String[] args) {

        //게임 방식 선택
        Scanner sc = new Scanner(System.in);

        System.out.println("가위바위보 게임을 시작합니다. 아래 보기 중 하나를 고르세요.");
        System.out.println("1. 5판 3승");
        System.out.println("2. 3판 2승");
        System.out.println("3. 1판 1승"); //선택 문구 출력.

        int answer1 = sc.nextInt(); //사용자 입력 받음.

        int total = 0; //총 게임 수 초기화.
        int win = 0; //이긴 게임 수 초기화.


        //위에서 선택한 게임 방식 선택에 따른 조건문
        while(true) {
            switch(answer1) {
                case 1 : //5판 3승 선택시
                    total = 5;
                    win = 3;
                    break; //5판 3승 달성하면 종료
                case 2 :
                    total = 3;
                    win = 2;
                    break; //3판 2승 달성하면 종료
                case 3 :
                    total = 1;
                    win = 1;
                    break; //1판 1승 달성하면 종료
                default :
                    System.out.println("다시 입력해주세요!");
                    continue; //처음으로 돌아가서 입력하게 하고 싶은데 어떻게 써야 할지 모르겠다
            }



            int userWins = 0; //사용자 이긴 수 초기화
            int comWins = 0; //컴퓨터 이긴 수 초기화
            int count = 0; //진행된 게임 수 초기화

            while (count < total && userWins < win) { //총 게임 끝나기 전 + 유저 승리 확실시 전

                //사용자의 가위, 바위, 보 입력받기
                System.out.println("가위, 바위, 보 중 하나를 적어주세요.");
                String userInput = sc.next();

                int user = 0;
                switch (userInput) {
                    case "가위" :
                        user = 1;
                        break;
                    case "바위" :
                        user = 2;
                        break;
                    case "보" :
                        user = 3;
                        break;
                    default :
                        System.out.println("다시 입력해주세요!");
                        continue;
                }


                //컴퓨터의 가위, 바위, 보 정하기
                int com = (int) (Math.random() * 3 + 1); //1~3 중 랜덤값 뽑기
                switch (com) { //값에 가위, 바위, 보 부여(필요없나?)
                    case 1 :
                        System.out.println("컴퓨터의 선택: 가위");
                        break;
                    case 2 :
                        System.out.println("컴퓨터의 선택: 바위");
                        break;
                    case 3 :
                        System.out.println("컴퓨터의 선택: 보");
                        break;
                }


                //승패 정하기
                if (user == com) {
                    System.out.println("비겼습니다!!!");
                } else if ((user == 1 && com == 2) || (user == 2 && com == 3) || (user == 3 && com == 1)) {
                    System.out.println("졌습니다!!!");
                    comWins++;
                } else {
                    System.out.println("이겼습니다!!!");
                    userWins++;
                }
                count++;
            }

            if (userWins > comWins) {
                System.out.println("### 사용자 승!!!");
            } else if (userWins == comWins) {
                System.out.println("### 비겼습니다!!!");
            } else {
                System.out.println("### 컴퓨터 승!!!");
            }
        }
    }
}