package net.tullco.jaipur.state.transitions;

import java.util.List;

import net.tullco.jaipur.exceptions.InvalidStateTransitionException;

public interface StateTransition {
	public void update() throws InvalidStateTransitionException;
	public List<String> getValidOldStates();
}
