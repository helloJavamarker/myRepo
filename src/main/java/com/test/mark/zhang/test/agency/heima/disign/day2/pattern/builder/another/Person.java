package com.test.mark.zhang.test.agency.heima.disign.day2.pattern.builder.another;

import lombok.Getter;
import lombok.ToString;

/**
 * @author by mark
 * @Classname Person
 * @Description TODO
 * @Date 2022/1/24 1:16 下午
 */
@Getter
@ToString
public class Person {

    private final String name;  //必填
    private final long IdCard; //必填
    private final String hobby;
    private final String prinvice;

    private Person(PersonBuilder builder) {
        this.hobby = builder.hobby;
        this.IdCard = builder.IdCard;
        this.name = builder.name;
        this.prinvice = builder.privince;
    }


    public static class PersonBuilder {
        private final String name;
        private final long IdCard;
        private String hobby;
        private String privince;

        public PersonBuilder(String name, long idCard) {
            this.name = name;
            IdCard = idCard;
        }

        public PersonBuilder hobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        public PersonBuilder privince(String privince) {
            this.privince = privince;
            return this;
        }

        public Person create() {
            return new Person(this);
        }
    }

}
