package ch.nth.test.animations;

import java.io.Serializable;

/**
 * @Author Danijel Turić
 * 2019
 * Animations
 */
public class Data implements Serializable {

    public String path;
    public String description;

    public Data(String path, String description) {
        this.path = path;
        this.description = description;
    }

}
