package com.mezzasalma.neighbors.ui

import android.os.Bundle
import android.view.Menu
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mezzasalma.neighbors.NavigationListener
import com.mezzasalma.neighbors.R
import com.mezzasalma.neighbors.di.DI
import com.mezzasalma.neighbors.ui.fragments.ListNeighborsFragment

class NeighborsActivity : AppCompatActivity(), NavigationListener {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DI.inject(application)

        setContentView(R.layout.activity_neighbors)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        showFragment(ListNeighborsFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val item = menu.findItem(R.id.persistence)
        item.setActionView(R.layout.switch_layout)
        val switch = item.actionView.findViewById<SwitchCompat>(R.id.persistence_button)

        switch.isChecked = DI.repository.persistence

        switch.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
            DI.repository.switchPersistence(application, b)
            Toast.makeText(this, "Switch clicked, value is $b", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }
}
