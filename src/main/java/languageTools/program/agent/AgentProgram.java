/**
 * GOAL interpreter that facilitates developing and executing GOAL multi-agent
 * programs. Copyright (C) 2011 K.V. Hindriks, W. Pasman
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

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import krTools.KRInterface;
import krTools.language.DatabaseFormula;
import krTools.language.Query;
import krTools.parser.SourceInfo;
import languageTools.analyzer.agent.AgentValidator;
import languageTools.parser.GOAL;
import languageTools.program.Program;
import languageTools.program.agent.msc.Macro;

/**
 * <p>A GOAL agent program.</p>
 * 
 * <p>An agent program consists of a set of {@link Module}s.</p>
 * <p>
 * Use {@link AgentValidator} to create an agent program from a text file
 * with extension 'goal'.
 * </p>
 * 
 * @author Koen Hindriks
 */
public class AgentProgram extends Program {
	/**
	 * An agent program is a set of modules, either imported or defined in the
	 * agent program file itself.
	 */
	private List<Module> modules = new ArrayList<Module>();
	/**
	 * <p>List of imported module files.</p>
	 * 
	 * <p>It should be possible to resolve the location of module files that are imported
	 * relative to the source file used to construct this agent program (alternatively,
	 * the path should be absolute).</p>
	 */
	private List<File> importedFiles = new ArrayList<File>();
	
	/**
	 * The knowledge representation language used in this agent program to represent, a.o.,
	 * the knowledge, beliefs, goals, and action pre- and post-conditions. 
	 */
	private KRInterface krInterface = null;
	
	/**
	 * Creates a new (empty) agent program.
	 * 
	 * @param agentfile The source file used to construct this agent program.
	 */
	public AgentProgram(SourceInfo info) {
		super(info);
	}

	/**
	 * @return List of the modules defined in the program file (does <b>not</b>
	 * 			include the imported modules!) 
	 */
	public List<Module> getModules() {
		return modules;
	}

	/**
	 * Adds a module to this agent program.
	 * 
	 * @param module The {@link Module} that is added.
	 */
	public void addModule(Module module) {
		modules.add(module);
	}
	
	/**
	 * @return A list of module files that are imported in this agent program. 
	 */
	public List<File> getImportedModules() {
		return importedFiles;
	}
	
	/**
	 * Adds a module file to the list of imported module files.
	 * 
	 * @param file The module file that is added.
	 */
	public void addImportedModule(File file) {
		importedFiles.add(file);
	}

	/**
	 * @return The {@link KRInterface} used in the agent program.
	 */
	public KRInterface getKRInterface() {
		return krInterface;
	}

	/**
	 * @param krInterface The {@link KRInterface} that is used in the agent program.
	 */
	public void setKRInterface(KRInterface krInterface) {
		this.krInterface = krInterface;
	}

	/**
	 * Checks whether the agent program performs mental model queries.
	 * 
	 * <p>An agent program uses mental model queries if it has a mental state
	 * condition with an agent expression type other than self or this (i.e.,
	 * variable, quantor, or constant).</p>
	 * 
	 * @return {@code true} iff the agent program performs mental model queries.
	 */
	public boolean usesMentalModels() {
// TODO:
//		for (Module m : this.getAllModules()) {
//			if (m.getRuleSet() == null) {
//				continue;
//			}
//			for (Rule rule : m.getRuleSet()) {
//				for (MentalLiteral literal : rule.getCondition().getLiterals()) {
//					for (SelectExpression agentExpression : literal
//							.getSelector().getExpressions()) {
//						if (agentExpression.getType() != SelectorType.SELF
//								&& agentExpression.getType() != SelectorType.THIS) {
//							return true;
//						}
//					}
//				}
//			}
//		}

		return false;
	}

	/**
	 * An agent program can be run if it is valid, all of its modules can be run, and
	 * all of the imported module files have been validated and can be run.
	 */
	@Override
	public boolean canRun() {
		boolean canRun = isValid();
		for (Module module : modules) {
			canRun &= module.canRun();
		}
		// TODO: imported module files (how do we validate these?)
		canRun &= false;
		return canRun;
	}
	
	/**
	 *
	 * @param tokenIndex
	 */
	
	/**
	 * @param tokenIndex Integer index of parser token.
	 * @return String with the name of the token.
	 */
	public static String getTokenName(int tokenIndex) {
		return GOAL.tokenNames[tokenIndex].replaceAll("'", "");
	}
	
	/**
	 * @return A string with the imported file names, module names, and KR interface name.
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for (File file : importedFiles) {
			str.append("import: " + file + "\n");
		}
		for (Module module : modules) {
			str.append("module: " + module + "\n");
		}
		str.append("KR interface: " + krInterface);
		
		return str.toString();
	}

	/**
	 * Builds a string representation of this {@link AgentProgram}.
	 * 
	 * @param linePrefix A prefix used to indent parts of a program, e.g., a single space or tab.
	 * @param indent A unit to increase indentation with, e.g., a single space or tab.
	 * @return A string-representation of this agent program.
	 */
	@Override
	public String toString(String linePrefix, String indent) {
		StringBuilder str = new StringBuilder();

		str.append(linePrefix + "<agent program:\n");
		
		// Imports
		str.append(linePrefix + indent + "<imports:\n");
		Iterator<File> imports = importedFiles.iterator();
		while (imports.hasNext()) {
			str.append(linePrefix + indent + indent + imports.next());
			str.append((imports.hasNext() ? ",\n" : "\n"));
		}
		str.append(linePrefix + indent + ">,\n");
		
		// Modules
		str.append(linePrefix + indent + "<modules:\n");
		Iterator<Module> mods = modules.iterator();
		while (mods.hasNext()) {
			str.append(mods.next().toString(linePrefix + indent + indent, indent));
			str.append((mods.hasNext() ? ",\n" : "\n"));
		}
		str.append(linePrefix + ">,\n");
		
		// KR interface
		str.append(linePrefix + "<KR interface: " + krInterface + ">\n");
		
		str.append(">");
		

		return str.toString();
	}
	
	/**
	 * @return All knowledge specified in all modules.
	 */
	public List<DatabaseFormula> getAllKnowledge() {
		List<DatabaseFormula> knowledge = new LinkedList<>();
		for( Module module : modules ){
			knowledge.addAll(module.getKnowledge());
		}
		return knowledge;
	}
	
	/**
	 * @return All beliefs specified in all modules.
	 */
	public List<DatabaseFormula> getAllBeliefs() {
		List<DatabaseFormula> beliefs = new LinkedList<>();
		for( Module module : modules ){
			beliefs.addAll(module.getBeliefs());
		}
		return beliefs;
	}
	
	/**
	 * @return All goals specified in all modules.
	 */
	public List<Query> getAllGoals() {
		List<Query> goals = new LinkedList<>();
		for( Module module : modules ){
			goals.addAll(module.getGoals());
		}
		return goals;
	}
	
	/**
	 * @return All action specifications in all modules.
	 */
	public List<ActionSpecification> getAllActionSpecs() {
		List<ActionSpecification> specs = new LinkedList<>();
		for( Module module : modules ){
			specs.addAll(module.getActionSpecifications());
		}
		return specs;
	}
	
	/**
	 * @return All macros in all modules.
	 */
	public List<Macro> getAllMacros() {
		List<Macro> specs = new LinkedList<>();
		for( Module module : modules ){
			specs.addAll(module.getMacros());
		}
		return specs;
	}
}