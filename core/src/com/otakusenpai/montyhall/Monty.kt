package com.otakusenpai.montyhall

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.otakusenpai.montyhall.managers.GameManager
import com.otakusenpai.montyhall.managers.InputManager
import com.otakusenpai.montyhall.managers.TextManager

open class Monty() : ApplicationListener {
    private lateinit var camera: OrthographicCamera
    private lateinit var batch: SpriteBatch
    private lateinit var gameManager: GameManager
    private lateinit var textManager: TextManager
    private lateinit var inputManager: InputManager
    private var w = 0f
    private var h = 0f

    override fun create() {
        w = Gdx.graphics.width.toFloat()
        h = Gdx.graphics.height.toFloat()

        camera = OrthographicCamera(w,h)

        camera.setToOrtho(false)
        batch = SpriteBatch()
        textManager = TextManager(w,h)
        gameManager = GameManager(h,w,textManager)
        inputManager = InputManager(gameManager)
    }

    override fun dispose() {
        batch.dispose()
        gameManager.dispose()
    }

    override fun render() {
        Gdx.gl.glClearColor(1f,1f,1f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.projectionMatrix = camera.combined

        batch.begin()
        println("Begin")
        inputManager.handleInput(camera)
        println("Input handled")
        gameManager.renderGame(batch)
        println("Game rendered")
        textManager.displayMessage(gameManager,batch)
        println("Text messaged")
        println("End")
        batch.end()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }
}