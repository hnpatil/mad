package aosp.sdk.mad.store.local

import android.content.Context
import aosp.sdk.mad.store.data.TickerDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.suspendCoroutine

class FileStore(private val context: Context) {

    private val scope = CoroutineScope(Dispatchers.IO)

    suspend fun saveTickerDetails(tickerDetail: TickerDetail) {
        val repo = File(context.filesDir, "ticker")
        val file = File(repo, tickerDetail.symbol!!)
        saveData(repo, file, tickerDetail.toString())
    }

    suspend fun fetchTickerDetail(ticker: String): TickerDetail? {
        val repo = File(context.filesDir, "ticker")
        val file = File(repo, ticker)
        return if (file.exists()) {
            TickerDetail.fromString(fetchData(file))
        } else {
            null
        }
    }

    private suspend fun saveData(repo: File, file: File, fileContent: String): Boolean {
        return suspendCoroutine {
            scope.launch {
                if (!repo.exists()) {
                    repo.mkdir()
                }
                file.writeText(fileContent)
                it.resumeWith(Result.success(true))
            }
        }
    }

    private suspend fun fetchData(file: File): String {
        return suspendCoroutine {
            scope.launch {
                val raw = StringBuilder()
                file.forEachLine { chars -> raw.append(chars) }
                it.resumeWith(Result.success(raw.toString()))
            }
        }
    }
}