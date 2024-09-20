import android.app.NotificationManager
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.example.waterme.worker.WaterReminderWorker
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class WaterReminderWorkerTest {
    private lateinit var context: Context
    private val KEY_PLANT_NAME = "NAME"

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun waterReminderWorker_doWork_resultSuccess() {
        val inputData = Data.Builder()
            .putString(KEY_PLANT_NAME, "Test Plant")
            .build()

        val worker = TestListenableWorkerBuilder<WaterReminderWorker>(context)
            .setInputData(inputData)
            .build()

        runBlocking {
            val result = worker.doWork()
            assertTrue(result is ListenableWorker.Result.Success)
        }
    }
}