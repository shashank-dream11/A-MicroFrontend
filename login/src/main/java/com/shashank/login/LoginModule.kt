package com.shashank.login

import com.shashank.logincontract.ILoginCallback
import com.shashank.logincontract.ILoginModule
import java.lang.ref.WeakReference

object LoginModule : ILoginModule {

    private val callbacks = HashSet<WeakReference<ILoginCallback>>();

    override fun getLoginFragment() = LoginFragment()

    override  fun registerLoginListener(callback : ILoginCallback) {
        callbacks.add(WeakReference(callback))
    }

    internal fun onLoginSuccess(userName : String) {
        callbacks.mapNotNull { it.get() }.forEach { it.onLoginSuccess(userName) }
    }
}

