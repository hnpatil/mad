package aosp.sdk.mad.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import aosp.sdk.mad.store.StocksRepo
import aosp.sdk.mad.store.data.Ticker

class TickerPagingSource(private val stocksRepo: StocksRepo, private val pageSize: Int): PagingSource<Int, Ticker>() {
    override fun getRefreshKey(state: PagingState<Int, Ticker>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Ticker> {
        return try {
            val nextPage = params.key ?: 0
            val response = stocksRepo.getTickers(nextPage, pageSize)
            val hasMore = response.size == pageSize

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = if (hasMore) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}