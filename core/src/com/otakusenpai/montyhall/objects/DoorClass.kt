package com.otakusenpai.montyhall.objects

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

class DoorClass() {
    open lateinit var openSprite: Sprite
    open lateinit var closedSprite: Sprite

    open var position = Vector2()

    open var height: Float = 0.0f
    open var width: Float = 0.0f

    open var isOpen: Boolean = false
    open var isGoat: Boolean = false

    constructor(height: Float,width: Float): this() {
        this.height = height
        this.width = width
    }

    fun render(batch: SpriteBatch) {
        println("In DoorClass")
        if(isOpen) {
            println("Door opened")
            openSprite.draw(batch)
        } else if(!isOpen) {
            println("Door closed")
            closedSprite.draw(batch)
        }
        println("Exit DoorClass")
    }
}

