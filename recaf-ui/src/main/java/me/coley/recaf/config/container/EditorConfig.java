package me.coley.recaf.config.container;

import me.coley.recaf.config.ConfigContainer;
import me.coley.recaf.config.ConfigID;
import me.coley.recaf.config.Group;
import me.coley.recaf.ui.pane.OutlinePane;
import me.coley.recaf.ui.util.Icons;

/**
 * Config container for editor values.
 *
 * @author Matt Coley
 */
public class EditorConfig implements ConfigContainer {
	/**
	 * Show types of fields and methods in the {@link OutlinePane}.
	 */
	@Group("outline")
	@ConfigID("showoutlinedtypes")
	public boolean showOutlinedTypes = false;

	/**
	 * Show synthetic fields and methods in the {@link OutlinePane}.
	 */
	@Group("outline")
	@ConfigID("showoutlinedsynths")
	public boolean showOutlinedSynthetics = false;

	/**
	 * Preferred decompiler implementation.
	 * See {@link me.coley.recaf.decompile.DecompileManager}.
	 */
	@Group("decompile")
	@ConfigID("implementation")
	public String decompiler = "CFR";

	/**
	 * Time to wait until cancelling a decompile for taking too long.
	 */
	@Group("decompile")
	@ConfigID("timeout")
	public int decompileTimeout = 10_000;

	@Override
	public String iconPath() {
		return Icons.ACTION_EDIT;
	}

	@Override
	public String internalName() {
		return "conf.editor";
	}
}