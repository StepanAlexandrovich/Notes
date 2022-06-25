package java.android.notes.wrapper.fragments.core

import android.widget.EditText
import android.os.Bundle
import android.view.*
import java.android.notes.R
import java.android.notes.activity.IDatatSourseHandler
import java.android.notes.wrapper.helpers.Extra
import androidx.appcompat.app.AppCompatActivity
import java.android.notes.saveout.IPreferences
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.android.notes.core.Control

class NoteFragment : Fragment(), View.OnClickListener {
    private var control: Control? = null
    private var editTextHeadLine: EditText? = null
    private var editTextDescription: EditText? = null
    private var editTextBody: EditText? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        control = (activity as IDatatSourseHandler).getterControl()
        Extra.initToolbar(activity as AppCompatActivity, R.id.toolbarNote)
        editTextHeadLine = view.findViewById<EditText>(R.id.editTextHeadLine)
        editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)
        editTextBody = view.findViewById<EditText>(R.id.editTextBody)
        val note = control!!.note

        editTextHeadLine?.setText(note.headline)
        editTextDescription?.setText(note.description)
        editTextBody?.setText(note.body)
        view.findViewById<View>(R.id.buttonSave).setOnClickListener(this)
        view.findViewById<View>(R.id.buttonCancel).setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_note, menu)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonSave -> {
                val save = control!!.save(
                    editTextHeadLine!!.text.toString(),
                    editTextDescription!!.text.toString(),
                    editTextBody!!.text.toString()
                )
                if (save) {
                    //Toast.makeText(context,"dsds", Toast.LENGTH_SHORT).show()
                    //(requireActivity() as IPreferences).putStringControl() // save out
                    requireActivity().supportFragmentManager.popBackStack()
                } else {
                    Toast.makeText(context, "FILL NOTES'NAME", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.buttonCancel -> requireActivity().supportFragmentManager.popBackStack()
        }
    }
}