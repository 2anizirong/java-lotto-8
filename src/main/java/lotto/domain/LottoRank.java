package lotto.domain;

import java.text.NumberFormat;

public enum LottoRank {
    FIRST(6, 2000000000, false, "6개 일치 (%s원) - %d개"),
    SECOND(5, 30000000, true, "5개 일치, 보너스 볼 일치 (%s원) - %d개"),
    THIRD(5, 1500000, false, "5개 일치 (%s원) - %d개"),
    FOURTH(4, 50000, false, "4개 일치 (%s원) - %d개"),
    FIFTH(3, 5000, false, "3개 일치 (%s원) - %d개"),
    MISS(0, 0, false, "");

    private final int matchCount;
    private final long prizeMoney;
    private final boolean hasBonus;
    private final String message;

    LottoRank(int matchCount, long prizeMoney, boolean hasBonus, String message) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.hasBonus = hasBonus;
        this.message = message;
    }

    public static LottoRank valueOf(int matchCount, boolean hasBonus) {
        if (matchCount == FIRST.matchCount) {
            return FIRST;
        }
        if (matchCount == SECOND.matchCount && hasBonus) {
            return SECOND;
        }
        if (matchCount == THIRD.matchCount) {
            return THIRD;
        }
        if (matchCount == FOURTH.matchCount) {
            return FOURTH;
        }
        if (matchCount == FIFTH.matchCount) {
            return FIFTH;
        }
        return MISS;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public String getMessage(int count) {
        String formattedPrize = NumberFormat.getInstance().format(prizeMoney);
        return String.format(message, formattedPrize, count);
    }
}
