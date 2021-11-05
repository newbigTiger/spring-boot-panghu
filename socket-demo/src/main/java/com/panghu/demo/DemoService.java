package com.panghu.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public interface DemoService {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class User implements Serializable {
        String name;
    }

    User getUser(String name);

}
