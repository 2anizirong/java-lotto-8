package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Money;
import lotto.util.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("구입 금액만큼 로또를 정확히 발행해야 한다.")
    @Test
    void 구입_금액만큼_로또를_정확히_발행해야_한다() {
        // given
        Money money = new Money(8000);

        LottoGenerator lottoGenerator = new LottoGenerator() {
            @Override
            public Lotto generate() {
                // 테스트를 위해 항상 고정된 로또 번호를 반환
                return new Lotto(List.of(1, 2, 3, 4, 5, 6));
            }
        };

        LottoMachine lottoMachine = new LottoMachine(lottoGenerator);

        // when
        List<Lotto> lottos = lottoMachine.purchaseLottos(money);

        // then
        assertThat(lottos).hasSize(8); // 8000원이므로 8개
        assertThat(lottos.get(0).getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }
}

