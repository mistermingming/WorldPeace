package com.sdq.libs.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import java.util.concurrent.atomic.AtomicLong

/**
 *
 * @PackageName com.sdq.libs.base
 * @date 2022/8/4 13:22
 * @author songdongqi
 */
interface Event {
    val eventId: Long

    companion object {
        private val EVENT_ID: AtomicLong = AtomicLong(0)
        fun generateEventId(): Long = EVENT_ID.getAndIncrement()
        fun get(): Event = EmptyEvent()
    }
}

private class EmptyEvent : Event {
    override val eventId: Long = Event.generateEventId()
}

fun <T : Event> Flow<T>.distinctEvent(): Flow<T> = distinctUntilChangedBy { it.eventId }