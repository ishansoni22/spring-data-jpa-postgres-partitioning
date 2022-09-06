package com.ishan.postgrespartitioning.port.adapter.config;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map.Entry;
import org.hibernate.engine.jdbc.batch.internal.AbstractBatchImpl;
import org.hibernate.engine.jdbc.batch.spi.BatchKey;
import org.hibernate.engine.jdbc.spi.JdbcCoordinator;

/*
This custom batch implementation is used to override the NonBatchingBatch.class
so that we can work with partitioned tables in postgres
 */
public class CustomBatchImpl extends AbstractBatchImpl {

  protected CustomBatchImpl(BatchKey key,
      JdbcCoordinator jdbcCoordinator) {
    super(key, jdbcCoordinator);
    this.jdbcCoordinator = jdbcCoordinator;
  }

  private JdbcCoordinator jdbcCoordinator;

  @Override
  protected void doExecuteBatch() {
    //Not required
  }

  @Override
  public void addToBatch() {
    this.notifyObserversImplicitExecution();
    Iterator var1 = this.getStatements().entrySet().iterator();

    while(var1.hasNext()) {
      Entry<String, PreparedStatement> entry = (Entry)var1.next();
      String statementSQL = (String)entry.getKey();

      try {
        PreparedStatement statement = (PreparedStatement)entry.getValue();
        int rowCount = this.jdbcCoordinator.getResultSetReturn().executeUpdate(statement);

        //This is the check that throws the count exception when working with partitioned tables
        //this.getKey().getExpectation().verifyOutcome(rowCount, statement, 0, statementSQL);
        this.jdbcCoordinator.getResourceRegistry().release(statement);
        this.jdbcCoordinator.afterStatementExecution();
      //} catch (SQLException var6) {
      //  this.abortBatch(var6);
      //  throw this.sqlExceptionHelper().convert(var6, "could not execute non-batched batch statement", statementSQL);
      } catch (RuntimeException var7) {
        this.abortBatch(var7);
        throw var7;
      }
    }

    this.getStatements().clear();
  }

}
