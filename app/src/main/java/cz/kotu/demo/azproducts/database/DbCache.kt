package cz.kotu.demo.azproducts.database

import android.text.format.DateUtils
import androidx.room.withTransaction
import javax.inject.Inject

class DbCache @Inject constructor(
    private val cacheRecordDao: CacheRecordDao,
    private val db: AzDatabase,
) {
    /**
     * @param D - downloaded data
     * @param R - result data
     */
    suspend fun <D, R> cachedOrGet(
        cacheKey: String,
        download: suspend () -> D,
        storeDb: suspend (D) -> Unit,
        loadDb: suspend () -> R,
    ): R {
        val currentTime = System.currentTimeMillis()
        val cacheRecord = cacheRecordDao.get(cacheKey)
        val cacheTime = cacheRecord?.time ?: 0

        if (cacheTime + DateUtils.MINUTE_IN_MILLIS < currentTime) {
            download().also { downloaded ->
                db.withTransaction {
                    storeDb(downloaded)
                    cacheRecordDao.put(CacheRecord(cacheKey, currentTime))
                }
            }
        }

        return loadDb()
    }
}