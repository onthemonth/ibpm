package com.brightd.jbpm;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by pengyong on 16/12/22.
 */
public class KieSessionRuntimeManagerFactoryBean implements FactoryBean, InitializingBean {

    private RuntimeEngine runtimeEngine;

    @Override
    public Object getObject() throws Exception {
        return runtimeEngine.getKieSession();
    }

    @Override
    public Class<?> getObjectType() {
        return KieSession.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public RuntimeEngine getRuntimeEngine() {
        return runtimeEngine;
    }

    public void setRuntimeEngine(RuntimeEngine runtimeEngine) {
        this.runtimeEngine = runtimeEngine;
    }
}
