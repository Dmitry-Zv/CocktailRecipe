package by.zharikov.cocktails

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.zharikov.cocktails.ui.cocktailbyfirstletter.ClickOnCardViewRecipe
import by.zharikov.cocktails.ui.home.ClickEventHandler
import by.zharikov.shared.data.entity.cocktail.Cocktail

class MainActivity : AppCompatActivity(),ClickEventHandler{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.homeFragment, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun viewNextFragment(holder: View, position: Int) {
        when (position) {
            0 -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_navigation_fragment_by_first_letter)
            1 -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_cocktailByNameFragment)
            2 -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_cocktailRandomFragment)
        }
    }
}