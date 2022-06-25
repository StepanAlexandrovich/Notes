package java.android.notes.wrapper.fragments.extra

import java.android.notes.wrapper.alerts.CustomDialogListener
import java.android.notes.wrapper.alerts.CustomDialogFragmentWithView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import java.android.notes.R
import java.android.notes.wrapper.helpers.CreateFragment
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.fragment.app.Fragment

class LaunchFragment : Fragment(), View.OnClickListener, CustomDialogListener {
    private val dialog: CustomDialogFragmentWithView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_launch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.buttonOpenNotes).setOnClickListener(this)
        view.findViewById<View>(R.id.buttonClose).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonOpenNotes ->
                //dialog = new CustomDialogFragmentWithView();
                //dialog.setListener(this);
                //dialog.show(requireActivity().getSupportFragmentManager(),CustomDialogFragmentWithView.TAG);

                // временно
                CreateFragment.createNotesFragment(requireActivity() as AppCompatActivity)
            R.id.buttonClose -> requireActivity().finish()
        }
    }

    ////////////////////////
    override fun onOk() {
        if (dialog!!.editText!!.text.toString() == "4") {
            CreateFragment.createNotesFragment(requireActivity() as AppCompatActivity)
        } else {
            Toast.makeText(
                requireActivity() as AppCompatActivity,
                "Результат проведённого тэста указывает на то, что уровня знаний недостаточно. Совершенствуйте свои навыки и обязательно возвращайтесь снова",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onNo() {
        Toast.makeText(
            requireActivity() as AppCompatActivity,
            "Действие отменено пользователем",
            Toast.LENGTH_SHORT
        ).show()
    }
}