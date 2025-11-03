package lotto.domain;

public class Money {
    // 로또 1개 가격
    public static final int LOTTO_PRICE = 1000;

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        // 로또 1개 가격 미만이거나 로또 1개 가격으로 나누어 떨어지지 않을 경우
        if (amount < LOTTO_PRICE || amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 "+ LOTTO_PRICE +"원 단위여야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

    // 로또 몇 개 구매했는지 계산하기
    public int getLottoCount() {
        return amount / LOTTO_PRICE;
    }
}

