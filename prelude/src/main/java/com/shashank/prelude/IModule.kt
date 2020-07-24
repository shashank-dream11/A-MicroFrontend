package com.shashank.prelude

import java.lang.ref.WeakReference

abstract class BaseModule<Listener> {

    val callbacks = HashSet<WeakReference<Listener>>()

    fun register(c: Listener)  {
        if(!callbacks.mapNotNull { it.get() }.contains(c)) {
            callbacks.add(WeakReference(c))
        }
    }
}