package kpk.dev.presentation.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.ObservableSource


fun EditText.addTextWatcher(): ObservableSource<Boolean> {
    return Observable.create { emmiter ->
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    if (it.isEmpty()) {
                        emmiter.onNext(false)
                    } else {
                        emmiter.onNext(true)
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }
}