package com.gabrielbmoro.programmingchallenge.ui.base

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import com.gabrielbmoro.programmingchallenge.BR
import org.koin.core.KoinComponent

open class BaseViewModel(application: Application) : AndroidViewModel(application), Observable, KoinComponent {

    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    @get:Bindable
    var isLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    private val lstSubscriptions = ArrayList<rx.Subscription>()

    fun registerDisposable(d : rx.Subscription) {
        lstSubscriptions.add(d)
    }

    override fun onCleared() {
        super.onCleared()

        lstSubscriptions.forEach { if(!it.isUnsubscribed) it.unsubscribe() }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks?.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.remove(callback)
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with [Bindable] to generate a field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.notifyCallbacks(this, fieldId, null)
    }

}