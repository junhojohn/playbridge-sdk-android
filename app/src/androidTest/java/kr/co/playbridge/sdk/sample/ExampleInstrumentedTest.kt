package kr.co.playbridge.sdk.sample

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("kr.co.playbridge.sdk.sample", appContext.packageName)
    }

    @Test
    fun flavorTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        if(BuildConfig.FLAVOR.equals("uat")){
            assertEquals(true, appContext.resources.getString(R.string.app_name).contains("uat"))
            assertEquals(false, appContext.resources.getString(R.string.app_name).contains("real"))
        }else if(BuildConfig.FLAVOR.equals("real")){
            assertEquals(false, appContext.resources.getString(R.string.app_name).contains("uat"))
            assertEquals(true, appContext.resources.getString(R.string.app_name).contains("real"))
        }
    }

    @Test
    fun buildTypeTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        if(BuildConfig.DEBUG){
            assertEquals(true, BuildConfig.BUILD_TYPE.contains("debug"))
            assertEquals(false, BuildConfig.BUILD_TYPE.contains("real"))
        }else {
            assertEquals(false, BuildConfig.BUILD_TYPE.contains("debug"))
            assertEquals(true, BuildConfig.BUILD_TYPE.contains("real"))
        }
    }
}