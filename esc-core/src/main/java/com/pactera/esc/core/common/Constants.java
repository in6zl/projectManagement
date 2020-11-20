package com.pactera.esc.core.common;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author yayun
 */
public interface Constants {
    String SPRING_PROFILE_DEVELOPMENT = "dev";
    String SPRING_PROFILE_TEST = "test";
    String SPRING_PROFILE_PRODUCTION = "prod";
    String SPRING_PROFILE_SWAGGER = "swagger";
    String RESULT = "result";
    String MESSAGE = "message";
    Integer PAGE_NUM = 0;
    Integer PAGE_SIZE = 10;

    Integer ENABLE = 1;
    Integer DISABLE = 0;
    Integer FINISHED = 1;
    Integer NO_FINISHED = 0;

    String DEFAULT_PASSWORD = "123456";
    Byte NO_COMPILTE = 0 ;
    Byte PRODUCTPLAN_MATERIAL_REQUIRE_PLAN = 0;
    int HOUR_MINUTES = 60;

    String ADMIN_USER_ID = "1";
    String ROLE_CREATE = "1";
    String ROLE_UPDATE = "2";

    //--------------hibernate validate------------------

    interface CheckId {

    }

    interface Password {

    }

    interface Create {

    }

    interface Update {

    }

    @Getter
    enum UserType {
        /**
         * 超管
         */
        ADMINISTRATOR(0, "超级管理员"),
        GENERAL(1, "普通用户"),
        supplier(2, "供应商")
        ;
        int type;
        String name;

        UserType(int type, String name) {
            this.type = type;
            this.name = name;
        }
    }
}
