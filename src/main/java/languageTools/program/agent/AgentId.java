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

package languageTools.program.agent;

import java.util.Objects;

/**
 *
 */
public class AgentId {
	private final String name;

	/**
	 * Constructs a new AgentId.
	 *
	 * @param name
	 *            unique name of the agent
	 */
	public AgentId(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof AgentId) {
			String othername = ((AgentId) other).getName();
			return Objects.equals(this.name, othername);
		} else {
			return false;
		}
	}
}
