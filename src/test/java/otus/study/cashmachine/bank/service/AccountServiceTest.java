package otus.study.cashmachine.bank.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import otus.study.cashmachine.bank.dao.AccountDao;
import otus.study.cashmachine.bank.data.Account;
import otus.study.cashmachine.bank.service.impl.AccountServiceImpl;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class AccountServiceTest {

    Account account;
    AccountDao accountDao;

    AccountServiceImpl accountServiceImpl;

    @BeforeEach
    public void init() {

    }

    @Test
    /**
     * unit test: test account creation with mock and ArgumentMatcher
     * @author Elena Chuyan
     */
    void createAccountMock() {
// @TODO test account creation with mock and ArgumentMatcher

        accountDao = Mockito.mock(AccountDao.class);
        when(accountDao.saveAccount(new Account(1L,new BigDecimal(5000)))).thenReturn(new Account(1L,new BigDecimal(5000)));

        accountServiceImpl = Mockito.mock(AccountServiceImpl.class);
        when(accountServiceImpl.createAccount( argThat(argument -> argument.equals(new BigDecimal(5000))) )).thenReturn(new Account(1L,new BigDecimal(5000)));

        Account testAcc = accountServiceImpl.createAccount(new BigDecimal(5000));

        Assertions.assertEquals(new BigDecimal(5000),testAcc.getAmount());
    }



    @Test
    /**
     * unit test: test account creation with ArgumentCaptor
     * @author Elena Chuyan
     */
    void createAccountCaptor() {
//  @TODO test account creation with ArgumentCaptor
        accountDao = Mockito.mock(AccountDao.class);
        when(accountDao.saveAccount(new Account(1L,new BigDecimal(5000)))).thenReturn(new Account(1L,new BigDecimal(5000)));

        accountServiceImpl = Mockito.mock(AccountServiceImpl.class);
        ArgumentCaptor<BigDecimal> captor= ArgumentCaptor.forClass(BigDecimal.class);
        when(accountServiceImpl.createAccount( captor.capture())).thenReturn(new Account(1L,new BigDecimal(5000)));


        Account testAcc = accountServiceImpl.createAccount(new BigDecimal(5000));
        verify(accountServiceImpl).createAccount(captor.capture());
        verifyNoMoreInteractions(accountServiceImpl);
    }

    @Test
    void addSum() {
    }

    @Test
    void getSum() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void checkBalance() {
    }
}
