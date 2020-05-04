package com.mageddo.micronaut;

import com.mageddo.micronaut.service.FruitsService;
import com.mageddo.micronaut.commons.DatabaseConfigurator;
import com.mageddo.micronaut.commons.DatabaseConfiguratorExtension;
import io.micronaut.test.annotation.MicronautTest;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest(environments = "test", transactional = false)
@ExtendWith({DatabaseConfiguratorExtension.class})
class TransactionalTest {

	@Inject
	private DatabaseConfigurator databaseConfigurator;

	@Inject
	private DataSource micronautDs;

	@Inject
	private FruitsService fruitsService;

	private PGSimpleDataSource vanillaDs;

	@BeforeEach
	void before() throws SQLException {
		this.vanillaDs = new PGSimpleDataSource();
		this.vanillaDs.setUrl(micronautDs.getConnection().getMetaData().getURL());
		this.vanillaDs.setUser("postgres");
		this.vanillaDs.setPassword("postgres");
	}

	@Test
	void mustInsertAndCommit() {

		// arrange
		assertEquals(0, getFruitsTraces(this.vanillaDs));

		databaseConfigurator.execute("/fruits-service-test/001.sql");

		// act

		// assert
		assertEquals(2, getFruitsTraces(this.micronautDs));
		assertEquals(2, getFruitsTraces(this.vanillaDs));

	}

	@Test
	public void mustInsertFruitTraceAndCommitUsingTheService(){
		// arrange
		assertEquals(0, getFruitsTraces(this.vanillaDs));
		assertEquals(0, getFruitsTraces(this.micronautDs));
		assertThrows(NoTransactionException.class, TransactionAspectSupport::currentTransactionStatus);

		// act
		fruitsService.createTrace();

		// assert
		assertThrows(NoTransactionException.class, TransactionAspectSupport::currentTransactionStatus);
		assertEquals(1, getFruitsTraces(this.micronautDs));
		assertEquals(1, getFruitsTraces(this.vanillaDs));
	}

	@Test
	public void shouldInsertAndRollbackWhenInTransaction(){
		// arrange
		assertEquals(0, getFruitsTraces(this.vanillaDs));
		assertEquals(0, getFruitsTraces(this.micronautDs));
		assertThrows(NoTransactionException.class, TransactionAspectSupport::currentTransactionStatus);

		// act
		assertThrows(IllegalStateException.class, () -> fruitsService.createTraceAndRollback());

		// assert
		assertThrows(NoTransactionException.class, TransactionAspectSupport::currentTransactionStatus);
		assertEquals(0, getFruitsTraces(this.micronautDs));
		assertEquals(0, getFruitsTraces(this.vanillaDs));
	}

	@Test
	public void shouldInsertAndDontRollbackWhenNotInATransaction(){
		// arrange
		assertEquals(0, getFruitsTraces(this.vanillaDs));
		assertEquals(0, getFruitsTraces(this.micronautDs));
		assertThrows(NoTransactionException.class, TransactionAspectSupport::currentTransactionStatus);

		// act
		assertThrows(IllegalStateException.class, () -> fruitsService.createTraceNoTransactionAndRollback());

		// assert
		assertThrows(NoTransactionException.class, TransactionAspectSupport::currentTransactionStatus);
		assertEquals(1, getFruitsTraces(this.micronautDs));
		assertEquals(1, getFruitsTraces(this.vanillaDs));
	}

	@SneakyThrows
	private int getFruitsTraces(DataSource ds) {
		int i = 0;
		@Cleanup
		final var conn = ds.getConnection();
		@Cleanup
		final var ps = conn.prepareStatement("SELECT * FROM FRUITS_TRACE");
		@Cleanup
		final var rs = ps.executeQuery();
		while (rs.next()){
			i++;
		}
		return i;
	}


}
