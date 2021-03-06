package com.useful.ucars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class CarHealthData implements MetadataValue {
	double health = 5;
	Plugin plugin = null;

	public CarHealthData(double health, Plugin plugin) {
		this.health = health;
		this.plugin = plugin;
	}

	// @Override
	public boolean asBoolean() {
		return false;
	}

	// @Override
	public byte asByte() {
		return 0;
	}

	// @Override
	public double asDouble() {
		return health;
	}

	// @Override
	public float asFloat() {
		return (float) (health);
	}

	// @Override
	public int asInt() {
		return (int) Math.floor(health + 0.5f);
	}

	// @Override
	public long asLong() {
		return Math.round(health);
	}

	// @Override
	public short asShort() {
		return Short.parseShort("" + health);
	}

	// @Override
	public String asString() {
		return "" + health;
	}

	// @Override
	public Plugin getOwningPlugin() {
		return plugin;
	}

	// @Override
	public void invalidate() {
		health = 0;
		return;
	}

	// @Override
	public Object value() {
		return health;
	}

	public void damage(double amount, Minecart entity) {
		health = ((int)this.health - amount);
		if (health <= 0) {
			die(entity);
		}
		return;
	}
	
	public void damage(double amount, Minecart entity, Player whoHurt) {
		health = ((int)this.health - amount);
		if (health <= 0) {
			die(entity, whoHurt);
		}
		return;
	}

	public void setHealth(double amount) {
		this.health = ((int)amount);
	}

	public double getHealth() {
		return this.health;
	}
	
	public void die(Minecart m, Player whoHurt){
		if(m == null || !m.isValid() || m.isDead()){
			return;
		}
		Bukkit.getPluginManager().callEvent(new ucarDeathEvent(m, whoHurt));
		return;
	}

	public void die(Minecart m){
		if(m == null || !m.isValid() || m.isDead()){
			return;
		}
		Bukkit.getPluginManager().callEvent(new ucarDeathEvent(m));
		return;
	}
}
