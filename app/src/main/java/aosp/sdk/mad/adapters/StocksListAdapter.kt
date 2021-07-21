package aosp.sdk.mad.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import aosp.sdk.mad.BR
import aosp.sdk.mad.R
import aosp.sdk.mad.store.data.Ticker

class StocksListAdapter(private val callback: (Ticker) -> Unit)
    : PagingDataAdapter<Ticker, StocksListAdapter.StockHolder>(TickerComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.stocks_list_item, parent, false
        )
        return StockHolder(callback, binding)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class StockHolder(private val callback: (Ticker) -> Unit, private val binding : ViewDataBinding)
        : RecyclerView.ViewHolder(binding.root) {
        private lateinit var ticker: Ticker

        init {
            binding.root.setOnClickListener { callback(ticker) }
        }
        fun bind(item: Ticker) {
            ticker = item
            binding.setVariable(BR.ticker, item)
            binding.executePendingBindings()
        }
    }

    object TickerComparator : DiffUtil.ItemCallback<Ticker>() {
        override fun areItemsTheSame(oldItem: Ticker, newItem: Ticker): Boolean {
            return oldItem.ticker == newItem.ticker
        }

        override fun areContentsTheSame(oldItem: Ticker, newItem: Ticker): Boolean {
            return oldItem == newItem
        }

    }
}