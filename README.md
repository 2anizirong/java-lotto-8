# java-lotto-precourse

## 구현할 기능 목록

- [x] MVC 패키지 구조 설정 (`controller`, `domain`, `view`)


- [x] 로또 구입 금액 입력 기능 구현
  - [x] 안내 문구 출력 ("구입금액을 입력해 주세요.")
  - [x] `Console.readLine()`으로 구입 금액 입력받기 
  - [x] 입력값이 비어있거나 공백일 경우 `IllegalArgumentException` 
  - [x] 숫자가 아닐 경우 `IllegalArgumentException` 
  - [x] 1000원으로 나누어 떨어지지 않을 경우 `IllegalArgumentException`
  - [x] 구입 금액을 Money 객체로 나타내기
    - [x] 1000원 미만일 경우 `IllegalArgumentException`
    - [x] 로또 몇 개 구매했는지 계산하기


- [x] 로또 발행 및 출력 기능 구현
  - [x] `LottoGenerator`에서 로또 생성
  - [x] `LottoMachine`에서 구매 수량만큼 로또 발행 
  - [x] `Lotto` 구현하기
    - [x] 6개가 아닌 경우 `IllegalArgumentException` 
    - [x] 1~45 범위를 벗어난 숫자가 있을 경우 `IllegalArgumentException`
    - [x] 중복된 숫자가 있을 경우 `IllegalArgumentException`
    - [x] 생성 시 로또 번호 오름차순 정렬
  - [x] 구매 수량 출력("n개를 구매했습니다.")
  - [x] 발행된 로또 번호 목록을 오름차순으로 정렬하여 출력


- [x] 당첨 번호 입력 기능 구현
  - [x] 안내 문구 출력 ("당첨 번호를 입력해 주세요.")
  - [x] `Console.readLine()`으로 `쉼표(,)`로 구분된 당첨 번호 6개 입력받기
  - [x] 6개가 아닐 경우 `IllegalArgumentException`
  - [x] 1~45 범위를 벗어난 숫자가 있을 경우 `IllegalArgumentException`
  - [x] 중복된 숫자가 있을 경우 `IllegalArgumentException`
  - [x] 숫자가 아닌 값이 포함된 경우 `IllegalArgumentException`
  - [x] 번호 6개로 Lotto 객체 생성하기


- [x] 보너스 번호 입력 기능 구현
  - [x] 안내 문구 출력 ("보너스 번호를 입력해 주세요.")
  - [x] `Console.readLine()`으로 보너스 번호 1개 입력받기
  - [x] 숫자가 아닌 경우 `IllegalArgumentException`
  - [x] 1~45 범위를 벗어난 숫자인 경우 `IllegalArgumentException`
  - [x] 보너스 번호가 당첨 번호와 중복되는 경우 `IllegalArgumentException`


- [x] 당첨 통계 및 수익률 계산/출력 기능 구현
  - [x] `WinningLotto` 구현하기 (당첨 번호 6개 + 보너스 번호 1개)
  - [x] `LottoRank` 구현하기
    - [x] 1등~5등이랑 꽝 기준 정의
    - [x] 등수별 상금 정의
  - [x] `LottoResult` 구현하기
    - [x] 구매한 로또들과 `WinningLotto`를 비교하여 당첨 통계 계산하기
    - [x] 총 당첨금 계산 기능
    - [x] 총 수익률 계산 (소수점 둘째 자리에서 반올림)
  - [x] "당첨 통계 ---" 문구 출력
  - [x] 5등부터 1등 순서로 당첨 내역 출력 ("3개 일치 (5,000원) - 1개")
  - [x] 총 수익률 출력 ("총 수익률은 62.5%입니다.")


- [x] 테스트 코드 작성하기
  - [x] `Lotto` 테스트 코드 작성하기
  - [x] `Money` 테스트 코드 작성하기
  - [x] `WinningLotto` 테스트 코드 작성하기
  - [x] `LottoRank` 테스트 코드 작성하기
  - [x] `LottoResult` 테스트 코드 작성하기
  - [x] `InputValidator` 테스트 코드 작성하기
