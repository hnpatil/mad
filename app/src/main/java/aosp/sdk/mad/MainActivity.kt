package aosp.sdk.mad

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MainActivity : AppCompatActivity() {

    private val vm: StocksVM by stateViewModel()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.fetchTickers()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onStart() {
        super.onStart()
        vm.selectedTicker.observe(this, Observer {
            if (it != null) {
                navController.navigate(R.id.action_stocksListFragment_to_stockDetailFragment)
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        vm.updateSelectedTicker(null)
    }
}