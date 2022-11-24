package com.sdq.biz.home

import com.sdq.libs.base.ControlledRunner
import com.sdq.libs.base.SingleRunner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * @PackageName com.sdq.biz.home
 * @date 2022/11/22 9:04
 * @author songdongqi
 */
sealed class State {
    object Idle : State()
    object Started : State()
    object Paused : State()
    object End : State()
}

enum class Action {
    START,
    PAUSE,
    RESUME,
    SEEK,
    END
}

data class AudioPlayer(
    val currentState: State,
    val currentAction: Action
)

class AudioPlayerService @Inject constructor() {
    private val singleRunner: SingleRunner = SingleRunner()

    private val controlledRunner: ControlledRunner<Any> = ControlledRunner()

    val playerFlow: Flow<AudioPlayer> = TODO()

    suspend fun start() {
        doAction(Action.START)
    }

    suspend fun pause() {
        doAction(Action.PAUSE)
    }

    suspend fun resume() {
        doAction(Action.RESUME)
    }

    suspend fun seek() {
        doAction(Action.SEEK)
    }

    suspend fun end() {
        doAction(Action.END)
    }

    private suspend fun doAction(action: Action) {
        singleRunner.afterPrevious {
            withContext(Dispatchers.IO) {
                when (action) {
                    Action.PAUSE -> {
                        val currentState = playerFlow.first().currentState
                        if (currentState == State.Started) {
                            delay(100)

                        }
                    }
                    Action.RESUME -> {
                        val currentState = playerFlow.first().currentState
                        if (currentState == State.Paused) {
                            delay(100)
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }
}