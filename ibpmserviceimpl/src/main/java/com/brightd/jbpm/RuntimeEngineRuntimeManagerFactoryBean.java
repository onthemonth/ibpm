package com.brightd.jbpm;

import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by pengyong on 16/12/22.
 */
public class RuntimeEngineRuntimeManagerFactoryBean implements FactoryBean, InitializingBean {

    private RuntimeManager runtimeManager;

    @Override
    public Object getObject() throws Exception {
        return runtimeManager.getRuntimeEngine(null);
    }

    @Override
    public Class<?> getObjectType() {
        return RuntimeEngine.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public RuntimeManager getRuntimeManager() {
        return runtimeManager;
    }

    public void setRuntimeManager(RuntimeManager runtimeManager) {
        this.runtimeManager = runtimeManager;
    }
}
