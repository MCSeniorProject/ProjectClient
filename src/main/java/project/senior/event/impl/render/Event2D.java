package project.senior.event.impl.render;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.ScaledResolution;
import project.senior.event.Event;

@Getter
@Setter
@AllArgsConstructor
public class Event2D extends Event {
    private final float partialTicks;
    private ScaledResolution sr;
}
