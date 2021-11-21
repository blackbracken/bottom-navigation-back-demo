package black.bracken.bottomnavigationbackdemo

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.bottomnavigationbackdemo.model.MainBottomNavigationSelectedItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val reselectedItemOnRootSource: MutableSharedFlow<MainBottomNavigationSelectedItem> =
        MutableSharedFlow()
    val reselectedItemOnRoot: SharedFlow<MainBottomNavigationSelectedItem> =
        reselectedItemOnRootSource.asSharedFlow()

    fun reselectBottomNavigationItemOnRoot(@IdRes selectedMenuId: Int) {
        val reselected = when (selectedMenuId) {
            R.id.homeFragment -> MainBottomNavigationSelectedItem.HOME
            R.id.dashboardFragment -> MainBottomNavigationSelectedItem.DASHBOARD
            R.id.notificationsFragment -> MainBottomNavigationSelectedItem.NOTIFICATIONS
            else -> return
        }

        viewModelScope.launch {
            reselectedItemOnRootSource.emit(reselected)
        }
    }

}