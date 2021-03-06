package ru.outs.java.hw6.atm;

import org.junit.Test;
import ru.otus.java.hw6.atm.Atm;
import ru.otus.java.hw6.atm.MoneyTokens;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AtmDepositTest {

    @Test
    public void shouldDepositOneToken() {
        Atm atm = new Atm();
        atm.depositCash(10);

        assertThat(atm.getBalance(), is(10));
        assertThat(atm.getTokenCellByValue(10).getBalance(), is(10));
    }

    @Test
    public void shouldDepositOneMoneyToken() {
        Atm atm = new Atm();
        atm.depositCash(MoneyTokens.TEN_RUBLES);

        assertThat(atm.getBalance(), is(10));
        assertThat(atm.getTokenCellByValue(10).getBalance(), is(10));
    }

    @Test
    public void shouldDepositMultipleTokens() {
        Atm atm = new Atm();
        atm.depositCash(10, 100, 1);

        assertThat(atm.getBalance(), is(111));
        assertThat(atm.getTokenCellByValue(10).getBalance(), is(10));
        assertThat(atm.getTokenCellByValue(100).getBalance(), is(100));
        assertThat(atm.getTokenCellByValue(1).getBalance(), is(1));
        assertThat(atm.getTokenCellByValue(2).getBalance(), is(0));
    }

    @Test
    public void shouldDepositMultipleMoneyTokens() {
        Atm atm = new Atm();
        atm.depositCash(MoneyTokens.TEN_RUBLES, MoneyTokens.HUNDRED_RUBLES, MoneyTokens.ONE_RUBLE);

        assertThat(atm.getBalance(), is(111));
        assertThat(atm.getTokenCellByValue(10).getBalance(), is(10));
        assertThat(atm.getTokenCellByValue(100).getBalance(), is(100));
        assertThat(atm.getTokenCellByValue(1).getBalance(), is(1));
        assertThat(atm.getTokenCellByValue(2).getBalance(), is(0));
    }

    @Test
    public void shouldNotProceedInvalidToken() {
        Atm atm = new Atm();
        atm.depositCash(11);

        assertThat(atm.getBalance(), is(0));
    }

    @Test
    public void shouldNotProceedSumWithInvalidToken() {
        Atm atm = new Atm();
        atm.depositCash(10, 1, 9);

        assertThat(atm.getBalance(), is(0));
        assertThat(atm.getTokenCellByValue(10).getBalance(), is(0));
    }
}
