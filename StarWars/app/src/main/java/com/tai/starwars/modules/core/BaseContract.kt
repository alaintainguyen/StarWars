package com.tai.starwars.modules.core

interface BaseContract {

    interface View

    interface Presenter {

        fun subscribe(view: BaseContract.View)

        fun unsubscribe(view: BaseContract.View)

    }

    interface Router
}

