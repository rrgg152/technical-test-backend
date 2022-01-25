package com.playtomic.tests.wallet;

import com.playtomic.tests.wallet.shared.domain.DomainService;
import com.playtomic.tests.wallet.shared.domain.Handler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, Handler.class}),
		value = {"com.playtomic.tests"}
)
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}
}
