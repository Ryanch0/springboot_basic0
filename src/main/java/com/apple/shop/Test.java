package com.apple.shop;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Test {
    private String name;
    private Integer age;

    public void 한살더하기() {
        this.age = this.age + 1;
    }

    public void 나이설정(Integer age) {
        if (age > 0 && age < 100) {
            this.age = age;
        }


    }
}
