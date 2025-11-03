package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.Lotto;

public class LottoGenerator {
    public Lotto generate() {
        // 랜덤으로 1~45 사이 범위의 숫자를 6개 뽑기
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                Lotto.LOTTO_NUMBER_MIN,
                Lotto.LOTTO_NUMBER_MAX,
                Lotto.LOTTO_NUMBER_COUNT
        );
        return new Lotto(numbers);
    }
}
