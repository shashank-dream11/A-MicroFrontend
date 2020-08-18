//package com.shashank.router
//
//import com.shashank.modulecontracts.Message
//
//object Router {
//
//    val modules = HashMap<String,  (Message) -> Message>()
//
//    fun register(name: String, callback: (Message) -> Message) {
//            modules[name] = callback
//    }
//
//    fun send(to: String, msg: Message) : Message {
//        return modules[to]?.invoke(msg)?:Message.Empty
//    }
//}

package com.shashank.router

import arrow.core.Either
import com.shashank.modulecontracts.Disposable
import com.shashank.modulecontracts.Message
import com.shashank.modulecontracts.Status
import com.shashank.modulecontracts.ValidationError


typealias MessageContract = (Message, String?) -> Either<ValidationError, Status>
object Router {

    val returnAddressMap = HashMap<String, String>()

    private val modules = HashMap<String, ArrayList<MessageContract>>()

    private val idMap = HashMap<String, MessageContract>()

    var isInitialised = false;

    fun init(modules : List<IModule>) {
        modules.forEach {
            it.register()
        }
    }

    fun subscribe(name: String, id: String, callback: MessageContract) : Disposable {
        val regModules = modules[name]?:ArrayList()
        modules[name] = regModules.apply { add(callback) }
        idMap[id] = callback;
        return object : Disposable {
            override fun dispose() {
                regModules.remove(callback)
            }

            override val isDisposed: Boolean
                get() = regModules.contains(callback)

        }
    }

    fun publish(to: String, toId : String?, from : String?, msg: Message) : Either<ValidationError, Int>  {
        return try {
            val toSend = ArrayList<MessageContract>()
            toId?.let { toId ->
                idMap[toId]?.let {
                    toSend.add(it)
                }
            }?: modules[to]?.let {
                toSend.addAll(it)
            }

            toSend.forEach {
                it(msg, from)
            }
            Either.right(toSend.size)
        } catch (e: Exception) {
            Either.left(ValidationError.InvalidationStructure(e))
        }
    }
}