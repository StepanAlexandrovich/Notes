package java.android.notes.wrapper.fragments.core

import androidx.recyclerview.widget.RecyclerView
import java.android.notes.R
import android.os.Bundle
import android.view.*
import java.android.notes.activity.IDatatSourseHandler
import java.android.notes.listeners.ButtonsClickListener
import androidx.appcompat.app.AppCompatActivity
import java.android.notes.wrapper.helpers.Extra
import java.android.notes.activity.MainActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import java.android.notes.listeners.ItemsClickListener
import androidx.fragment.app.Fragment
import java.android.notes.core.Control
import java.android.notes.listeners.MenuListener

class NotesFragment : Fragment() {
    private var control: Control? = null
    private var adapter: NotesAdapter? = null
    private var rv: RecyclerView? = null
    private val animDelay = 500
    private val imageDirectionId = R.drawable.list_down
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        control = (activity as IDatatSourseHandler).getterControl()
        initList(view)
        ButtonsClickListener((activity as AppCompatActivity?)!!, view, control!!, rv!!)

        // переделать
        Extra.initToolbar(activity as AppCompatActivity, R.id.toolbarNotes)
        // переделать и убрать импорт MainActivity
        (requireActivity() as MainActivity).initDrawer()
    }

    private fun initList(view: View) {
        adapter = NotesAdapter()

        rv = view.findViewById<RecyclerView>(R.id.rvNotes)



        // animation
        val animator = DefaultItemAnimator()
        animator.addDuration = animDelay.toLong()
        animator.changeDuration = animDelay.toLong()
        animator.removeDuration = animDelay.toLong()
        rv?.setItemAnimator(animator)
        adapter!!.setList(control!!.notes.notes)
        val llm = LinearLayoutManager(context)
        llm.reverseLayout = true
        llm.stackFromEnd = true
        rv?.postDelayed(Runnable { rv?.scrollToPosition(control!!.notes.numberOfNotes() - 1) }, 200)
        rv?.setLayoutManager(llm)
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.separator, null))
        rv?.addItemDecoration(decorator)
        rv?.setAdapter(adapter)
        adapter!!.setListener(
            ItemsClickListener(
                control!!,
                (activity as AppCompatActivity?)!!,
                adapter!!
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_notes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return MenuListener((activity as AppCompatActivity?)!!, item, control!!, adapter!!)
            .menuSwitch()
    }
}