package lotto;

import lotto.domain.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @DisplayName("일치 개수와 보너스 여부에 따라 정확한 등수를 반환해야 한다.")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "4, true, FOURTH", // 4개 일치는 보너스와 상관없이 4등
            "3, false, FIFTH",
            "3, true, FIFTH",  // 3개 일치는 보너스와 상관없이 5등
            "2, false, MISS",
            "1, true, MISS",
            "0, false, MISS"
    })
    void 일치_개수와_보너스_여부에_따라_정확한_등수를_반환해야_한다(int matchCount, boolean hasBonus, LottoRank expectedRank) {
        LottoRank rank = LottoRank.valueOf(matchCount, hasBonus);
        assertThat(rank).isEqualTo(expectedRank);
    }

    @DisplayName("각 등수의 상금이 정확해야 한다.")
    @Test
    void 각_등수의_상금이_정확해야_한다() {
        assertThat(LottoRank.FIRST.getPrizeMoney()).isEqualTo(2000000000);
        assertThat(LottoRank.SECOND.getPrizeMoney()).isEqualTo(30000000);
        assertThat(LottoRank.THIRD.getPrizeMoney()).isEqualTo(1500000);
        assertThat(LottoRank.FOURTH.getPrizeMoney()).isEqualTo(50000);
        assertThat(LottoRank.FIFTH.getPrizeMoney()).isEqualTo(5000);
        assertThat(LottoRank.MISS.getPrizeMoney()).isEqualTo(0);
    }
}

