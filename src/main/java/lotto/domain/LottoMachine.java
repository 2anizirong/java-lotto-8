package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.util.LottoGenerator;

public class LottoMachine {
    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> purchaseLottos(Money money) {
        int count = money.getLottoCount();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoGenerator.generate());
        }
        return lottos;
    }
}
