package com.playtomic.tests.shared.infrastructure.bus.query;

import com.playtomic.tests.shared.domain.DomainError;
import com.playtomic.tests.shared.domain.bus.query.Query;
import com.playtomic.tests.shared.domain.bus.query.QueryBus;
import com.playtomic.tests.shared.domain.bus.query.QueryHandler;
import com.playtomic.tests.shared.domain.bus.query.Response;
import com.playtomic.tests.shared.domain.bus.query.error.QueryHandlerExecutionError;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public final class InMemoryQueryBus implements QueryBus {
    private final QueryHandlersInformation information;
    private final ApplicationContext       context;

    public InMemoryQueryBus(QueryHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context     = context;
    }

    @Override
    public Response ask(Query query) throws QueryHandlerExecutionError {
        try {
            Class<? extends QueryHandler> queryHandlerClass = information.search(query.getClass());

            QueryHandler handler = context.getBean(queryHandlerClass);

            return handler.handle(query);
        } catch (DomainError error) {
            throw error;
        } catch (Throwable error) {
            throw new QueryHandlerExecutionError(error);
        }
    }
}