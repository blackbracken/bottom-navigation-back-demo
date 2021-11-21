package black.bracken.bottomnavigationbackdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import black.bracken.bottomnavigationbackdemo.databinding.ActivityMainBinding
import black.bracken.bottomnavigationbackdemo.model.MainBottomNavigationSelectedItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        with(binding.navView) {
            setOnItemReselectedListener { menuItem ->
                val menuItemIdToFragmentIdMap = mapOf(
                    R.id.navigation_home to R.id.homeFragment,
                    R.id.navigation_dashboard to R.id.dashboardFragment,
                    R.id.navigation_notifications to R.id.notificationsFragment,
                )
                val rootDestinationIds = menuItemIdToFragmentIdMap.values
                val currentId = navController.currentDestination?.id
                    ?: return@setOnItemReselectedListener
                val rootId = menuItemIdToFragmentIdMap[menuItem.itemId]
                    ?: return@setOnItemReselectedListener

                if (currentId in rootDestinationIds) {
                    viewModel.reselectBottomNavigationItemOnRoot(rootId)
                } else {
                    navController.popBackStack(rootId, false)
                }
            }
        }
    }
}