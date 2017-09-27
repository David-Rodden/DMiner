package node_structure;

import org.osbot.rs07.utility.ConditionalSleep;

import java.util.function.BooleanSupplier;

public class SleepAction extends ConditionalSleep {
    private final BooleanSupplier condition;

    SleepAction(final BooleanSupplier condition, final int timeout) {
        super(timeout);
        this.condition = condition;
    }

    @Override
    public boolean condition() throws InterruptedException {
        return condition.getAsBoolean();
    }
}