package lotto;

import lotto.validation.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1000j", "abc", " 1000"})
    void 구입_금액이_숫자가_아니면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 입력값이 숫자가 아닙니다.");
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1500", "500", "0"})
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
    }

    @DisplayName("입력값이 비어있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void 입력값이_비어있으면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 입력값이 비어있습니다.");
    }

    @DisplayName("당첨 번호 개수가 6개가 아니면 예외가 발생한다.")
    @Test
    void 당첨_번호_개수가_6개가_아니면_예외가_발생한다() {
        String input = "1,2,3,4,5";
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @DisplayName("당첨 번호에 숫자가 아닌 값이 있으면 예외가 발생한다.")
    @Test
    void 당첨_번호에_숫자가_아닌_값이_있으면_예외가_발생한다() {
        String input = "1,2,3,4,5,a";
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 입력값이 숫자가 아닙니다.");
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 당첨_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        String input = "1,2,3,4,5,5";
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 당첨_번호가_1_45_범위를_벗어나면_예외가_발생한다() {
        String input = "1,2,3,4,5,46";
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45사이의 숫자여야 합니다.");
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        String input = "6";
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateBonusNumber(input, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("유효한 입력값들은 정상적으로 파싱된다.")
    @Test
    void 유효한_입력값들은_정상적으로_파싱된다() {
        int amount = InputValidator.validatePurchaseAmount("8000");
        assertThat(amount).isEqualTo(8000);

        List<Integer> numbers = InputValidator.validateWinningNumbers("1,2,3,4,5,6");
        assertThat(numbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));

        int bonus = InputValidator.validateBonusNumber("7", List.of(1, 2, 3, 4, 5, 6));
        assertThat(bonus).isEqualTo(7);
    }
}

