package aosp.sdk.mad.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import aosp.sdk.mad.BR
import aosp.sdk.mad.R
import aosp.sdk.mad.vm.StocksVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StockDetailFragment : Fragment() {

    private val vm: StocksVM by sharedViewModel()
    private lateinit var binding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.fragment_stock_detail, container, false
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        vm.tickerDetail.observe(this, Observer {
            binding.setVariable(BR.tickerDetail, it)
            binding.executePendingBindings()
        })

        vm.loadingTickerDetail.observe(this, Observer{
            binding.setVariable(BR.isLoading, it)
            binding.executePendingBindings()
        })
    }
}