package black.bracken.bottomnavigationbackdemo.model

enum class MainBottomNavigationSelectedItem {
    HOME,
    DASHBOARD,
    NOTIFICATIONS;

    fun isHome(): Boolean = (this == HOME)
    fun isDashboard(): Boolean = (this == DASHBOARD)
    fun isNotifications(): Boolean = (this == NOTIFICATIONS)
}