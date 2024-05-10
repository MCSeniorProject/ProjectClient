package project.senior.settings.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Setter;
import project.senior.settings.Setting;

@Setter
public class BooleanSetting extends Setting {
    private boolean state;

    public BooleanSetting(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    private boolean isEnabled() {
        return state;
    }

    private void toggle() {
        setState(!isEnabled());
    }
}
