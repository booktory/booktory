# ✅ Git Convention

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

# 📎 Git 사용법

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
