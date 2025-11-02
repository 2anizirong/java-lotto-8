package lotto.view;

import java.util.List;
import lotto.domain.Lotto;

public class OutputView {
    public void outputLottoCount(int count) {
        System.out.printf("\n%d개를 구매했습니다.\n", count);
    }

    public void outputLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }
}
