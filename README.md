# 📚 Booktori

![booktory](images/booktory_slide.gif)

> 온라인 독서 모임 플랫폼 책토리 (Booktori)

<br>

## 👨‍👩‍👦 팀원

- 원지연
- 유원선
- 이예은
- 홍원기

<br>

## 📆 프로젝트 개요

- **진행 기간:** 2021.07.12 ~ 진행 중🏃
- **웹사이트 이름:** Booktori (책토리)
  - 온라인으로 독서 모임을 하고 싶은 사람들을 위한 커뮤니티 & 화상회의 서비스

<br>

## 📑 Tech Blog & Log

[책토리 Blog](https://booktori.tistory.com/)

<details open>
  <summary>7/11 ~ 8/20 책토리 회의록</summary>
  <ul>
      <li><a href="./Document/Dev_log/20210712_회의록.md">7/12 회의록 : 아이디어 회의</a></li>
      <li><a href="./Document/Dev_log/20210713_회의록.md">7/13 회의록 : 온라인 독서 모임 플랫폼</a></li>
      <li><a href="./Document/Dev_log/20210714_회의록.md">7/14 회의록 : 공통 프로젝트 진행상황 공유</a></li>
      <li><a href="./Document/Dev_log/20210715_회의록.md">7/15 회의록 : 기능설계</a></li>
      <li><a href="./Document/Dev_log/20210716_회의록.md">7/16 회의록 : 공통 프로젝트 마감 및 아이디어 디벨롭, 기능 우선순위</a></li>
      <li><a href="./Document/Dev_log/20210719_회의록.md">7/19 회의록 : 와이어프레임 작업1</a></li>
      <li><a href="./Document/Dev_log/20210720_회의록.md">7/20 회의록 : 와이어프레임 작업2</a></li>
      <li><a href="./Document/Dev_log/20210721_회의록.md">7/21 회의록 : 와이어프레임 마무리 및 기능명세서 업데이트</a></li>
      <li><a href="./Document/Dev_log/20210722_회의록.md">7/22 회의록 : ERD 작성, 기획/설계 정리</a></li>
      <li><a href="./Document/Dev_log/20210723_회의록.md">7/23 회의록 : REST API 문서 작성, JIRA 테스크 생성 및 관리</a></li>
      <li><a href="./Document/Dev_log/20210726_회의록.md">7/26 회의록 : UI 디자인 컨셉 설정, JPA</a></li>
      <li><a href="./Document/Dev_log/20210727_회의록.md">7/27 회의록 : UI 작업1, REST API 설계</a></li>
      <li><a href="./Document/Dev_log/20210728_회의록.md">7/28 회의록 : UI 작업2, API 개발</a></li>
      <li><a href="./Document/Dev_log/20210729_회의록.md">7/29 회의록 : UI 작업 마무리, 프로토타입 제작, API 개발</a></li>
      <li><a href="./Document/Dev_log/20210730_회의록.md">7/30 회의록 : 중간 발표</a></li>
      <li><a href="./Document/Dev_log/20210802_회의록.md">8/02 회의록 : 기능개발1</a></li>
      <li><a href="./Document/Dev_log/20210803_회의록.md">8/03 회의록 : 기능개발2</a></li>
      <li><a href="./Document/Dev_log/20210804_회의록.md">8/04 회의록 : 기능개발3</a></li>
      <li><a href="./Document/Dev_log/20210805_회의록.md">8/05 회의록 : 기능개발4</a></li>
      <li><a href="./Document/Dev_log/20210806_회의록.md">8/06 회의록 : 기능개발5 / 팀별 발표 </a></li>
      <li><a href="./Document/Dev_log/20210809_회의록.md">8/09 회의록 : 기능개발6</a></li>
      <li><a href="./Document/Dev_log/20210810_회의록.md">8/10 회의록 : 기능개발7</a></li>
      <li><a href="./Document/Dev_log/20210811_회의록.md">8/11 회의록 : 기능개발8</a></li>
      <li><a href="./Document/Dev_log/20210812_회의록.md">8/12 회의록 : 기능개발9</a></li>
      <li><a href="./Document/Dev_log/20210813_회의록.md">8/13 회의록 : 기능개발10</a></li>
      <li><a href="./Document/Dev_log/20210816_회의록.md">8/16 회의록 : 테스트1</a></li>
      <li><a href="./Document/Dev_log/20210817_회의록.md">8/17 회의록 : 테스트2</a></li>
      <li><a href="./Document/Dev_log/20210818_회의록.md">8/18 회의록 : 테스트3</a></li>
      <li><a href="./Document/Dev_log/20210819_회의록.md">8/19 회의록 : </a></li>
      <li><a href="./Document/Dev_log/20210820_회의록.md">8/20 회의록 : 최종발표</a></li>
  </ul>
</details>

<br>

## ✅ Git Convention

### Commit

```
[#Jira 이슈번호] type: 행위 요약

body
```

```
[#S05P12A607-141] feat: 회원정보 등록 api 구현

행위에 대한 자세한 설명 (What & Why)
```

- **type 종류**

| type     | 설명                                 |
| -------- | ------------------------------------ |
| feat     | 새로운 기능에 대한 커밋              |
| fix      | 버그 수정에 대한 커밋                |
| build    | 빌드 관련 파일 수정에 대한 커밋      |
| chore    | 그 외 자잘한 수정에 대한 커밋        |
| ci       | CI 관련 설정 수정에 대한 커밋        |
| docs     | 문서 수정에 대한 커밋                |
| style    | 코드 스타일 혹은 포맷 등에 관한 커밋 |
| refactor | 코드 리팩토링에 대한 커밋            |
| test     | 테스트 코드 수정에 대한 커밋         |
| design   | CSS 등 UI 수정에 대한 커밋           |
| comment  | 주석 추가 및 수정에 대한 커밋        |

### Branch

master

 |

develop

 |

feature (각 기능 단위)

- develop은 develop-be, develop-fe로 나뉨

  - back-end 관련 작업과 front-end 관련 작업을 각각 처리

- feature의 기능 단위는 Jira Sub-Task 기준

  - 필요에 따라 통합해서 사용해도 괜찮음

- 예시
  - feature/fe-**signup**
  - feature/be-**signup**

<br>

## 📎 Git 사용법

- 문제가 발생할 경우 팀에 바로 공유하기
- `git branch`, `git status`, `git pull` 의 습관화
- conflict를 최소화 합시다😊

1. develop branch로 이동 후 최신 버전 업데이트

   ```bash
   $ git switch develop
   $ git pull origin develop
   ```

2. 각 기능 단위 feature branch 생성

   ```bash
   $ git switch -c <브랜치명>
   ```

3. 해당 branch에 변경 내용 add - commit - push

   ```bash
   $ git add <파일 또는 폴더>
   $ git commit -m '커밋 메시지'
   $ git push origin <브랜치명>
   ```

4. GitLab에서 `create merge request` - 코드 리뷰 후 `merge`

5. 로컬에서 merge 완료된 feature branch 삭제

   ```bash
   $ git switch develop
   $ git merge <브랜치명>
   $ git pull # develop보다 커밋한 횟수만큼 앞서 있으므로
   $ git branch -d <브랜치명>
   ```
