package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    // 로또 번호의 숫자 범위는 1~45까지
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;

    // 1개 로또 발행할 때 뽑아야 되는 숫자 개수
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        // this.numbers = numbers;
        // 로또 번호를 오름차순으로 정렬
        this.numbers = numbers
                .stream()
                .sorted()
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.");
        }
    }

    /**
     * 로또 번호가 중복된 숫자가 있을 경우
     */
    private void validateDuplicates(List<Integer> numbers) {

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);        // HashSet은 중복 값을 허용하지 않습니다
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    /**
     * 로또 범위 (1~45)
     */
    private void validateRange(List<Integer> numbers) {
        boolean isOutOfRange = numbers.stream()
                .anyMatch(this::isInvalidNumber);
        if (isOutOfRange) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 " + LOTTO_NUMBER_MIN + "부터 " + LOTTO_NUMBER_MAX + " 사이의 숫자여야 합니다."
            );
        }
    }

    /**
     * 로또 개별 숫자가 1~45 범위를 벗어나는지 확인하기
     */
    private boolean isInvalidNumber(int number) {
        return number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);       // 수정할 수 없는 로또 번호 리스트로 불러오기
    }

    public boolean contains(int number) {
        return numbers.contains(number);        // 로또에 해당 특정 번호를 포함하는지 확인하기 (true, false로..)
    }

    // 로또 번호 리스트 형태로 출력하기
    @Override
    public String toString() {
        return numbers.toString();
    }
}
