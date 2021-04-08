package by.zharikov.cocktails

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.zharikov.cocktails.databinding.ActivityMainBinding
import by.zharikov.cocktails.ui.cocktailbyfirstletter.ClickOnCardViewRecipe
import by.zharikov.cocktails.ui.home.ClickEventHandler
import by.zharikov.cocktails.ui.register.RegistrationAndLoginActivity
import by.zharikov.cocktails.ui.register.RegistrationAndSigningViewModel
import by.zharikov.shared.data.entity.cocktail.Cocktail

class MainActivity : AppCompatActivity(), ClickEventHandler {

    private lateinit var registrationAndSigningViewModel: RegistrationAndSigningViewModel
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrationAndSigningViewModel =
            ViewModelProvider(this).get(RegistrationAndSigningViewModel::class.java)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment, R.id.navigation_saved_data
//            )
//        )
        setSupportActionBar(binding.toolBar)
        navView.setupWithNavController(navController)

    }

    override fun viewNextFragment(holder: View, position: Int) {
        when (position) {
            0 -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_navigation_fragment_by_first_letter)
            1 -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_cocktailByNameFragment)
            2 -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_cocktailRandomFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_log_out -> {
                registrationAndSigningViewModel.signOutUser()
                startActivity(Intent(this, RegistrationAndLoginActivity::class.java))
                finish()
                true
            }
            R.id.app_bar_setting -> {

                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }

    }
}
