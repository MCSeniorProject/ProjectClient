package project.senior.event.impl.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.senior.event.Event;

@Getter
@AllArgsConstructor
public final class EventKey extends Event {
    private final int key;

}
