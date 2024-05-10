package project.senior.event.impl.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.Packet;
import project.senior.event.Event;

@Getter
@Setter
@AllArgsConstructor
public final class EventPacket extends Event {
    private Packet<?> packet;
}
