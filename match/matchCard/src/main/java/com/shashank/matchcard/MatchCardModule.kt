package com.shashank.matchcenter

import android.view.View
import arrow.core.Either
import com.shashank.matchcard.MatchCard
import com.shashank.modulecontracts.MatchCardModel
import com.shashank.modulecontracts.Message
import com.shashank.modulecontracts.Status
import com.shashank.modulecontracts.ValidationError
import com.shashank.router.IModule
import com.shashank.router.Router
import java.lang.Exception
import java.util.*

object MatchCardModule : IModule {

    val supportedMsg = arrayOf("get_match_card")

    override val moduleId = UUID.randomUUID().toString()

    init {
        register()
    }

    override fun register() {
        Router.subscribe("get_match_card", moduleId) {msg, id ->
            try {
                when (msg) {
                    is Message.ContextMessage -> {
                        Router.publish(
                            "return_match_card",
                            id,
                            moduleId,
                            Message.ViewMessage(MatchCard(msg.value))
                        )
                        Either.right(Status.Success)
                    }
                    else -> Either.left(ValidationError.InvalidationStructure(Exception("Invalid Structure")))
                }
            } catch (e: Exception) {
                return@subscribe Either.left(ValidationError.InvalidationStructure(e))
            }
        }

        Router.subscribe("bind_match_card", moduleId) {msg, id ->
            try {
                when (msg) {
                    is Message.ComplexMessage -> {
                        decodeBindData(msg)?.let {
                            if (it.first is MatchCard && it.second != null) {
                                (it.first as MatchCard).bindData(it.second!!)
                                Router.publish(
                                    "return_bind_card",
                                    id,
                                    moduleId,
                                    Message.StringMessage("success")
                                )
                                Either.right(Status.Success)
                            } else {
                                Either.left(ValidationError.InvalidationStructure(Exception("Invalid Structure")))
                            }
                        }
                            ?: Either.left(ValidationError.InvalidationStructure(Exception("Invalid Structure")))
                    }
                    else -> Either.left(ValidationError.InvalidationStructure(Exception("Invalid Structure")))
                }
            } catch (e: Exception) {
                return@subscribe Either.left(ValidationError.InvalidationStructure(e))
            }
        }
    }


    fun decodeBindData(msg : Message.ComplexMessage) : Pair<View, MatchCardModel?>? {
        val view = msg.get<Message.ViewMessage>("view")?.value;
        return view?.let {
            val data = msg.get<Message.ComplexMessage>("data");
            return Pair(
                view, if (data != null)
                    decodeMatchCardData(data)
                else null
            )
        }
    }

    fun decodeMatchCardData(msg : Message) : MatchCardModel? {
        return if(msg is Message.ComplexMessage) {
            MatchCardModel(
                msg.get<Message.IntegerMessage>("tourId")?.value?:-1,
                msg.get<Message.StringMessage>("TourName")?.value?:"",
                msg.get<Message.StringMessage>("Team1Name")?.value?:"",
                msg.get<Message.StringMessage>("Team2Name")?.value?:"",
                msg.get<Message.StringMessage>("contestsText")?.value?:""
            )
        } else {
            null;
        }
    }

    fun encode(it: MatchCardModel) = Message.ComplexMessage {
        int("tourId", it.tourId)
        string("TourName", it.TourName)
        string("Team1Name", it.Team1Name)
        string("Team2Name", it.Team2Name)
        string("contestsText", it.contestsText)
    }
}