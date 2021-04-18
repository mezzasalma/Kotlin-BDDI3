package com.mezzasalma.neighbors.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mezzasalma.neighbors.NavigationListener
import com.mezzasalma.neighbors.R
import com.mezzasalma.neighbors.adapters.ListNeighborHandler
import com.mezzasalma.neighbors.adapters.ListNeighborsAdapter
import com.mezzasalma.neighbors.di.DI
import com.mezzasalma.neighbors.fragments.AddNeighborFragment
import com.mezzasalma.neighbors.models.Neighbor
import com.mezzasalma.neighbors.viewmodels.NeighborViewModel

/**
 * Cette classe set un fragment.
 * Cette classe est aussi un ListNeighborHandler, elle implémente l'interface
 */
class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: NeighborViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
    }

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        (activity as? NavigationListener)?.updateTitle(R.string.list_neighbors_title)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

        val addNeighbor: FloatingActionButton = view.findViewById(R.id.add_neighbor_button)
        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighborFragment())
        }
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(R.string.confirm_delete)
                setMessage("${getString(R.string.message_delete)} ${neighbor.name} ?")
                setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener() { _: DialogInterface, _: Int ->
                        DI.repository.deleteNeighbor(neighbor)
                        setData()
                        Toast.makeText(it, R.string.wow, Toast.LENGTH_SHORT).show()
                    }
                )
                setNegativeButton(R.string.cancel, null)
            }
            builder.create()
        }
        alertDialog?.show()
    }

    override fun onUpdateFavoriteStatus(neighbor: Neighbor) {
        DI.repository.updateFavoriteStatus(neighbor)
        setData()
    }

    override fun openLink(neighbor: Neighbor) {
        val url = Uri.parse("http://${neighbor.webSite}")
        val i = Intent(Intent.ACTION_VIEW, url)
        startActivity(i)
    }

    private fun setData() {
        viewModel.neighbors.observe(viewLifecycleOwner) {
            val adapter = ListNeighborsAdapter(it, this)
            recyclerView.adapter = adapter
        }
    }
}
