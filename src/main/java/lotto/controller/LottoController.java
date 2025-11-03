package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.util.LottoGenerator;
import lotto.validation.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine(new LottoGenerator());
    }

    public void run() {
        Money money = inputPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(money);
        printPurchasedLottos(lottos);

        WinningLotto winningLotto = inputWinningLotto();
        showResults(lottos, winningLotto, money);
    }

    /**
     * T(반환값)를 받을 때까지 Supplier<T>를 반복 실행합니다
     * @param reader
     * @return
     * @param <T>
     *     : T가 될 수 있는 타입 예시
     *     Money, Lotto, Integer 객체를 생성해 반환합니다 (각각 구입 금액, 당첨 번호, 보너스 번호)
     */
    private <T> T inputWithValidation(Supplier<T> reader) {
        while (true) {
            try {
                return reader.get();
            } catch (IllegalArgumentException e) {
                outputView.outputError(e.getMessage());
            }
        }
    }

    private Money inputPurchaseAmount() {
        // 여기서 T는 Money
        return inputWithValidation(() -> {
            String input = inputView.inputPurchaseAmount();
            int amount = InputValidator.validatePurchaseAmount(input);
            return new Money(amount);
        });
    }

    private List<Lotto> purchaseLottos(Money money) {
        return lottoMachine.purchaseLottos(money);
    }

    private void printPurchasedLottos(List<Lotto> lottos) {
        outputView.outputLottoCount(lottos.size());
        outputView.outputLottos(lottos);
    }

    private WinningLotto inputWinningLotto() {
        Lotto winningNumbers = inputWinningNumbers();
        int bonusNumber = inputBonusNumber(winningNumbers.getNumbers());
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto inputWinningNumbers() {
        // 여기서 T는 Lotto
        return inputWithValidation(() -> {
            String input = inputView.inputWinningNumbers();
            List<Integer> numbers = InputValidator.validateWinningNumbers(input);
            return new Lotto(numbers);
        });
    }

    private int inputBonusNumber(List<Integer> winningNumbers) {
        // 여기서는 T가 Integer
        return inputWithValidation(() -> {
            String input = inputView.inputBonusNumber();
            return InputValidator.validateBonusNumber(input, winningNumbers);
        });
    }

    private void showResults(List<Lotto> lottos, WinningLotto winningLotto, Money money) {
        LottoResult result = new LottoResult(lottos, winningLotto);
        double rateResult = result.calculateRateResult(money);

        outputView.outputStatistics(result.getStatistics());
        outputView.outputRateResult(rateResult);
    }
}
