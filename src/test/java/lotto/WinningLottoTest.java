package lotto;

import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import lotto.domain.Lotto;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("사용자 로또를 받아 정확한 등수를 계산해야 한다.")
    @ParameterizedTest
    @MethodSource("로또와_등수_제공")
    void 사용자_로또를_받아_정확한_등수를_계산해야_한다(Lotto userLotto, LottoRank expectedRank) {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // when
        LottoRank rank = winningLotto.calculateRank(userLotto);

        // then
        assertThat(rank).isEqualTo(expectedRank);
    }

    // 테스트 케이스 제공 메서드
    private static Stream<org.junit.jupiter.params.provider.Arguments> 로또와_등수_제공() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 6)), LottoRank.FIRST // 1등
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 7)), LottoRank.SECOND // 2등
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 5, 8)), LottoRank.THIRD // 3등
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        new Lotto(List.of(1, 2, 3, 4, 8, 9)), LottoRank.FOURTH // 4등
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        new Lotto(List.of(1, 2, 3, 9, 10, 11)), LottoRank.FIFTH // 5등
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        new Lotto(List.of(1, 2, 10, 11, 12, 13)), LottoRank.MISS // 꽝
                )
        );
    }
}

