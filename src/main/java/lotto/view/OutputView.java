package lotto.view;

import static lotto.domain.LottoRank.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;

public class OutputView {
    public void outputLottoCount(int count) {
        System.out.printf("\n%d개를 구매했습니다.\n", count);
    }

    public void outputLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public void outputStatistics(Map<LottoRank, Integer> result) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.println(FIFTH.getMessage(result.getOrDefault(FIFTH, 0)));
        System.out.println(FOURTH.getMessage(result.getOrDefault(FOURTH, 0)));
        System.out.println(THIRD.getMessage(result.getOrDefault(THIRD, 0)));
        System.out.println(SECOND.getMessage(result.getOrDefault(SECOND, 0)));
        System.out.println(FIRST.getMessage(result.getOrDefault(FIRST, 0)));
    }

    public void outputRateResult(double rate) {
        DecimalFormat df = new DecimalFormat("#,##0.0%");
        System.out.printf("총 수익률은 %s입니다.\n", df.format(rate / 100.0));
    }

    public void outputError(String message) {
        System.out.println(message);
    }
}
