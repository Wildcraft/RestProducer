package org.wildcraft.businessobjects;

import org.wildcraft.framework.BaseBusinessObject;

/**
 * Created by Narendran Solai on 5/19/15.
 */
public class User extends BaseBusinessObject {

    private String name;
    private long age;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
