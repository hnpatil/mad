package aosp.sdk.mad.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import aosp.sdk.mad.R
import aosp.sdk.mad.vm.StocksVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StocksListFragment : Fragment() {

    private val vm: StocksVM by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stocks_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.stocksList)
        recyclerView.adapter = vm.adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false )
        return view
    }
}