package data_type;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

import java.util.Optional;

public class Rock {
    private final Script ref;
    private final Position position;
    private int id;
    private boolean swap;

    public Rock(final Script ref, final Position position, final int id) {
        this.ref = ref;
        this.position = position;
        this.id = id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    private Optional<RS2Object> getOptionalObject() {
        //noinspection unchecked
        return ref.getObjects().filter(obj -> obj.exists() && (swap ? obj.getId() == id : obj.getId() != id)).stream().findAny();
    }

    public RS2Object getEntity() {
        final Optional<RS2Object> objectHere = getOptionalObject();
        return objectHere.orElse(null);
    }

    public boolean exists() {
        return getOptionalObject().isPresent();
    }

    public boolean mine(final Script ref) {
        final RS2Object rock = getEntity();
        return rock.exists() && rock.interact("Mine");
    }

    public void swapId() {
        swap = true;
    }
}
