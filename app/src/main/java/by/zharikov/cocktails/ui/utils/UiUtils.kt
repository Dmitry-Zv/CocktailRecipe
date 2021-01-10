package by.zharikov.cocktails.ui.utils

import android.content.DialogInterface
import androidx.fragment.app.Fragment
import by.zharikov.cocktails.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showAlert(
    title:Int,
    message : String,
    positiveButtonResId:Int = R.string.positive_button,
    negativeButtonResId:Int = R.string.negative_button,
    positiveButtonFun: (() -> Unit)? = null,
    negativeButtonFun: (() -> Unit)? = null
){
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonResId, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                positiveButtonFun?.let { it() }
                dialog?.dismiss()
            }

        })
        .setNegativeButton(negativeButtonResId, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                negativeButtonFun?.let { it() }
                dialog?.dismiss()
            }

        })
        .show()
}