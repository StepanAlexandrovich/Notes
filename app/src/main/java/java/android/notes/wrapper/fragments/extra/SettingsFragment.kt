package java.android.notes.wrapper.fragments.extra

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import java.android.notes.R
import java.android.notes.wrapper.helpers.CreateFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.buttonEditDate).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonEditDate -> CreateFragment.createCalendarFragment(requireActivity() as AppCompatActivity)
        }
    }
}