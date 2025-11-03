package lotto.domain;

public class WinningLotto {
    private final Lotto winningLotto;       // 당첨 번호 6개
    private final int bonusNumber;          // 보너스 번호 1개

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    // 보너스 번호와 당첨 번호의 중복 확인하기
    private void validate(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    // 등수 계산하기 (LottoRank Enum으로 관리합니다)
    public LottoRank calculateRank(Lotto userLotto) {
        // 등첨 번호 6개와 일치하는 번호 개수 계산하기
        int matchCount = countMatches(userLotto);

        // 보너스 번호 가지고 있는지 확인하기
        boolean hasBonus = userLotto.contains(bonusNumber);

        // 최종 등수
        return LottoRank.valueOf(matchCount, hasBonus);
    }

    // 사용자 로또가 당첨 번호 6개와 몇 개나 일치하는지 계산하기
    private int countMatches(Lotto userLotto) {
        return (int) userLotto
                .getNumbers()
                .stream()
                .filter(winningLotto::contains)
                .count();
    }
}