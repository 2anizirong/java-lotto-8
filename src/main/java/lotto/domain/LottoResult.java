package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    // 등수별 당첨 횟수 저장하기
    private final Map<LottoRank, Integer> statistics;

    // 구매한 로또와 당첨 번호 비교하여 계산하기
    public LottoResult(List<Lotto> purchasedLottos, WinningLotto winningLotto) {
        this.statistics = new EnumMap<>(LottoRank.class);
        calculateStatistics(purchasedLottos, winningLotto);
    }

    // 당첨된 통계를 계산하기
    private void calculateStatistics(List<Lotto> purchasedLottos, WinningLotto winningLotto) {
        for (Lotto lotto : purchasedLottos) {
            LottoRank rank = winningLotto.calculateRank(lotto);
            statistics.merge(rank, 1, Integer::sum);        // 등수 횟수 + 1
        }
    }

    public Map<LottoRank, Integer> getStatistics() {
        return statistics;
    }

    // 총 수익률 계산하기 (수익률 = (총 당첨금 / 총 구매금액) * 100)
    public double calculateRateResult(Money purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        if (totalPrize == 0) {
            return 0.0;
        }
        double rate = (double) totalPrize / purchaseAmount.getAmount() * 100.0;
        // 소수점 둘째 자리에서 반올림

        return Math.round(rate * 10.0) / 10.0;
    }

    private long calculateTotalPrize() {
        return statistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
