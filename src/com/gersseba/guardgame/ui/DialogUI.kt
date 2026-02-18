//package com.gersseba.guardgame.ui
//
//import korlibs.image.color.*
//import korlibs.korge.view.*
//import korlibs.korge.input.*
//import korlibs.time.*
//import korlibs.event.Key
//import korlibs.korge.time.*
//import kotlinx.coroutines.*
//import kotlin.time.Duration
//
//class DialogUI(val stage: Stage) {
//    private var currentDialogContainer: Container? = null
//    private var currentInputTextView: Text? = null
//    private var keyEventListener: AutoCloseable? = null
//    private val padding = 10.0
//
//    fun showDialog(message: String, duration: kotlin.time.Duration = 3.seconds) {
//        closeDialog()
//
//        currentDialogContainer = stage.container {
//            solidRect(this@DialogUI.stage.width, this@DialogUI.stage.height, Colors.BLACK.withAd(0.5))
//
//            val dialogWidth = this@DialogUI.stage.width * 0.8
//            val dialogHeight = 100.0
//
//            container {
//                xy((this@DialogUI.stage.width - dialogWidth) / 2, (this@DialogUI.stage.height - dialogHeight) / 2)
//
//                solidRect(dialogWidth, dialogHeight, Colors["#404040"])
//                solidRect(dialogWidth, 2.0, Colors.WHITE)
//
//                text(message, 16.0, Colors.WHITE).xy(padding, padding)
//            }
//        }
//
//        stage.delay(Duration.fromSeconds(5))
//        stage.delayFrame() {
//            closeDialog()
//        }
//    }
//
//    fun showInputDialog(prompt: String, onSubmit: (String) -> Unit) {
//        closeDialog()
//
//        var inputText = ""
//        currentDialogContainer = stage.container {
//            solidRect(this@DialogUI.stage.width, this@DialogUI.stage.height, Colors.BLACK.withAd(0.5))
//
//            val dialogWidth = this@DialogUI.stage.width * 0.7
//            val dialogHeight = 180.0
//            val dialogX = (this@DialogUI.stage.width - dialogWidth) / 2
//            val dialogY = (this@DialogUI.stage.height - dialogHeight) / 2
//
//            container {
//                xy(dialogX, dialogY)
//                solidRect(dialogWidth, dialogHeight, Colors["#404040"])
//
//                text(prompt, 16.0, Colors.WHITE).xy(padding, padding)
//
//                val inputBoxY = 50.0
//                solidRect(dialogWidth - 2 * padding, 30.0, Colors["#282828"]).xy(padding, inputBoxY)
//
//                currentInputTextView = text(inputText, 14.0, Colors.WHITE).xy(padding + 5, inputBoxY + 5)
//
//                uiButton("Submit", Colors["#649664"], padding, dialogHeight - 40) {
//                    onSubmit(inputText)
//                    closeDialog()
//                }
//
//                uiButton("Cancel", Colors["#966464"], dialogWidth - 80 - padding, dialogHeight - 40) {
//                    closeDialog()
//                }
//            }
//        }
//
//        stage.keys {
//            down(Key.BACKSPACE) {
//                if (inputText.isNotEmpty()) {
//                    inputText = inputText.dropLast(1)
//                    currentInputTextView?.text = inputText
//                }
//            }
//            down(Key.ENTER) {
//                onSubmit(inputText)
//                closeDialog()
//            }
//        }
//    }
//
//    private fun Container.uiButton(label: String, color: RGBA, x: Double, y: Double, action: () -> Unit) {
//        container {
//            this.xy(x, y)
//            solidRect(80.0, 30.0, color)
//            text(label, 14.0, Colors.WHITE).xy((80.0 - label.length * 8) / 2, 8.0)
//            onClick { action() }
//        }
//    }
//
//    fun closeDialog() {
//        currentDialogContainer?.removeFromParent()
//        currentDialogContainer = null
//        keyEventListener?.close()
//        keyEventListener = null
//    }
//}