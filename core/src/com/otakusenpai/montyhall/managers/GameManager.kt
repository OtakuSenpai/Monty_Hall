package com.otakusenpai.montyhall.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.otakusenpai.montyhall.State
import com.otakusenpai.montyhall.objects.DoorClass
import com.otakusenpai.montyhall.random

open class GameManager {
    val doorList = mutableListOf<DoorClass>()

    lateinit var doorTexture: Texture
    lateinit var carTexture: Texture
    lateinit var goatTexture: Texture
    lateinit var background: Texture

    open var state: State = State.Start
    open var goatOrCar = mutableListOf<Boolean>()
    open var hasWon = false
    var temp = Vector3()

    private val DOOR_RESIZE_FACTOR = 2500f
    private val DOOR_VERT_POSITION_FACTOR = 3f
    private val DOOR1_HORIZ_POSITION_FACTOR = 7.77f
    private val DOOR2_HORIZ_POSITION_FACTOR = 2.57f
    private val DOOR3_HORIZ_POSITION_FACTOR = 1.52f
    var width = 0.0f
    var height = 0.0f

    lateinit var backSprite: Sprite

    constructor(height: Float,width: Float,textManager: TextManager) {
        this.height = height
        this.width = width
        doorTexture = Texture(Gdx.files.internal("closed_door.png"))
        carTexture = Texture(Gdx.files.internal("door_with_a_car.png"))
        goatTexture = Texture(Gdx.files.internal("door_with_a_goat.png"))
        background = Texture(Gdx.files.internal("monty_hall.png"))
        backSprite = Sprite(background)
        backSprite.setSize(width, height)
        backSprite.setPosition(0f,0f)
        initDoors()
    }

    fun renderGame(batch: SpriteBatch) {
        println("In gameManager")
        backSprite.draw(batch)
        println("Background drawed")
        for(door in doorList) {
            door.render(batch)
        }
        println("Door rendered")
        println("Exit gameManager")
    }

    fun dispose() {
        doorTexture.dispose()
        carTexture.dispose()
        goatTexture.dispose()
    }

    fun initDoors() {
        for(i in (0..2)) {
            doorList.add(DoorClass())
        }

        doorList.get(0).position.set(width/DOOR1_HORIZ_POSITION_FACTOR,
                height/DOOR_VERT_POSITION_FACTOR)
        doorList.get(0).position.set(width/DOOR2_HORIZ_POSITION_FACTOR,
                height/DOOR_VERT_POSITION_FACTOR)
        doorList.get(0).position.set(width/DOOR3_HORIZ_POSITION_FACTOR,
                height/DOOR_VERT_POSITION_FACTOR)

        for(door in doorList) {
            door.closedSprite = Sprite(doorTexture)
            door.openSprite = Sprite()
            door.width = door.closedSprite.width * (width/DOOR_RESIZE_FACTOR)
            door.height = door.closedSprite.height * (height/DOOR_RESIZE_FACTOR)
            door.closedSprite.setSize(door.width,door.height)
            door.closedSprite.setPosition(door.position.x,door.position.y)

            door.openSprite.setSize(door.width,door.height)
            door.openSprite.setPosition(door.position.x,door.position.y)
        }

        val m = (0..2).random()
        if(m == 0) {
            doorList.get(0).openSprite.setRegion(carTexture)
            doorList.get(0).isGoat = false
            doorList.get(1).openSprite.setRegion(goatTexture)
            doorList.get(0).isGoat = true
            doorList.get(2).openSprite.setRegion(goatTexture)
            doorList.get(0).isGoat = true
        } else if(m == 1) {
            doorList.get(0).openSprite.setRegion(goatTexture)
            doorList.get(0).isGoat = true
            doorList.get(1).openSprite.setRegion(carTexture)
            doorList.get(0).isGoat = false
            doorList.get(2).openSprite.setRegion(goatTexture)
            doorList.get(0).isGoat = true
        } else if(m == 2) {
            doorList.get(0).openSprite.setRegion(goatTexture)
            doorList.get(0).isGoat = true
            doorList.get(1).openSprite.setRegion(goatTexture)
            doorList.get(0).isGoat = true
            doorList.get(2).openSprite.setRegion(carTexture)
            doorList.get(0).isGoat = false
        }
    }
}