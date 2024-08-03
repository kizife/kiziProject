/*
구성 순서
1. 테스트케이스의 수 testcase, 부지의 변의 길이 N, 배열 입력
2. N*N만큼 8방향 순회 반복
3. G 있으면 높이 2 설정
4. 이외의 경우, 행과 열에 있는 건물 수 각각 더함 (중복 제거)
 */

package com.ssafy.ws01.step5;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int testcase = sc.nextInt(); // 테스트 케이스의 수

        for (int t = 1; t <= testcase; t++) {
            int N = sc.nextInt();
            sc.nextLine(); // 줄바꿈 제거

            char[][] grid = new char[N][N]; //2차원 배열
            for (int i = 0; i < N; i++) { //N만큼 반복
                grid[i] = sc.nextLine().replace(" ", "").toCharArray();
            } //사용자 입력에서 공백을 제거하고, 배열로 변환해서 grid[i]에 저장

            int maxHeight = 0;

            //주위를 둘러싼 8개 부지 탐색
            int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
            int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) { //N개 행, 열만큼 반복
                    if (grid[i][j] == 'B') {
                        boolean yesPark = false; //공원 여부 초기화
                        for (int d = 0; d < 8; d++) { //8개 방향을 순회하며 반복함
                            int ni = i + dx[d]; // (i, j)에서 dx[d]만큼 이동
                            int nj = j + dy[d]; // (i, j)에서 dy[d]만큼 이동

                            //grid[ni][nj]가 부지 내에 있는지+G인지 확인
                            if (ni >= 0 && ni < N && nj >= 0 && nj < N && grid[ni][nj] == 'G') {
                                yesPark = true; //G가 있다면 yesPark를 true로
                                break;
                            }
                        }

                        int height;
                        if (yesPark) {
                            height = 2; //주위에 공원 있다면 높이 2
                        } else {
                            int rowB = 0, colB = 0;
                            for (int k = 0; k < N; k++) { //N만큼 반복
                                if (grid[i][k] == 'B') rowB++; //행i에 있는 빌딩 수만큼 ++
                                if (grid[k][j] == 'B') colB++; //열j에 있는 빌딩 수만큼 ++
                            }
                            height = rowB + colB - 1; //빌딩 수의 합산. (i, j)는 중복이므로 -1
                        }
                        maxHeight = Math.max(maxHeight, height); //둘 중 더 높은 것
                    }
                }
            }

            System.out.println("#" + t + " " + maxHeight);
        }

    }
}
