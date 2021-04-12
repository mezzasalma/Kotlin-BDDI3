package com.mezzasalma.neighbors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mezzasalma.neighbors.NavigationListener
import com.mezzasalma.neighbors.R
import com.mezzasalma.neighbors.databinding.AddNeighborBinding
import com.mezzasalma.neighbors.models.Neighbor

class AddNeighborFragment : Fragment() {
    lateinit var binding: AddNeighborBinding //  par rapport au nom du layout
    var phoneIsValid: Boolean = false

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_neighbor, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.add_neighbor, container, false)
        binding.lifecycleOwner = this

        (activity as? NavigationListener)?.updateTitle(R.string.add_neighbor_title)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compute()
    }

    private fun compute() {
        with(binding) {

            avatar.error = getString(R.string.error)

            saveButton.setOnClickListener() {
                onSaveForm()
            }
            avatar.doAfterTextChanged {
                enableButton()
            }
            name.doAfterTextChanged {
                enableButton()
            }
            tel.doAfterTextChanged {
                val phone = tel.text.toString()
                if (phone.take(2) != "06" || phone.take(2) != "07") {
                    Toast.makeText(activity, "Phone number isn't valid", Toast.LENGTH_SHORT).show()
                } else {
                    phoneIsValid = true
                }
                enableButton()
            }
            address.doAfterTextChanged {
                enableButton()
            }
            website.doAfterTextChanged {
                enableButton()
            }
            about.doAfterTextChanged {
                enableButton()
            }
        }
        println("computed !!!")
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
//            Toast.makeText(view?.context, "${saveButton.isEnabled}", Toast.LENGTH_SHORT).show()
            println("AVATAR URI CONTENT" + avatar.text)
            println(saveButton.isEnabled)
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
