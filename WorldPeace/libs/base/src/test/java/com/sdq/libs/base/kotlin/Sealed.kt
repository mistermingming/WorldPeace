package com.sdq.libs.base.kotlin

/**
 *
 * @PackageName com.sdq.libs.base.kotlin
 * @date 2022/11/25 9:45
 * @author songdongqi
 */

/**
 * Sealed Class
 * Sealed Interface
 * Sealed Class & Interface VS Enum
 * Sealed Class VS Interface
 */

/**
 * 密封类
 * 对其他 module 隐藏
 * 反编译的 Kotlin 代码可以看到 sealed class 本身被编译为 abstract class。
 * sealed class 是抽象类，可以拥有抽象方法，无法直接实例化。
 * 除了在 sealed class 内嵌套子类外，还可以在外部扩展子类：
 * 除了可以在同文件下 sealed class 外扩展子类外，还可以在同包名不同文件下扩展。
 */
sealed class GameAction(times: Int) {
    // Inner of Sealed Class
    object Start : GameAction(1)
    data class AutoTick(val time: Int) : GameAction(2)
    class Exit : GameAction(3)
}

sealed class PermissionResult

//问题模拟
//层级混乱、阅读性不佳，甚至有的时候功能相近的时候还得特意取个不同的名称
enum class Action {
    Tick,
    // GameAction
    Start, Exit, Restart,
    // BirdAction
    Up, Down, HitGround, HitPipe, CrossedPipe,
    // PipeAction
    Move, Reset,
    // RoadAction
    // 防止和 Pipe 的 Action 重名导致编译出错，
    // 将功能差不多的 Road 移动和重置 Action 定义加上了前缀
    RoadMove, RoadReset
}

fun dispatch(action: Action) {
    when (action) {
        Action.Tick -> TODO()

        Action.Start -> TODO()
        Action.Exit -> TODO()
        Action.Restart -> TODO()

        Action.Up -> TODO()
        Action.Down -> TODO()
        Action.HitGround -> TODO()
        Action.HitPipe -> TODO()
        Action.CrossedPipe -> TODO()

        Action.Move -> TODO()
        Action.Reset -> TODO()

        Action.RoadMove -> TODO()
        Action.RoadReset -> TODO()
    }
}

sealed interface SealedAction

enum class BirdAction : SealedAction {
    Up, Down, HitGround, HitPipe, CrossedPipe
}

enum class PipeAction : SealedAction {
    Move, Reset
}

enum class RoadAction : SealedAction {
    Move, Reset
}

object Tick: SealedAction

fun dispatchSealedAction(action: SealedAction) {
    when (action) {
        Tick -> TODO()

        is BirdAction -> {
            when (action) {
                BirdAction.Up -> TODO()
                BirdAction.Down -> TODO()
                else -> TODO()
            }
        }
        is PipeAction -> {
            when (action) {
                PipeAction.Move -> TODO()
                PipeAction.Reset -> TODO()
            }
        }
        is RoadAction -> {
            when (action) {
                RoadAction.Move -> TODO()
                RoadAction.Reset -> TODO()
            }
        }
    }
}