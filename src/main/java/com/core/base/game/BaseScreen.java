package com.core.base.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.*;

import java.lang.reflect.InvocationTargetException;

public class BaseScreen implements Screen {
    private boolean centerCamera;
    private Color bgColor;
    private final OrthographicCamera camera;
    private Viewport viewport;
    protected Stage stage;
    
    public BaseScreen(float worldWidth, float worldHeight) {
        this(worldWidth, worldHeight, FitViewport.class);
    }
    
    public BaseScreen(float worldWidth, float worldHeight, Class<? extends Viewport> cls) {
        try {
            camera = new OrthographicCamera();
            setViewport(worldWidth, worldHeight, cls);
            stage = new Stage();
            setCenterCamera(true);
            setBgColor(Color.WHITE);
        } catch (NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void updateCamera() { camera.update(); }
    
    public void updateViewport(int worldWidth, int worldHeight) {
        viewport.update(worldWidth, worldHeight, isCenterCamera());
    }
    
    public void setViewport(float worldWidth, float worldHeight, Class<? extends Viewport> cls)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            viewport = cls
                    .getConstructor(float.class, float.class, Camera.class)
                    .newInstance(worldWidth, worldHeight, camera);
        }catch (NoSuchMethodException ignored){
            viewport = cls
                    .getConstructor(float.class, float.class)
                    .newInstance(worldWidth, worldHeight);
            camera.position.set(worldWidth / 2.0f, worldHeight / 2.0f, 0);
            camera.update();
            
            viewport.setCamera(camera);
        }
    }
    
    public void setBgColor(Color bgColor) { this.bgColor = bgColor; }
    
    public Color getBgColor() { return bgColor; }
    
    public BaseScreen setCenterCamera(boolean centerCamera) {
        this.centerCamera = centerCamera;
        return this;
    }
    
    public boolean isCenterCamera() { return centerCamera; }
    
    @Override
    public void show() { }
    
    private void clear() {
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    
    @Override
    public void render(float dt) {
        clear();
        
        updateCamera();
        
        stage.act(dt);
        stage.draw();
    }
    
    @Override
    public void resize(int worldWidth, int worldHeight) {
        updateViewport(worldWidth, worldHeight);
        updateCamera();
    }
    
    @Override
    public void pause() { }
    
    @Override
    public void resume() { }
    
    @Override
    public void hide() { }
    
    @Override
    public void dispose() { stage.dispose(); }
}
