package com.shashank.messagebasedmodularization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import arrow.core.Either
import com.shashank.messagebasedmodularization.AppModule
import com.shashank.messagebasedmodularization.R
import com.shashank.modulecontracts.Message
import com.shashank.modulecontracts.Status
import com.shashank.router.Router

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Router.publish("open_match_center", null, AppModule.moduleId, Message.Empty)

        Router.subscribe("return_open_match_center" , AppModule.moduleId) { msg, id->
            if(msg is Message.FragmentMessage) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, msg.value)
                    .commit()
            }
            Either.right(Status.Success)
        }


    }
}