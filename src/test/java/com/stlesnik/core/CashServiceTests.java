package com.stlesnik.core;

import com.stlesnik.core.dao.CassetteDao;
import com.stlesnik.core.model.Banknote;
import com.stlesnik.core.model.Withdraw;
import com.stlesnik.core.service.CashServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CashServiceTests {

	@Mock
	CassetteDao cassetteDao;

	@InjectMocks
	CashServiceImpl cashService;

	@Test
	//amount is too big to do withdraw
	void shouldReturnErrorMessage() {
		given(cassetteDao.getCurrentCounter(5000)).willReturn(3);
		given(cassetteDao.getCurrentCounter(2000)).willReturn(3);
		given(cassetteDao.getCurrentCounter(1000)).willReturn(3);
		given(cassetteDao.getCurrentCounter(500)).willReturn(3);
		given(cassetteDao.getCurrentCounter(200)).willReturn(3);
		given(cassetteDao.getCurrentCounter(100)).willReturn(3);

		assertThatExceptionOfType(Exception.class)
				.isThrownBy(() -> { cashService.withdrawMoney(99999900); })
				.withMessage("it is impossible to issue the amount");
	}

	@Test
	//we get a list of banknotes in the amount of 8800, in which each denomination will be used once
	void shouldReturnWithdrawWithoutErrorMessage() throws Exception {
		given(cassetteDao.getCurrentCounter(5000)).willReturn(3);
		given(cassetteDao.getCurrentCounter(2000)).willReturn(3);
		given(cassetteDao.getCurrentCounter(1000)).willReturn(3);
		given(cassetteDao.getCurrentCounter(500)).willReturn(3);
		given(cassetteDao.getCurrentCounter(200)).willReturn(3);
		given(cassetteDao.getCurrentCounter(100)).willReturn(3);

		List<Banknote> banknotes = cashService.withdrawMoney(8800).getBanknotes();
		assertThat(banknotes.get(0).getAmount()).isEqualTo(1);//5000
		assertThat(banknotes.get(1).getAmount()).isEqualTo(1);//2000
		assertThat(banknotes.get(2).getAmount()).isEqualTo(1);//1000
		assertThat(banknotes.get(3).getAmount()).isEqualTo(1);//500
		assertThat(banknotes.get(4).getAmount()).isEqualTo(1);//200
		assertThat(banknotes.get(5).getAmount()).isEqualTo(1);//100
	}


}
