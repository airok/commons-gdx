package com.gemserk.commons.artemis.systems;

import java.util.ArrayList;

import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.gemserk.commons.artemis.components.ScriptComponent;
import com.gemserk.commons.artemis.scripts.Script;

public class ScriptSystem extends EntityProcessingSystem {

	private static final Class<ScriptComponent> scriptComponentClass = ScriptComponent.class;

	public ScriptSystem() {
		super(ScriptComponent.class);
	}

	@Override
	protected void added(Entity e) {
		super.added(e);
		ArrayList<Script> scripts = e.getComponent(scriptComponentClass).getScripts();
		for (int i = 0; i < scripts.size(); i++) 
			scripts.get(i).init(world, e);
	}

	@Override
	protected void removed(Entity e) {
		ArrayList<Script> scripts = e.getComponent(scriptComponentClass).getScripts();
		for (int i = 0; i < scripts.size(); i++) 
			scripts.get(i).dispose(world, e);
		super.removed(e);
	}

	@Override
	protected void process(Entity e) {
		ArrayList<Script> scripts = e.getComponent(scriptComponentClass).getScripts();
		for (int i = 0; i < scripts.size(); i++) 
			scripts.get(i).update(world, e);
	}

}
