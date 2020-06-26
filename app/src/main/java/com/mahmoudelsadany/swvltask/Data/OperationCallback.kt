package com.mahmoudelsadany.swvltask.Data

interface OperationCallback<T> {
    fun onSuccess(data:T?)
    fun onError(error:String?)
}