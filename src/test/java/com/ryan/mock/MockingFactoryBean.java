package com.ryan.mock;

import org.jmock.Mockery;
import org.springframework.beans.factory.FactoryBean;

public class MockingFactoryBean implements FactoryBean {

    private final Class<?> clazzToMock;
    private final Mockery mockery;

    public MockingFactoryBean(final Class<?> clazzToMock, final Mockery mockery) {
        this.clazzToMock = clazzToMock;
        this.mockery = mockery;
    }

    public Object getObject() throws Exception {
        return mockery.mock(clazzToMock);
    }

    public Class<?> getObjectType() {
        return clazzToMock;
    }

    public boolean isSingleton() {
        return true;
    }

}