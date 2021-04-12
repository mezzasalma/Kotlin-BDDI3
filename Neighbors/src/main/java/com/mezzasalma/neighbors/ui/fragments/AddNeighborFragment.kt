package com.mezzasalma.neighbors.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.mezzasalma.neighbors.NavigationListener
import com.mezzasalma.neighbors.R
import com.mezzasalma.neighbors.databinding.AddNeighborBinding
import com.mezzasalma.neighbors.models.Neighbor

class AddNeighborFragment : Fragment() {
    lateinit var binding: AddNeighborBinding //  par rapport au nom du layout
    private lateinit var formView: View
    private lateinit var fields: List<TextInputEditText>
    private var phoneIsValid = false

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.add_neighbor, container, false)
//        binding = DataBindingUtil.inflate(inflater, R.layout.add_neighbor, container, false)
//        binding.lifecycleOwner = this

        binding = DataBindingUtil.inflate(inflater, R.layout.add_neighbor, container, false)
        with(binding) {
            fields = listOf(name, avatar, address, tel, about, website)
        }
        formView = binding.root

        (activity as? NavigationListener)?.updateTitle(R.string.add_neighbor_title)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind()
    }

    private fun bind() {
        with(binding) {
            fields.forEach { it.doAfterTextChanged { onUserInput() } }

//            tel.doAfterTextChanged { checkPhone(it) }
//            avatar.doAfterTextChanged { checkUrl(it) }
//            website.doAfterTextChanged { checkUrl(it) }

            saveButton.setOnClickListener() { onSaveForm() }
        }
        println("computed !!!")
    }

    private fun onUserInput() {
        enableButton()
    }

    private fun checkPhone(it: Editable?) {
        val phone = it.toString()
        phoneIsValid = phone.length == 10 && (phone.startsWith("06") || phone.startsWith("07"))
//        if (!phoneIsValid) binding.tel.error("")
    }

    private fun checkUrl(it: Editable?) {
        val url = it.toString()
        if (URLUtil.isValidUrl(url)) {
            //
        }
    }

    private fun enableButton() {
        with(binding) {
            saveButton.isEnabled = !avatar.text.isNullOrEmpty() &&
                !name.text.isNullOrEmpty() &&
                !tel.text.isNullOrEmpty() &&
                phoneIsValid &&
                !address.text.isNullOrEmpty() &&
                !website.text.isNullOrEmpty() &&
                !about.text.isNullOrEmpty()
        }
    }

    fun onSaveForm() {
        var newNeighbor: Neighbor
        with(binding) {
            saveButton.setOnClickListener() {
                newNeighbor = Neighbor(
                    0,
                    name.text.toString(),
                    avatar.text.toString(),
                    address.text.toString(),
                    tel.text.toString(),
                    about.text.toString(),
                    false,
                    website.text.toString()
                )
            }
        }
//        return newNeighbor
    }
}
