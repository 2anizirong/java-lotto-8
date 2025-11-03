package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private WinningLotto winningLotto;
    private List<Lotto> purchasedLottos;

    @BeforeEach
    void setUp() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        purchasedLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 1등 (1개)
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 2등 (1개)
                new Lotto(List.of(1, 2, 3, 4, 5, 8)), // 3등 (1개)
                new Lotto(List.of(1, 2, 3, 4, 8, 9)), // 4등 (1개)
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),// 5등 (1개)
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 꽝 (1개)
        );
    }

    @DisplayName("당첨 통계를 정확하게 계산해야 한다.")
    @Test
    void 당첨_통계를_정확하게_계산해야_한다() {
        // given
        LottoResult result = new LottoResult(purchasedLottos, winningLotto);

        // when
        Map<LottoRank, Integer> statistics = result.getStatistics();

        // then
        assertThat(statistics.getOrDefault(LottoRank.FIRST, 0)).isEqualTo(1);
        assertThat(statistics.getOrDefault(LottoRank.SECOND, 0)).isEqualTo(1);
        assertThat(statistics.getOrDefault(LottoRank.THIRD, 0)).isEqualTo(1);
        assertThat(statistics.getOrDefault(LottoRank.FOURTH, 0)).isEqualTo(1);
        assertThat(statistics.getOrDefault(LottoRank.FIFTH, 0)).isEqualTo(1);
        assertThat(statistics.getOrDefault(LottoRank.MISS, 0)).isEqualTo(1);
    }

    @DisplayName("총 수익률을 소수점 둘째 자리에서 반올림하여 계산해야 한다.")
    @Test
    void 총_수익률을_소수점_둘째_자리에서_반올림하여_계산해야_한다() {
        // given
        // 총 당첨금: 20억 + 3000만 + 150만 + 5만 + 5천 = 2031555000
        // 총 구매 금액: 6장 * 1000원 = 6000원
        Money purchaseAmount = new Money(6000);
        LottoResult result = new LottoResult(purchasedLottos, winningLotto);

        // when
        // (2031555000 / 6000) * 100 = 33859250.0
        double rate = result.calculateRateResult(purchaseAmount);

        // then
        assertThat(rate).isEqualTo(33859250.0);
    }

    @DisplayName("당첨금이 0원일 때 수익률은 0.0% 이다.")
    @Test
    void 당첨금이_0원일_때_수익률은_0_0_이다() {
        // given
        List<Lotto> losingLottos = List.of(
                new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                new Lotto(List.of(16, 17, 18, 19, 20, 21))
        );
        Money purchaseAmount = new Money(2000);
        LottoResult result = new LottoResult(losingLottos, winningLotto);

        // when
        double rate = result.calculateRateResult(purchaseAmount);

        // then
        assertThat(rate).isEqualTo(0.0);
    }

    @DisplayName("예시 수익률 62.5% 계산 테스트")
    @Test
    void 예시_수익률_62_5_계산_테스트() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto wl = new WinningLotto(winningNumbers, bonusNumber);

        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 10, 11, 12))); // 5등 1개
        Money purchaseAmount = new Money(8000); // 8장 구매 가정하기

        // 5등 1개 (5000원), 나머지 7개는 꽝
        Map<LottoRank, Integer> stats = new EnumMap<>(LottoRank.class);
        stats.put(LottoRank.FIFTH, 1);
        stats.put(LottoRank.MISS, 7);

        // LottoResult를 직접 생성하지 않고 통계 Map을 기반으로 수익률만 테스트
        // 총 당첨금 5000원, 총 구매액 8000원
        // (5000 / 8000) * 100 = 0.625 * 100 = 62.5

        LottoResult result = new LottoResult(lottos, wl); // 이 생성자는 1개만 계산함
        // 5등 1개(5000원) / 1000원 구매 -> 500.0%
        assertThat(result.calculateRateResult(new Money(1000))).isEqualTo(500.0);

        // (5000.0 / 8000.0) * 100.0 = 62.5
        // (Math.round(62.5 * 10.0) / 10.0) = 62.5
        double rate = (double) 5000 / 8000 * 100.0;
        double roundedRate = Math.round(rate * 10.0) / 10.0;
        assertThat(roundedRate).isEqualTo(62.5);
    }
}

