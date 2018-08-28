package com.otakusenpai.montyhall.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.otakusenpai.montyhall.State
import com.otakusenpai.montyhall.objects.DoorClass

open class InputManager(gameManager: GameManager) {
    val gameManager = gameManager

    open fun handleInput(camera: OrthographicCamera) {
        if(Gdx.input.justTouched()) {
            gameManager.temp.set(Gdx.input.getX().toFloat(),Gdx.input.getY().toFloat(),0f)
            camera.unproject(gameManager.temp)

            var touchX = gameManager.temp.x
            var touchY = gameManager.temp.y

            for(door in gameManager.doorList) {
                if(!door.isOpen) {
                    if(handleDoor(door,touchX,touchY)) {
                        break
                    }
                }
            }
        }
    }

    open fun handleDoor(door: DoorClass,touchX: Float, touchY: Float): Boolean {
        var found = false

        if((touchX >= door.position.x && touchX <= (door.position.x + door.width)) &&
                (touchY >= door.position.y && touchY <= (door.position.y + door.height))) {
            door.isOpen = true
            found = true
        }

        when(gameManager.state) {
            State.Start -> {
                if(found) {
                    gameManager.state = State.Confirm
                    if(!door.isGoat) {
                        gameManager.hasWon = true
                    }
                }
            }

            State.Confirm -> {
                if(found) {
                    if(!door.isGoat) {
                        gameManager.hasWon = true
                    }
                    gameManager.state = State.End
                }
            }
        }

        return found
    }
}