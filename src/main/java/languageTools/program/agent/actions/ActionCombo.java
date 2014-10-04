/**
 * The GOAL Grammar Tools. Copyright (C) 2014 Koen Hindriks.
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package languageTools.program.agent.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import krTools.language.Substitution;

/**
 * An {@link ActionCombo} is a list of actions that have been combined by the +
 * operator in a {@link Rule} of an agent program.
 * <p>
 * An action combo is executed whenever the first action in the sequence can be
 * performed, i.e., the precondition of that action holds. Preconditions of
 * other actions are only inspected after the actions that precede the actions
 * in the list have been executed. If a precondition that is evaluated fails,
 * the execution of the action combo is terminated and the remaining actions are
 * not performed.
 * </p>
 */
public class ActionCombo implements Iterable<Action<?>> {

	/**
	 * A list of ordered actions that are part of this {link ActionCombo}.
	 */
	private List<Action<?>> actions = new ArrayList<Action<?>>();

	/**
	 * Creates an (empty) action combo.
	 */
	public ActionCombo() {

	}

	/**
	 * Returns the {@link Action}s of this {@link ActionCombo}.
	 * 
	 * @return The actions that are part of this action combo.
	 */
	public List<Action<?>> getActions() {
		return actions;
	}
	
	/**
	 * (Re)sets the actions in this {@link ActionCombo}.
	 * 
	 * @param actions A list of actions.
	 */
	public void setActions(List<Action<?>> actions) {
		this.actions = actions;
	}

	/**
	 * Adds an {@link Action} to (the end of) the list of actions that are part of
	 * this {@link ActionCombo}.
	 * 
	 * @param action The action to be added.
	 */
	public void addAction(Action<?> action) {
		this.actions.add(action);
	}

	/**
	 * Applies the given substitution to this {@link ActionCombo} by applying it
	 * to each of the {@link Action}s that are part of this combo.
	 * 
	 * @param substitution The substitution to be applied to the action.
	 * @return The instantiated combo action where (free) variables that are
	 *         bound by the substitution have been instantiated by the
	 *         corresponding terms in the substitution.
	 */
	public ActionCombo applySubst(Substitution substitution) {
		ActionCombo actions = new ActionCombo();
		
		for (Action<?> action : this) {
			actions.addAction((Action<?>) action.applySubst(substitution));
		}
		return actions;
	}
	
	/**
	 * @return The number of actions that are part of this combo.
	 */
	public int size() {
		return actions.size();
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		if (this.actions.size() > 0) {
			sBuilder.append(this.actions.get(0).toString());
			for (int i = 1; i < this.actions.size(); i++) {
				sBuilder.append(" + " + this.actions.get(i).toString());
			}
		}
		return sBuilder.toString();
	}

	@Override
	public Iterator<Action<?>> iterator() {
		return this.actions.iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((actions == null) ? 0 : actions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ActionCombo other = (ActionCombo) obj;
		if (actions == null) {
			if (other.actions != null) {
				return false;
			}
		} else if (!actions.equals(other.actions)) {
			return false;
		}
		return true;
	}

}
