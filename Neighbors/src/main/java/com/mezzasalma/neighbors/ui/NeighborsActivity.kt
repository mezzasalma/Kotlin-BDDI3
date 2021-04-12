package com.mezzasalma.neighbors.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mezzasalma.neighbors.NavigationListener
import com.mezzasalma.neighbors.R
import com.mezzasalma.neighbors.ui.fragments.ListNeighborsFragment

class NeighborsActivity : AppCompatActivity(), NavigationListener {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_neighbors)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        showFragment(ListNeighborsFragment())
//        val button: Button = findViewById(R.id.click_me)
//        button.setOnClickListener() {
//            //  Lancer une activité
//            val intent = Intent(baseContext, NeighborsActivity2::class.java)
//            startActivity(intent)
//
//            //    Lancer une url
//            val url = Uri.parse("https://12millions.theoplawinski.com")
//            val i = Intent(Intent.ACTION_VIEW, url)
//            startActivity(i)
//
//            //    Composer un # de téléphone
//            val url = Uri.parse("tel:0614053552")
//            val i = Intent(Intent.ACTION_DIAL, url)
//            startActivity(i)
//        }
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
