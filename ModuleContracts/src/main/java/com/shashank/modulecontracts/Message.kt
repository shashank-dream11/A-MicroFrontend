package com.shashank.modulecontracts

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import java.io.Serializable
import java.lang.Exception

sealed class Message {

    object Empty : Message()
    class IntegerMessage(val value: Int) : Message()
    class StringMessage(val value: String) : Message()
    class BooleanMessage(val value: Boolean) : Message()
    class SerializableMessage(val value: Serializable) : Message()
    class FragmentMessage(val value: Fragment) : Message()
    class ViewMessage(val value: View) : Message()
    class ContextMessage(val value: Context) : Message()
    class ArrayMessage(vararg ip: Message) : Message() {

        val content = arrayOf(ip)
    }

    class ComplexMessage(funct: ComplexMessage.() -> Unit) : Message() {

        val content = HashMap<String, Message>()

        init {
            this.funct()
        }

        fun <T : Message> get(name: String): T? {
            val item = content[name]
            return item as T?
        }

        fun int(key: String, value: Int) = IntegerMessage(value).apply {
            content[key] = this
        }

        fun string(key: String, value: String) = StringMessage(value).apply {
            content[key] = this
        }

        fun fragment(key: String, value: Fragment) = FragmentMessage(value).apply {
            content[key] = this
        }

        fun view(key: String, value: View) = ViewMessage(value).apply {
            content[key] = this
        }

        fun boolean(key: String, value: Boolean) = BooleanMessage(value).apply {
            content[key] = this
        }

        fun context(key: String, value: Context) = ContextMessage(value).apply {
            content[key] = this
        }

        fun serializable(key: String, value: Serializable) = SerializableMessage(value).apply {
            content[key] = this
        }

        fun complex(key: String, value: ComplexMessage.() -> Unit): ComplexMessage {
            val msg = ComplexMessage(value);
            content[key] = msg
            return msg
        }

        fun complex(key: String, value: ComplexMessage): ComplexMessage {
            content[key] = value
            return value
        }

        fun array(key: String, vararg values: Message): ArrayMessage {
            val msg = ArrayMessage(*values)
            content[key] = msg
            return msg
        }
    }

}

fun createSampleMessage() = Message.ComplexMessage {
    int("matchId", 1)
    string("matchName", "test")
    complex("tour") {
        int("tourId", 1)
        string("tourName", "test")
        array(
            "Arraytest",
            string("test", "test"),
            string("test", "test"),
            string("test", "test")
        )
    }
}

sealed class ValidationError {
    class FieldNotFound(val fieldName: String) : ValidationError()
    class InvalidationStructure(val exception: Exception) : ValidationError()
    object TargetNotFound : ValidationError()
}

sealed class Status {
    object Success : Status()
}

interface Disposable {

    fun dispose()

    val isDisposed: Boolean
}