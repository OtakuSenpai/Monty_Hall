package com.otakusenpai.montyhall.managers

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.otakusenpai.montyhall.State

open class TextManager() {
    lateinit var font: BitmapFont
    var glyphLayout = GlyphLayout()

    val start = "Select a door please !"
    val win = "You Win!"
    val lose = "You Lose!"
    val choose = "Please select another door :)"

    var width: Float = 0f
    var height: Float = 0f

    constructor(width: Float,height: Float): this() {
        this.width = width
        this.height = height

        font = BitmapFont()
        font.color = Color.CORAL

        font.data.scaleX = width/1600f
        font.data.scaleY = height/1800f
    }

    fun displayMessage(gameManager: GameManager,batch: SpriteBatch) {
        when(gameManager.state) {
            State.Start -> {
                glyphLayout.setText(font,start)
                font.draw(batch,glyphLayout,(width/2 - glyphLayout.width/2),(height/2 -glyphLayout.height/2))
            }
            State.Confirm -> {
                glyphLayout.setText(font,choose)
                font.draw(batch,glyphLayout,(width/2 - glyphLayout.width/2),(height/2 -glyphLayout.height/2))
            }
            State.End -> {
                if(gameManager.hasWon) {
                    glyphLayout.setText(font,win)
                    font.draw(batch,glyphLayout,(width/2 - glyphLayout.width/2),(height/2 -glyphLayout.height/2))
                } else {
                    glyphLayout.setText(font,lose)
                    font.draw(batch,glyphLayout,(width/2 - glyphLayout.width/2),(height/2 -glyphLayout.height/2))
                }
            }
        }
    }
}