package shadows.map.core;

import shadows.map.proxy.CommonProxy;

public class ConfigFile {

	public static void syncConfig() { // Gets called from preInit
		try {
			// Load config
			CommonProxy.config.load();

		} catch (Exception e) {
			// Failed reading/writing, just continue
		} finally {
			// Save props to config IF config changed
			if (CommonProxy.config.hasChanged())
				CommonProxy.config.save();
		}
	}

}
