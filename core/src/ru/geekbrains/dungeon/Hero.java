package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;

public class Hero {
    private ProjectileController projectileController;
    private Vector2 position;
    private TextureRegion texture;
    private Vector2 velocity;
    private float indent;
    private boolean isActive;

    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 100);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
        this.velocity = new Vector2(300, 300);
        this.indent = 20;
        this.isActive = false;
    }

    public void update(float dt) {
        move(dt);
        checkBounds();

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            switchMode();
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isActive == true){
            projectileController.activate(position.x, position.y, 200, 40);
            projectileController.activate(position.x, position.y, 200, 0);
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isActive == false) {
            projectileController.activate(position.x, position.y, 200, 0);
        }
    }

    private boolean switchMode(){
        if (isActive == false) {
            isActive = true;
        } else if (isActive == true) {
            isActive = false;
        }
        return isActive;
    }



    private void move(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            position.x += velocity.x * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            position.y += velocity.y * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            position.y -= velocity.y * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            position.x -= velocity.x * dt;
        }
    }

    private void checkBounds(){
        if (position.x - indent < 0){
            position.x = indent;
        }
        if (position.x + indent > 800){
            position.x = 800 - indent;
        }
        if (position.y - indent < 0){
            position.y = indent;
        }
        if (position.y >  700){
            position.y = 700;
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 20, position.y - 20);
    }
}
