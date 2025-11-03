package lotto;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다. (45 초과)")
    @Test
    void createLottoByOverRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다. (1 미만)")
    @Test
    void createLottoByUnderRange() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @DisplayName("Lotto 객체 생성 시 번호가 오름차순으로 정렬된다.")
    @Test
    void Lotto_객체_생성_시_번호가_오름차순으로_정렬된다() {
        Lotto lotto = new Lotto(List.of(5, 3, 1, 4, 2, 6));
        List<Integer> numbers = lotto.getNumbers();
        assertThat(numbers).isSorted();
        assertThat(numbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("contains 메서드가 번호 포함 여부를 정확히 반환한다.")
    @Test
    void contains_메서드가_번호_포함_여부를_정확히_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.contains(3)).isTrue();
        assertThat(lotto.contains(7)).isFalse();
    }
}
