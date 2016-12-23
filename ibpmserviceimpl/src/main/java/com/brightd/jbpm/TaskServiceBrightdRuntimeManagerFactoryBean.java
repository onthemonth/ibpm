package com.brightd.jbpm;

/**
 * Created by pengyong on 16/12/8.
 */

import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.task.TaskService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


public class TaskServiceBrightdRuntimeManagerFactoryBean implements FactoryBean, InitializingBean {

    private RuntimeEngine runtimeEngine;

    @Override
    public Object getObject() throws Exception {
        return runtimeEngine.getTaskService();
    }

    @Override
    public Class<?> getObjectType() {
        return TaskService.class;
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

