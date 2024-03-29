package kpk.dev.presentation.dialog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

class ErrorDialog : BaseDialog() {

    companion object {
        fun getInstance(args: Bundle): ErrorDialog {
            val dialog = ErrorDialog()
            dialog.arguments = args
            return dialog
        }
    }

    override fun initUI(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupDialog(dialogBuilder: AlertDialog.Builder) {
        dialogBuilder.apply {
            this.setPositiveButton("OK") { _, _ -> dismiss() }
        }
        dialogBuilder.setTitle(arguments?.getString(TITLE_ARG_KEY))
        dialogBuilder.setMessage(arguments?.getString(MESSAGE_ARG_KEY))
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getIsCustomDialog(): Boolean = false

    override fun getDialogTag(): String = ErrorDialog::class.java.simpleName
}