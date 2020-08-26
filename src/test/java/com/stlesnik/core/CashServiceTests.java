package com.stlesnik.core;

import com.stlesnik.core.dao.AtmDao;
import com.stlesnik.core.model.Banknote;
import com.stlesnik.core.service.CashServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CashServiceTests {

	@Mock
	AtmDao atmDao;

	@InjectMocks
	CashServiceImpl cashService;

	@Test
	//amount is too big to do withdraw
	void shouldReturnErrorMessage() {
		given(atmDao.getCurrentCounter(5000)).willReturn(3);
		given(atmDao.getCurrentCounter(2000)).willReturn(3);
		given(atmDao.getCurrentCounter(1000)).willReturn(3);
		given(atmDao.getCurrentCounter(500)).willReturn(3);
		given(atmDao.getCurrentCounter(200)).willReturn(3);
		given(atmDao.getCurrentCounter(100)).willReturn(3);

		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> { cashService.withdrawMoneyWithExchange(99999900); })
				.withMessage("it is impossible to issue the amount");
	}

	@Test
	//we get a list of banknotes in the amount of 8800, in which each denomination will be used once
	void shouldReturnWithdrawWithoutErrorMessage() throws Exception {
		given(atmDao.getCurrentCounter(5000)).willReturn(1);
		given(atmDao.getCurrentCounter(2000)).willReturn(0);
		given(atmDao.getCurrentCounter(1000)).willReturn(0);
		given(atmDao.getCurrentCounter(500)).willReturn(0);
		given(atmDao.getCurrentCounter(200)).willReturn(0);
		given(atmDao.getCurrentCounter(100)).willReturn(0);

		List<Banknote> banknotes = cashService.withdrawMoneyWithExchange(5000).getBanknotes();
		assertThat(banknotes.get(0).getAmount()).isEqualTo(1);//5000
		assertThat(banknotes.get(1).getAmount()).isEqualTo(0);//2000
		assertThat(banknotes.get(2).getAmount()).isEqualTo(0);//1000
		assertThat(banknotes.get(3).getAmount()).isEqualTo(0);//500
		assertThat(banknotes.get(4).getAmount()).isEqualTo(0);//200
		assertThat(banknotes.get(5).getAmount()).isEqualTo(0);//100
	}


}
