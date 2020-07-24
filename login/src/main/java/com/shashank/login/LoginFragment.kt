package com.shashank.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener {
            if (isValid()) {

                LoginModule.onLoginSuccess(username.editText?.text.toString())
            }
        }
    }

    private fun isValid() =
        !username.editText?.text.isNullOrBlank() && !pasword.editText?.text.isNullOrBlank()
}