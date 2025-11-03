package lotto.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.Lotto;
import lotto.domain.Money;

public class InputValidator {

    /**
     * 에러 메세지 상수 처리하기
     */

    // 숫자가 아닐 경우
    private static final String ERROR_NOT_NUMBER = "[ERROR] 입력값이 숫자가 아닙니다.";

    // 1000원 단위가 아닐 경우
    private static final String ERROR_THOUSAND_UNIT = "[ERROR] 구입 금액은 " + Money.LOTTO_PRICE + "원 단위여야 합니다.";

    // 입력값이 비어있거나 공백일 경우
    private static final String ERROR_EMPTY_INPUT = "[ERROR] 입력값이 비어있습니다.";

    // 6개가 아닌 경우
    private static final String ERROR_INVALID_COUNT = "[ERROR] 당첨 번호는 " + Lotto.LOTTO_NUMBER_COUNT + "개여야 합니다.";

    // 1~45 범위를 벗어난 숫자가 있을 경우
    private static final String ERROR_NUMBER_RANGE = "[ERROR] 로또 번호는 " + Lotto.LOTTO_NUMBER_MIN + "부터 " + Lotto.LOTTO_NUMBER_MAX + "사이의 숫자여야 합니다.";

    // 중복된 숫자가 있을 경우
    private static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 당첨 번호는 중복될 수 없습니다.";

    // 보너스 번호가 당첨 번호와 중복되는 경우
    private static final String ERROR_BONUS_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    // 구매 금액
    public static int validatePurchaseAmount(String input) {
        validateNotEmpty(input);
        int amount = validateNumeric(input);
        validateThousandUnit(amount);

        return amount;
    }

    // 당첨 번호
    // 입력값이 비어있거나 6개가 아니거나 숫자가 아니거나 범위를 벗어나거나 중복이 있을 경우
    public static List<Integer> validateWinningNumbers(String input) {
        validateNotEmpty(input);

        // 쉼표(,)로 구분된 당첨 번호 6개 입력받기
        String[] parts = input.split(",");
        validateCount(parts);

        List<Integer> numbers = parseNumbers(parts);        // 숫자로 변환하기
        validateNumberRange(numbers);       // 1~45 범위인지 확인하기
        validateDuplicates(numbers);        // 중복된 숫자가 있는지 확인하기

        return numbers;
    }

    // 보너스 번호
    // 입력값이 비어있거나 숫자가 아니거나 범위를 벗어나거나 당첨 번호와 중복될 경우
    public static int validateBonusNumber(String input, List<Integer> winningNumbers) {
        validateNotEmpty(input);
        int number = validateNumeric(input);
        validateNumberRange(List.of(number));
        validateBonusDuplicate(number, winningNumbers);

        return number;
    }

    // 문자열이 숫자로 변환 가능한지 확인하기
    private static int validateNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // parseInt에서 숫자가 아닌 값을 변환하려 할 때
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }

    // 1000원으로 안 나눠떨어지는 경우
    private static void validateThousandUnit(int amount) {
        if (amount <= 0 || amount % Money.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_THOUSAND_UNIT);
        }
    }

    // 입력값이 비어있거나 공백일 경우
    private static void validateNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_INPUT);
        }
    }

    // 문자열 배열의 길이가 로또 번호 개수(6개)와 일치하는지 확인하기
    private static void validateCount(String[] parts) {
        if (parts.length != Lotto.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_COUNT);
        }
    }

    // 숫자 리스트로 변환하기
    private static List<Integer> parseNumbers(String[] parts) {
        try {
            return java.util.Arrays.stream(parts)
                    .map(String::trim)      // 불필요한 공백 제거하기
                    .map(Integer::parseInt) // 숫자로 변환하기
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }

    // 로또 번호가 1~45 범위 내에 있는지 확인하기
    private static void validateNumberRange(List<Integer> numbers) {
        boolean isOutOfRange = numbers.stream()
                .anyMatch(n -> n < Lotto.LOTTO_NUMBER_MIN || n > Lotto.LOTTO_NUMBER_MAX);
        if (isOutOfRange) {
            throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
        }
    }

    // 당첨 번호에 중복된 값이 있는지 확인하기
    private static void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    // 보너스 번호가 당첨 번호 리스트에 포함되어 있는 번호인지 확인하기
    private static void validateBonusDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_DUPLICATE);
        }
    }
}
