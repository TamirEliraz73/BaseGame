package com.core.handler;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.GdxRuntimeException;
import org.jetbrains.annotations.NotNull;

/**
 * <p>This is a singleton class designed to wrap the original class (see {@link AssetManager}) with a simple
 * and user-friendly methodology.</p>
 * <p>Note: It also includes a {@link #dispose()} method to be called when finished using the class, in order to
 * avoid memory leaks.</p>
 *
 * @author Tamir Eliraz
 */
public class AssetManagerHandler {
    private final AssetManager assetManager;
    private static AssetManagerHandler instance;
    
    private AssetManagerHandler() { assetManager = new AssetManager(); }
    
    /**
     * @return the single instance of {@link AssetManagerHandler}
     */
    public static AssetManagerHandler getInstance() {
        if (instance == null) instance = new AssetManagerHandler();
        return instance;
    }
    
    /**
     * This method is intended for debugging purposes only.
     * It provides direct access to {@link #assetManager}
     */
    public AssetManager getAssetManager() {
        return assetManager;
    }
    
    /**
     * Uploads a {@link Texture} file, automatically waiting for the upload to finish.
     *
     * @param fileName
     *         the name of the {@link Texture} file to upload
     * @return an instance of this class to allow for method chaining
     */
    public AssetManagerHandler tryLoadTexture(String fileName) {
        return tryLoadTexture(fileName, true);
    }
    
    /**
     * Uploads a {@link Texture} file if it does not already exist in memory.
     *
     * @param fileName
     *         the name of the {@link Texture} file to upload
     * @param waitForCompletion
     *         a boolean indicating whether to wait for the upload to finish
     * @return an instance of this class to allow for method chaining
     */
    public AssetManagerHandler tryLoadTexture(String fileName, boolean waitForCompletion) {
        if (!assetManager.isLoaded(fileName)) {
            assetManager.load(fileName, Texture.class);
            if (waitForCompletion)
                assetManager.finishLoadingAsset(fileName);
        }
        return this;
    }
    
    /**
     * Uploads an array of {@link Texture} files, automatically waiting for each upload to finish.
     *
     * @param fileNames
     *         an array of {@link Texture} file names to upload
     * @return an instance of this class to allow for method chaining
     */
    public AssetManagerHandler tryLoadTexture(String @NotNull [] fileNames) {
        return tryLoadTexture(fileNames, true);
    }
    
    /**
     * Uploads an array of {@link Texture} files, optionally waiting for each upload to finish.
     *
     * @param fileNames
     *         an array of {@link Texture} file names to upload
     * @param waitForCompletion
     *         a boolean indicating whether to wait for each upload to finish
     * @return an instance of this class to allow for method chaining
     */
    public AssetManagerHandler tryLoadTexture(String @NotNull [] fileNames, boolean waitForCompletion) {
        for (String fileName : fileNames) tryLoadTexture(fileName, waitForCompletion);
        return this;
    }
    
    /**
     * Returns the {@link Texture} associated with the given file name if it exists.
     *
     * @param fileName
     *         the name of the {@link Texture} file
     * @return the {@link Texture} associated with the specified file name
     * @throws GdxRuntimeException
     *         if the {@link Texture} does not exist
     */
    public Texture getTexture(String fileName) { return assetManager.get(fileName, Texture.class); }
    
    
    /**
     * Returns the {@link Texture} associated with the given file name if it exists.
     * Optionally sets the {@link TextureFilter} for both minification and magnification.
     *
     * @param fileName
     *         the name of the {@link Texture} file
     * @param filter
     *         the {@link TextureFilter} to use for both minification and magnification
     * @return the {@link Texture} associated with the specified file name
     * @throws GdxRuntimeException
     *         if the {@link Texture} does not exist
     */
    public Texture getTexture(String fileName, TextureFilter filter) {
        return getTexture(fileName, filter, filter);
    }
    
    /**
     * Returns the {@link Texture} associated with the given file name if it exists.
     * Optionally sets the {@link TextureFilter} for both minification and magnification.
     *
     * @param fileName
     *         the name of the {@link Texture} file
     * @param minFilter
     *         the {@link TextureFilter} to use for minification
     * @param magFilter
     *         the {@link TextureFilter} to use for magnification
     * @return the {@link Texture} associated with the specified file name
     * @throws GdxRuntimeException
     *         if the {@link Texture} does not exist
     */
    public Texture getTexture(String fileName, TextureFilter minFilter, TextureFilter magFilter) {
        Texture texture = getTexture(fileName);
        texture.setFilter(minFilter, magFilter);
        return texture;
    }
    
    /**
     * Disposes the AssetManager, releasing all resources and preventing memory leaks.
     * This method should be called when the AssetManager is no longer needed.
     */
    public void dispose() {
        assetManager.dispose();
    }
}
