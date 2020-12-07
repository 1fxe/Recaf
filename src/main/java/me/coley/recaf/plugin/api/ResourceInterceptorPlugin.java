package me.coley.recaf.plugin.api;

/**
 * Allows loading of custom resource
 *
 * @author Filip
 */
public interface ResourceInterceptorPlugin extends BasePlugin {

    /**
     * @param name resource name
     * @param ext resource extension
     * @return the actual extension of the resource
     */
    String onLoad(String name, String ext);

}