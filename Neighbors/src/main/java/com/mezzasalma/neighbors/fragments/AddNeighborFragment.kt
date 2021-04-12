package com.mezzasalma.neighbors.fragments

import android.app.Application
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.mezzasalma.neighbors.NavigationListener
import com.mezzasalma.neighbors.R
import com.mezzasalma.neighbors.data.NeighborRepository
import com.mezzasalma.neighbors.databinding.AddNeighborBinding
import com.mezzasalma.neighbors.models.Neighbor

class AddNeighborFragment : Fragment() {
    lateinit var binding: AddNeighborBinding
    private lateinit var formView: View
    private lateinit var fields: List<TextInputEditText>
    private var phoneValidate = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_neighbor, container, false)
        with(binding) {
            fields = listOf(name, avatar, address, tel, about, website)
        }

        formView = binding.root
        (activity as? NavigationListener)?.updateTitle(R.string.add_neighbor_title)

        return formView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        fields.forEach { it.doAfterTextChanged { onUserInput() } }

        with(binding) {
            avatar.doAfterFinishedEditing { checkUrl(it) }
            tel.doAfterFinishedEditing { checkPhone(it) }
            website.doAfterFinishedEditing { checkUrl(it) }
            address.doAfterFinishedEditing { checkEmail(it) }

            saveButton.setOnClickListener { save() }
        }
    }

    private fun onUserInput() {
        with(binding) {
            val imageValidate = avatar.isValidUrl()
            val userCanSave = (
                fields.all { it.isNotEmpty() } &&
                    phoneValidate &&
                    imageValidate &&
                    website.isValidUrl()
                )
            saveButton.isEnabled = userCanSave
        }
    }

    private fun checkPhone(input: TextInputEditText) {
        val value = input.text ?: ""
        phoneValidate = value.length == 10 && (value.startsWith("06") || value.startsWith("07"))
        if (!phoneValidate) {
            input.error = getString(R.string.error)
        }
        onUserInput()
    }

    private fun checkUrl(input: TextInputEditText) {
        if (!input.isValidUrl()) {
            input.error = getString(R.string.error)
        }
    }

    private fun checkEmail(input: TextInputEditText) {
        if (!input.isValidEmail()) {
            input.error = getString(R.string.error)
        }
    }

    /**
     * Extension pour vérifier si le champ est correct
     */
    private fun TextInputEditText.isNotEmpty(): Boolean = text?.isNotEmpty() ?: false

    /**
     * Extension pour vérifier si le champ contient bien une url valide
     */
    private fun TextInputEditText.isValidUrl(): Boolean = URLUtil.isValidUrl(text.toString())

    /**
     * Extension pour vérifier si le champ contient bien une url valide
     */
    private fun TextInputEditText.isValidEmail(): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches()

    /**
     * Extension pour bind un listener à la fin de l'édition d'un champ
     */
    private fun TextInputEditText.doAfterFinishedEditing(callback: (TextInputEditText) -> Unit) {
        setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                callback.invoke(v as TextInputEditText)
            }
        }
    }

    private fun save() {
        /**
         * Récupérer l'instance de l'application, si elle est null arrêter l'exécution de la méthode
         */
        val application: Application = activity?.application ?: return
        val lastNeighborId = NeighborRepository.getInstance().getNeighbors().value?.last()?.id ?: 0
        val id = lastNeighborId + 1
        with(binding) {
            val newNeighbor = Neighbor(
                id = id,
                name = name.text.toString(),
                avatarUrl = avatar.text.toString(),
                address = address.text.toString(),
                phoneNumber = tel.text.toString(),
                aboutMe = about.text.toString(),
                favorite = false,
                webSite = website.text.toString()
            )
            NeighborRepository.getInstance().createNeighbor(newNeighbor)
        }
        (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())
    }
}
