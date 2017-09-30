package node_structure;

import java.awt.*;
import java.util.function.BooleanSupplier;

public abstract class NFANode {
    static final Color present = new Color(12, 244, 0, 180);
    static final Color empty = new Color(255, 8, 0, 180);
    static final Color selection = new Color(255, 219, 0, 180);
    private NFANode success, failure;
    private NFAHandler handler;
    private String description;

    NFANode(final NFAHandler handler, final String description) {
        this.handler = handler;
        this.description = description;
    }

    NFAHandler getHandler() {
        return handler;
    }

    String getDescription() {
        return description;
    }

    protected abstract void action();

    protected abstract NFANode determine();

    protected abstract void transition();

    void setSuccess(final NFANode success) {
        this.success = success;
    }

    void setFailure(final NFANode failure) {
        this.failure = failure;
    }

    NFANode getSuccess() {
        return success;
    }

    NFANode getFailure() {
        return failure;
    }

    void sleepUntil(final BooleanSupplier condition) {
        new SleepAction(condition, 3500).sleep();
    }
}
